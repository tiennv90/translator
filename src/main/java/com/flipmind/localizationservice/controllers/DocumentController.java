package com.flipmind.localizationservice.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.flipmind.localizationservice.GlobalVariable;
import com.flipmind.localizationservice.models.Document;
import com.flipmind.localizationservice.models.DocumentString;
import com.flipmind.localizationservice.models.Project;
import com.flipmind.localizationservice.models.Status;
import com.flipmind.localizationservice.models.Tenant;
import com.flipmind.localizationservice.models.Translation;
import com.flipmind.localizationservice.models.json.JSONDocument;
import com.flipmind.localizationservice.models.json.JSONLocale;
import com.flipmind.localizationservice.models.json.JSONMessage;
import com.flipmind.localizationservice.models.json.JSONProject;
import com.flipmind.localizationservice.repositories.DocumentRepository;
import com.flipmind.localizationservice.repositories.DocumentStringRepository;
import com.flipmind.localizationservice.repositories.ProjectRepository;
import com.flipmind.localizationservice.repositories.TenantRepository;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import com.wordnik.swagger.models.Response;

import flexjson.JSONSerializer;

@RestController
@RequestMapping(value="/api/1.0")
@Api(value = "Document", description = "Mange documents")
public class DocumentController {

	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private TenantRepository tenantRepository;
	
	@Autowired
	private DocumentRepository documentRepository;

	@Autowired
	private DocumentStringRepository documentStringRepository;
	
	@RequestMapping(value="/projects/{projectslug}/documents", method=RequestMethod.GET)
	@ResponseBody
	@ApiOperation(
			value = "Returns all documents for the given project", 
			httpMethod = "GET", notes = "Returns all documents for the given project", 
			response = Response.class
	)
	@ApiResponses(
			value = {
					@ApiResponse(code = 404, message = "Something went wrong")
	})
	public ResponseEntity<String> getAllDocuments(
		@ApiParam(value = "Project Slug",  required = true)
		@PathVariable("projectslug") String projectSlug, HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
		try {
			
			String apiKey = request.getHeader(GlobalVariable.API_KEY);
			if (apiKey == null || apiKey.isEmpty()) {
				apiKey = request.getParameter(GlobalVariable.API_KEY);
			}
			List<Tenant> tenants =  tenantRepository.findByApiKey(apiKey);
			
			boolean isAuthenticated = false;
			if (tenants != null && !tenants.isEmpty()) {
				isAuthenticated = true;
			}
			
			if (isAuthenticated) {
				List<Project> projects = projectRepository.findBySlug(projectSlug);
				
				List<Document> result = new ArrayList<Document>();
				
				if (projects != null && !projects.isEmpty()) {
					for (Project project : projects) {
						List<Document> documents = project.getDocuments();
						result.addAll(documents);
					}
				}
			
				
				HttpHeaders headers = new HttpHeaders();
				headers.add("Content-Type", "application/json; charset=utf-8");
				return new ResponseEntity<String>(new JSONSerializer().exclude("*.class", "project").include("project.project_id").serialize(result), headers, HttpStatus.OK);
			} else {
				request.getRequestDispatcher(GlobalVariable.NO_AUTHENTICATION_PATH).forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher(GlobalVariable.ERROR_PATH).forward(request, response);
		}
		return null;
	}
	
	@RequestMapping(value="/projects/{projectslug}/{documentslug}", method=RequestMethod.GET)
	@ResponseBody
	@ApiResponses(
			value = {
					@ApiResponse(code = 404, message = "Something went wrong")
	})
	@ApiOperation(
			value = "Returns the document with the given ID", 
			httpMethod = "GET", notes = "Returns the document with the given ID along with the translations of that document", 
			response = Response.class
	)	
	public ResponseEntity<String>  getDocument(
			@ApiParam(value = "Project Slug",  required = true)
			@PathVariable("projectslug") String projectSlug, 
			
			@ApiParam(value = "Document Slug",  required = true)
			@PathVariable("documentslug") String documentSlug, 
			
			HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			String apiKey = request.getHeader(GlobalVariable.API_KEY);
			if (apiKey == null || apiKey.isEmpty()) {
				apiKey = request.getParameter(GlobalVariable.API_KEY);
			}
			
			List<Tenant> tenants =  tenantRepository.findByApiKey(apiKey);
			
			boolean isAuthenticated = false;
			
			if (tenants != null && !tenants.isEmpty()) {
				isAuthenticated = true;
			}
				
			List<Project> projects = projectRepository.findBySlug(projectSlug);
			
			List<JSONDocument> result = new ArrayList<JSONDocument>();
			List<JSONLocale> noAuthenticationResult = new ArrayList<JSONLocale>();
			if (projects != null && !projects.isEmpty()) {
				for (Project project : projects) {
					
					for (Document document : project.getDocuments()) {
						if (document.getSlug().equals(documentSlug)) {
							JSONDocument jsonDocument = new JSONDocument();
							
							if (isAuthenticated == true) {
								
								jsonDocument.setId(document.getId());
								jsonDocument.setSlug(document.getSlug());
								jsonDocument.setProject(new JSONProject(project.getId()));
								
							}
							for (Translation translation : document.getTranslations()) {
								if (translation.getStatus() != null && translation.getStatus().equals(Status.PUBLISHED) && translation.getLocale() != null) {
									
									JSONLocale newLocale = new JSONLocale(translation.getLocale().getLocaleCode(), translation.getLocale().getTitle());
									
									if(!jsonDocument.getPublishedLocales().contains(newLocale)) {
										jsonDocument.getPublishedLocales().add(newLocale);
									}
									
									if (!noAuthenticationResult.contains(newLocale)) {
										noAuthenticationResult.add(newLocale);
									}
									
								}
							}
							
							result.add(jsonDocument);
						}
					}
				}
			}
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "application/json; charset=utf-8");
			if (isAuthenticated) {
				return new ResponseEntity<String>(new JSONSerializer().exclude("*.class").deepSerialize(result), headers, HttpStatus.OK) ;
			}
			return new ResponseEntity<String>(new JSONSerializer().exclude("*.class").deepSerialize(noAuthenticationResult), headers, HttpStatus.OK) ; 
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher(GlobalVariable.ERROR_PATH).forward(request, response);
		}
		return null;
		
	}
	
	@RequestMapping(value="/projects/{projectslug}/{documentslug}", method=RequestMethod.DELETE)
	@ResponseBody
	@ApiResponses(
			value = {
					@ApiResponse(code = 404, message = "Something went wrong")
	})
	@ApiOperation(
			value = "Sets the date deleted on the document", 
			httpMethod = "DELETE", notes = "Sets the date deleted on the document (if not already deleted)", 
			response = Response.class
	)
	public ResponseEntity<String> deleteDocument(
			@ApiParam(value = "Project Slug",  required = true)
			@PathVariable("projectslug") String projectSlug, 
			
			@ApiParam(value = "Document Slug",  required = true)
			@PathVariable("documentslug") String documentSlug, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			String apiKey = request.getHeader(GlobalVariable.API_KEY);
			if (apiKey == null || apiKey.isEmpty()) {
				apiKey = request.getParameter(GlobalVariable.API_KEY);
			}
			
			List<Tenant> tenants =  tenantRepository.findByApiKey(apiKey);
			
			boolean isAuthenticated = false;
			
			if (tenants != null && !tenants.isEmpty()) {
				isAuthenticated = true;
			}
			
			if (isAuthenticated) {
				List<Project> projects = projectRepository.findBySlug(projectSlug);
				
				if (projects != null && !projects.isEmpty()) {
					
					JSONMessage message = new JSONMessage();
					message.setMessage("Sets the date deleted on the document (if not already deleted)");
					
					for (Project project : projects) {
						for (Document document : project.getDocuments()) {
							if (document.getSlug().equals(documentSlug) && 
									(document.getDeletedDate() == null || document.getDeletedDate().toString().isEmpty())) {
								document.setDeletedDate(new Date());
								
								documentRepository.save(document);
								message.setSuccess(true);
								message.getItems().add("Document: " + document.getSlug()  + " was deleted successfully.");
							}
						}
					}
					
					if (message.isSuccess() == false) {
						message.getItems().add("Document: " + documentSlug  + " was deleted by someone or the document does not exist");
					}
					HttpHeaders headers = new HttpHeaders();
					headers.add("Content-Type", "application/json; charset=utf-8");
					return new ResponseEntity<String>(new JSONSerializer().exclude("*.class").deepSerialize(message), headers, HttpStatus.OK) ;
				}
			} else {
				request.getRequestDispatcher(GlobalVariable.NO_AUTHENTICATION_PATH).forward(request, response);
			}
		} catch (Exception e) {
			request.getRequestDispatcher(GlobalVariable.ERROR_PATH).forward(request, response);
		}
		return null;
		
	}
	
	
	@RequestMapping(value="/projects/{projectslug}", method=RequestMethod.POST)
	@ResponseBody
	@ApiResponses(
			value = {
					@ApiResponse(code = 404, message = "Something went wrong")
	})
	@ApiOperation(
			value = "Creates/updates the document for the tenant", 
			httpMethod = "POST", notes = "Accepts a JSON document and creates/updates the document for the tenant", 
			response = Response.class
	)	
	public ResponseEntity<String> createDocument(			
			@ApiParam(value = "Project Slug",  required = true)
			@PathVariable("projectslug") String projectSlug, @RequestBody JSONDocument jsonDocument, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {	
			
			String apiKey = request.getHeader(GlobalVariable.API_KEY);
			if (apiKey == null || apiKey.isEmpty()) {
				apiKey = request.getParameter(GlobalVariable.API_KEY);
			}
			
			List<Tenant> tenants =  tenantRepository.findByApiKey(apiKey);
			
			boolean isAuthenticated = false;
			
			if (tenants != null && !tenants.isEmpty()) {
				isAuthenticated = true;
			}
			
			if (isAuthenticated) {
				Document document = documentRepository.findOne(jsonDocument.getId());
				boolean isSaveable = false;
				
				//if document existed then update the document base on the Request JSON
				if (document != null) {
					
					document.setSlug(jsonDocument.getSlug());
					document.setTitle(jsonDocument.getTitle());
					
					isSaveable = true;
					
				//if document not exist create a new document base on the JSON	
				} else {
					
					document = new Document();
					Project project = null;
					
					//if project id exited get project and assign the project for the new document
					if (jsonDocument.getProject() != null && jsonDocument.getProject().getId() > 0) {
						
						project = projectRepository.findOne(jsonDocument.getProject().getId());
						if (project != null) {
							document.setProject(project);
							isSaveable = true;
						}
						
					}
					
					//if project does not exist get project base on api_key and project slug
					if (document.getProject() == null) {
							
						if (tenants != null && tenants.isEmpty()) {
							Tenant tenant = tenants.get(0);
							for (Project p : tenant.getProjects()) {
								
								if (p.getSlug().equals(projectSlug)) {
									document.setProject(p);
									isSaveable = true;
									break;
								}
							}
						}
							
					}
					
					document.setSlug(jsonDocument.getSlug());
					document.setTitle(jsonDocument.getTitle());
				}
				
				JSONMessage message = new JSONMessage();
				message.setMessage("Creates/updates the document for the tenant");
				
				if (isSaveable) {
					
					documentRepository.save(document);
					message.setSuccess(true);
					message.getItems().add("Document " + document.getSlug() + " was updated/created successfully");
				} else {
					message.getItems().add("Can't create Document " + document.getSlug());
				}
				
				HttpHeaders headers = new HttpHeaders();
				headers.add("Content-Type", "application/json; charset=utf-8");
				return new ResponseEntity<String>(new JSONSerializer().exclude("*.class").deepSerialize(message), headers, HttpStatus.OK) ;
			} else {
				request.getRequestDispatcher(GlobalVariable.NO_AUTHENTICATION_PATH).forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher(GlobalVariable.ERROR_PATH).forward(request, response);
		}
		
		return null;
	}
		
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/projects/{projectslug}/{documentslug}/strings*", method=RequestMethod.PUT)
	@ResponseBody
	@ApiResponses(
			value = {
					@ApiResponse(code = 404, message = "Something went wrong")
	})
	@ApiOperation(
			value = "Adds the set of strings that the translator can enter", 
			httpMethod = "PUT", notes = "Adds the set of strings that the translator can enter (The template stored against the document)", 
			response = Response.class
	)
//	{
//		   "login.password.error":{
//		      "value":"Please enter your password",
//		      "public":true
//		   },
//		   "login.login.attempts":{
//		      "value":"You have made 0 login attempts",
//		      "public":true,
//		      "plurals":[
//		         {
//		            "1":"You have had one login attempt already"
//		         },
//		         {
//		            "2":"You have had two login attempts"
//		         },
//
//		      ]
//		   }
//		}
	public ResponseEntity<String> addStringAndDeleteNotIncludedContent(
			@ApiParam(value = "Project Slug",  required = true)
			@PathVariable("projectslug") String projectSlug, 
			
			@ApiParam(value = "Document Slug",  required = true)
			@PathVariable("documentslug") String documentSlug,
			
			HttpServletRequest request,
			HttpServletResponse response,
			
			@RequestParam("public") String isPublic,
			@RequestBody String jsonInput ) throws ServletException, IOException {
		
		JSONMessage message = new JSONMessage();
		HttpStatus httpStatus = HttpStatus.OK;
		System.out.println(jsonInput);
		try {
			List<Project> projects = projectRepository.findBySlug(projectSlug);
			Document currentDocument = null;
			if (projects != null && !projects.isEmpty()) {
				
				for (Project project : projects) {
					for (Document document : project.getDocuments()) {
						if (document.getSlug().equals(documentSlug)) {
							currentDocument = document;
							break;
						}
					}
				}
			
				if (currentDocument != null) {
					JSONObject jsonObject = new JSONObject(jsonInput);
					Set<String> keys = jsonObject.keySet();
					for (String key : keys) {
						JSONObject itemInputByTranslator = (JSONObject) jsonObject.get(key);
						String value = (String) itemInputByTranslator.get("value");
						boolean is_public = itemInputByTranslator.getBoolean("public");
						
						DocumentString documentString = new DocumentString();
						documentString.setStringKey(key);
						documentString.setDocument(currentDocument);
						if (value != null) {
							documentString.setStringValue(value);
						}
						
						if (is_public == true) {
							documentString.setPublic(true);
						}
						
						//save first document string value and key
						documentStringRepository.save(documentString);
						
						JSONArray jsonArrayPlurals = itemInputByTranslator.getJSONArray("plurals");
						if (jsonArrayPlurals != null && jsonArrayPlurals.length() > 0) {
							for (int i = 0; i < jsonArrayPlurals.length(); i++) {
								JSONObject jsonPlural = (JSONObject) jsonArrayPlurals.get(i);
								if (jsonPlural != null) {
									Set<String> plurakKeys = jsonPlural.keySet();
									for (String k : plurakKeys) {
										
										int plural = Integer.valueOf(k);
										documentString.setPlurals(plural);
										documentString.setStringValue(jsonPlural.getString(k));
										
										//save plural values with the current key
										documentStringRepository.save(documentString);
									}
								}
							}
						}
					}
					
					message.getItems().add("Document Strings was added successfully");
					if (isPublic != null && isPublic.equals("true")) {
						
					} else {
						
					}
				}
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
			message.getItems().add("Incorrect JSON format");
			httpStatus = HttpStatus.BAD_REQUEST;
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher(GlobalVariable.ERROR_PATH).forward(request, response);
		}
		message.setMessage("Adds the set of strings that the translator can enter");
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");
		
		return new ResponseEntity<String>(new JSONSerializer().exclude("*.class").deepSerialize(message), headers, httpStatus);
		
	}
	
	@RequestMapping(value="/projects/{projectslug}/{documentslug}/strings", method=RequestMethod.POST)
	@ApiResponses(
			value = {
					@ApiResponse(code = 404, message = "Something went wrong")
	})
	@ApiOperation(
			value = "creates/updates strings", 
			httpMethod = "POST", 
			notes = "creates/updates strings it does not delete strings that are not included in the POST", 
			response = Response.class
	)	
	public ResponseEntity<String> addString(
			@ApiParam(value = "Project Slug",  required = true)
			@PathVariable("projectslug") String projectSlug, 
			
			@ApiParam(value = "Document Slug",  required = true)
			@PathVariable("documentslug") String documentSlug,
			
			@RequestBody String jsonTranslatorInput) {
		
		JSONObject jsonObject = new JSONObject(jsonTranslatorInput);
		
		
		return null;
		
	}
	
	@RequestMapping(value="/projects/{projectslug}/{documentslug}/strings", method=RequestMethod.DELETE)
	@ResponseBody
	@ApiOperation(
			value = "Require credential, deletes  records within document", 
			httpMethod = "DELETE", 
			notes = "Accepts a post of ['login.login.attempts', ‘otherkey’] And deletes those records within this document", 
			response = Response.class
	)		
	public ResponseEntity<String> deleteRecords(
			
			@ApiParam(value = "Project Slug",  required = true)
			@PathVariable("projectslug") String projectSlug, 
			
			@ApiParam(value = "Document Slug",  required = true)
			@PathVariable("documentslug") String documentSlug,
			
			@RequestBody List<String> documentStrings,
			
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String apiKey = request.getHeader(GlobalVariable.API_KEY);
			if (apiKey == null || apiKey.isEmpty()) {
				apiKey = request.getParameter(GlobalVariable.API_KEY);
			}
			
			List<Tenant> tenants =  tenantRepository.findByApiKey(apiKey);
			
			boolean isAuthenticated = false;
			
			if (tenants != null && !tenants.isEmpty()) {
				isAuthenticated = true;
			}
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "application/json; charset=utf-8");
			
			JSONMessage message = new JSONMessage();
			message.setMessage("Deletes records within a specified document");
			
			if (isAuthenticated && documentStrings != null && !documentStrings.isEmpty()) {
				
				if (tenants != null && !tenants.isEmpty()) {
					Tenant tenant = tenants.get(0);
					for (Project project : tenant.getProjects()) {
						
						if (project.getSlug().equals(projectSlug)) {
							for (Document document : project.getDocuments()) {
								
								for (DocumentString string : document.getDocumentStrings()) {
									if (documentStrings.contains(string.getStringKey())) {
										documentStringRepository.delete(string);
										message.setSuccess(true);
										message.getItems().add("Key " + string.getStringKey() + " was deleted sucessfully");
									}
								}
								
								
							}
						}
						
					}
				}
				if (message.isSuccess() == false) {
					message.getItems().add("Your input keys does not match or already deleted by someone");
				}
				
			} else {
				message.getItems().add("Something went wrong");
				message.getItems().add("Request does not have authentication or documentStrings is empty");
			}
			
			return new ResponseEntity<String>(new JSONSerializer().exclude("*.class").deepSerialize(message), headers, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher(GlobalVariable.ERROR_PATH).forward(request, response);
		}
		return null;
		
	}

	
}
