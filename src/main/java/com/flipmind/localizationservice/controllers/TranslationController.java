package com.flipmind.localizationservice.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import com.wordnik.swagger.models.Response;

@RestController
@RequestMapping("/api/1.0")
@Api(value = "Translation", description = "Mange translations")
public class TranslationController {

	@RequestMapping(value = "/projects/{projectslug}/{documentslug}", method = RequestMethod.POST)
	@ApiResponses(
			value = {@ApiResponse(code = 404, message = "Something went wrong")
	})
	@ApiOperation(
			value = "Adds a translation to the document", 
			httpMethod = "POST", notes = "Adds a translation to the document", 
			response = Response.class
	)		
	public String addTranslation(
			@ApiParam(value = "Project Slug",  required = true)
			@PathVariable("projectslug") String projectSlug,
			
			@ApiParam(value = "Document Slug",  required = true)
			@PathVariable("documentslug") String documentSlug) {

		return "";

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
	public String deleteRecordsByLocale(
			@ApiParam(value = "Project Slug",  required = true)
			@PathVariable("projectslug") String projectSlug,
			
			@ApiParam(value = "Document Slug",  required = true)
			@PathVariable("documentslug") String documentSlug,
			
			@ApiParam(value = "Locale Code",  required = true)
			@PathVariable("localeCode") String localeCode) {

		return "";

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
