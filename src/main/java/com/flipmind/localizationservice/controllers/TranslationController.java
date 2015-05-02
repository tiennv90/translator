package com.flipmind.localizationservice.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.flipmind.localizationservice.GlobalVariable;
import com.flipmind.localizationservice.models.Document;
import com.flipmind.localizationservice.models.Locale;
import com.flipmind.localizationservice.models.Project;
import com.flipmind.localizationservice.models.Status;
import com.flipmind.localizationservice.models.Tenant;
import com.flipmind.localizationservice.models.TranslatedString;
import com.flipmind.localizationservice.models.Translation;
import com.flipmind.localizationservice.models.json.JSONMessage;
import com.flipmind.localizationservice.models.json.JSONTranslation;
import com.flipmind.localizationservice.repositories.DocumentRepository;
import com.flipmind.localizationservice.repositories.LocaleRepository;
import com.flipmind.localizationservice.repositories.TenantRepository;
import com.flipmind.localizationservice.repositories.TranslatedStringRepository;
import com.flipmind.localizationservice.repositories.TranslationRepository;
import com.flipmind.localizationservice.utils.Utils;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import com.wordnik.swagger.models.Response;

import flexjson.JSONSerializer;

@RestController
@RequestMapping("/api/1.0")
@Api(value = "Translation", description = "Manage translations")
public class TranslationController {
	
	@Autowired
	private TranslationRepository translationRepository;
	
	@Autowired 
	private TenantRepository tenantRepository;
	
	@Autowired
	private DocumentRepository documentRepository;

	@Autowired
	private LocaleRepository localeRepository;
	
	@Autowired
	private TranslatedStringRepository translatedStringRepository;
	
	@RequestMapping(value = "/projects/{projectslug}/{documentslug}", method = RequestMethod.POST)
	@ResponseBody
	@ApiResponses(
			value = {@ApiResponse(code = 404, message = "Something went wrong")
	})
	@ApiOperation(
			value = "Adds a translation to the document", 
			httpMethod = "POST", notes = "Adds a translation to the document", 
			response = Response.class
	)		
	public ResponseEntity<String> addTranslation(
			@ApiParam(value = "Project Slug",  required = true)
			@PathVariable("projectslug") String projectSlug,
			
			@ApiParam(value = "Document Slug",  required = true)
			@PathVariable("documentslug") String documentSlug,
			
			@RequestBody JSONTranslation jsonTranslation,
			
			HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
		
			String apiKey = request.getHeader(GlobalVariable.API_KEY);
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "application/json; charset=utf-8");
			
			JSONMessage message = new JSONMessage();
			message.setMessage("Adds a translation to the document");
			
			if (apiKey != null && !apiKey.isEmpty()) {
				List<Tenant> tenants = tenantRepository.findByApiKey(apiKey);
				
				
				if (tenants != null && !tenants.isEmpty()) {
					
					Translation translation = new Translation();
					
					//begin get document data
					if (jsonTranslation.getDocument() != null && jsonTranslation.getDocument().getId() > 0) {
						Document document = documentRepository.findOne(jsonTranslation.getDocument().getId());
						if (document != null) {
							translation.setDocument(document);
						} 
					}
					
					if (translation.getDocument() == null) {
						Tenant tenant = tenants.get(0);
						for (Project project : tenant.getProjects()) {
							
							if (project.getSlug().equals(projectSlug)) {
								for (Document document : project.getDocuments()) {
									
									if (document.getSlug().equals(documentSlug)) {
										translation.setDocument(document);
									}
									
								}
							}
							
						}					
					}
					//end get document data
					
					//begin get locale Data
					String localeCode = jsonTranslation.getLocale();
					List<Locale> locales = localeRepository.findByLocaleCode(localeCode);
					if (locales != null && !locales.isEmpty()) {
						translation.setLocale(locales.get(0));
					}
					//end get locale Data
					
					//get status
					Status status = Status.DRAFT;
					if (jsonTranslation.getStatus() != null) {
						if (jsonTranslation.getStatus().equalsIgnoreCase("PUBLISHED")) {
							status = Status.PUBLISHED;
						} else if (jsonTranslation.getStatus().equalsIgnoreCase("DELETED")) {
							status = Status.DELETED;
						}
					}
					translation.setStatus(status);
					
					//get active from date
					translation.setActiveFromDate(Utils.getDateFromString(jsonTranslation.getActiveFrom()));
					
					boolean isSaveable = false;
					if (translation.getDocument() != null && translation.getLocale() != null) {
						isSaveable = true;
					}
					
					if (isSaveable) {
						translation = translationRepository.save(translation);
						message.getItems().add("Translation: " + translation.getId() + " was added successfully");
					}
				}
			} else {
				
				message.getItems().add("Request does not have authentication or path variables does not exist");
			}
			
			return new ResponseEntity<String>(new JSONSerializer().exclude("*.class").deepSerialize(message), headers, HttpStatus.OK); 
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher(GlobalVariable.ERROR_PATH).forward(request, response);
		}
		return null;

	}

	@RequestMapping(value = "/projects/{projectslug}/{documentslug}/{localeCode}/strings", method = RequestMethod.DELETE)
	@ApiResponses(
			value = {@ApiResponse(code = 404, message = "Something went wrong")
	})
	@ApiOperation(
			value = "Require credential and deletes those records within the translatedstring table", 
			httpMethod = "DELETE", notes = "Accepts a post of [ 'login.login.attempts', ‘otherkey’] And deletes those records within the translatedstring table", 
			response = Response.class
	)
	public ResponseEntity<String> deleteRecordsByLocale(
			@ApiParam(value = "Project Slug",  required = true)
			@PathVariable("projectslug") String projectSlug,
			
			@ApiParam(value = "Document Slug",  required = true)
			@PathVariable("documentslug") String documentSlug,
			
			@ApiParam(value = "Locale Code",  required = true)
			@PathVariable("localeCode") String localeCode,
			
			@RequestBody List<String> stringKeys,
			HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			String apiKey = request.getHeader(GlobalVariable.API_KEY);
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "application/json; charset=utf-8");
			HttpStatus httpStatus = HttpStatus.OK;
			JSONMessage message = new JSONMessage();
			message.setMessage("Deletes records within the translatedstring table");
			
			List<Tenant> tenants =  tenantRepository.findByApiKey(apiKey);
			
			if (tenants != null && !tenants.isEmpty()) {
				
				//define a variable to check if there's no Translation in draft state
				boolean isOneDraftTranslation = false;
				
				Tenant tenant = tenants.get(0);
				for (Project project : tenant.getProjects()) {
					
					if (project.getSlug().equals(projectSlug)) {
						for (Document document : project.getDocuments()) {
							if (document.getSlug().equals(documentSlug)) {
								List<Translation> translations = document.getTranslations();
								
								for (Translation translation : translations) {
									System.out.println("====DEBUG===" + translation.getId());
									//The Translation to be affected is the current one in the Draft state
									if (translation.getStatus().equals(Status.DRAFT)) {
										
										isOneDraftTranslation = true;
										Locale locale = translation.getLocale();
										if (locale != null && locale.getLocaleCode() != null && locale.getLocaleCode().equals(localeCode)) {
											for (TranslatedString translatedString : translation.getTranslatedStrings()) {
												if (stringKeys.contains(translatedString.getKey())) {
													translatedStringRepository.delete(translatedString);
													message.setSuccess(true);
													message.getItems().add("TranslatedString " + translatedString.getKey() + " was deleted successfully");
												}
											}
										}
									}
								}
								
								//if none is in the draft status then a new Translation in the Draft status is created with 
								//the strings copied form the previous Published version.
								if (isOneDraftTranslation == false) {
									System.out.println("====isOneDraftTranslation false ===");
									for (Translation translation : translations) {
										if (translation.getStatus().equals(Status.PUBLISHED)) {
											System.out.println("====DEBUG: translation.getStatus().equals(Status.PUBLISHED)");
											Translation newDraftTranslation = new Translation();
											newDraftTranslation.setDocument(document);
											newDraftTranslation.setStatus(Status.DRAFT);
											List<Locale> locales = localeRepository.findByLocaleCode(localeCode);
											if (locales != null && !locales.isEmpty()) {
												newDraftTranslation.setLocale(locales.get(0));
											}
											newDraftTranslation = translationRepository.save(newDraftTranslation);
											
											//begin copy translated string to new translation
											if (translation.getTranslatedStrings() != null) {
												
												List<TranslatedString> listToCopy = new ArrayList<TranslatedString>();
												for (TranslatedString translatedString : translation.getTranslatedStrings()) {
													TranslatedString copyTranslatedString = new TranslatedString();
													copyTranslatedString.setKey(translatedString.getKey());
													copyTranslatedString.setValue(translatedString.getValue());
													copyTranslatedString.setTranslation(newDraftTranslation);
													copyTranslatedString.setPlurals(translatedString.getPlurals());
													copyTranslatedString = translatedStringRepository.save(copyTranslatedString);
													listToCopy.add(copyTranslatedString);
												}
												
												newDraftTranslation.setTranslatedStrings(listToCopy);
											}
											//end copy translated string to new translation
											
											message.getItems().add("There's no item in draft status. A new Translation in the draft status was created with id: " + newDraftTranslation.getId());
										}
										
									}
								}
								break;
							}
						}
					}
				}
				
				//print a message when translated strings can't be deleted via Draft Translation
				if (message.isSuccess() == false && isOneDraftTranslation == true) {
					message.getItems().add("Translated strings was not deleted, please recheck path variables");
				}
			} else {
				message.getItems().add("Request does not have authentication");
				httpStatus = HttpStatus.FORBIDDEN;
			}
			return new ResponseEntity<String>(new JSONSerializer().exclude("*.class").deepSerialize(message), headers, httpStatus);
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher(GlobalVariable.ERROR_PATH).forward(request, response);
		}
		return null;
	}
	
	@RequestMapping(value = "/projects/{projectslug}/{documentslug}/{localeCode}/strings", method = RequestMethod.POST)
	@ApiResponses(
			value = {@ApiResponse(code = 404, message = "Something went wrong")
	})
	@ApiOperation(
			value = "uploaded content to translatedstring table", 
			httpMethod = "POST", notes = "uploaded content to translatedstring table.Any fields " +
					"not existing in documentstring are ignored. A summary of the records created is " +
					"returned in a JSON document. Unspecified translated strings left un modified", 
			response = Response.class
	)	
	public String updateRecordsByLocale(
			@ApiParam(value = "Project Slug",  required = true)
			@PathVariable("projectslug") String projectSlug,
			
			@ApiParam(value = "Document Slug",  required = true)
			@PathVariable("documentslug") String documentSlug,
			
			@ApiParam(value = "Locale Code",  required = true)
			@PathVariable("localeCode") String localeCode) {

		return "";

	}
	
	@RequestMapping(value = "/projects/{projectslug}/{documentslug}/{export_format}/{locale}", method = RequestMethod.GET)
	@ApiResponses(
			value = {@ApiResponse(code = 404, message = "Something went wrong")
	})
	@ApiOperation(
			value = "Exports the translated strings to one of our supported formats", 
			httpMethod = "GET", notes = "Exports the translated strings to one of our supported formats", 
			response = Response.class
	)	
	public String exportStringByFormatAndLocale(
			@ApiParam(value = "Project Slug",  required = true)
			@PathVariable("projectslug") String projectSlug,
			
			@ApiParam(value = "Document Slug",  required = true)
			@PathVariable("documentslug") String documentSlug,
			
			@ApiParam(value = "Export Format",  required = true)
			@PathVariable("export_format") String exportFormat,
			
			@ApiParam(value = "Locale Code",  required = true)
			@PathVariable("localeCode") String localeCode) {

		return "";

	}	
	
	@RequestMapping(value = "/auth/{appkey}projects/{projectslug}/{documentslug}/{export_format}/{locale}", method = RequestMethod.GET)
	@ApiResponses(
			value = {@ApiResponse(code = 404, message = "Something went wrong")
	})
	@ApiOperation(
			value = "Require credential, export the translated strings to one of our supported formats", 
			httpMethod = "GET", notes = "Require credential on url to export the translated strings to one of our supported formats", 
			response = Response.class
	)
	public String authenticationExportStringByFormatAndLocale(
			@ApiParam(value = "Application key",  required = true)
			@PathVariable("appkey") String appKey,
			
			@ApiParam(value = "Project Slug",  required = true)
			@PathVariable("projectslug") String projectSlug,
			
			@ApiParam(value = "Document Slug",  required = true)
			@PathVariable("documentslug") String documentSlug,
			
			@ApiParam(value = "Export Format",  required = true)
			@PathVariable("export_format") String exportFormat,
			
			@ApiParam(value = "Locale Code",  required = true)
			@PathVariable("localeCode") String localeCode) {

		return "";

	}	
}
