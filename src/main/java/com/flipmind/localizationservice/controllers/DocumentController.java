package com.flipmind.localizationservice.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.flipmind.localizationservice.GlobalVariable;
import com.flipmind.localizationservice.models.Document;
import com.flipmind.localizationservice.models.Project;
import com.flipmind.localizationservice.repositories.ProjectRepository;
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

	@RequestMapping(value="/projects/{projectslug}/documents", method=RequestMethod.GET)
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
			return new ResponseEntity<String>(new JSONSerializer().exclude("*.class", "project").include("project.project_id").serialize(projects), headers, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher(GlobalVariable.ERROR_PATH).forward(request, response);
		}
		return null;
	}
	
	@RequestMapping(value="/projects/{projectslug}/{documentslug}", method=RequestMethod.GET)
	@ApiResponses(
			value = {
					@ApiResponse(code = 404, message = "Something went wrong")
	})
	@ApiOperation(
			value = "Returns the document with the given ID", 
			httpMethod = "GET", notes = "Returns the document with the given ID along with the translations of that document", 
			response = Response.class
	)	
	public String getDocument(
			@ApiParam(value = "Project Slug",  required = true)
			@PathVariable("projectslug") String projectSlug, 
			
			@ApiParam(value = "Document Slug",  required = true)
			@PathVariable("documentslug") String documentSlug) {
		
		return "";
		
	}
	
	@RequestMapping(value="/projects/{projectslug}/{documentslug}", method=RequestMethod.DELETE)
	@ApiResponses(
			value = {
					@ApiResponse(code = 404, message = "Something went wrong")
	})
	@ApiOperation(
			value = "Sets the date deleted on the document", 
			httpMethod = "DELETE", notes = "Sets the date deleted on the document (if not already deleted)", 
			response = Response.class
	)
	public String deleteDocument(
			@ApiParam(value = "Project Slug",  required = true)
			@PathVariable("projectslug") String projectSlug, 
			
			@ApiParam(value = "Document Slug",  required = true)
			@PathVariable("documentslug") String documentSlug) {
		
		return "";
		
	}
	
	
	@RequestMapping(value="/projects/{projectslug}", method=RequestMethod.POST)
	@ApiResponses(
			value = {
					@ApiResponse(code = 404, message = "Something went wrong")
	})
	@ApiOperation(
			value = "Creates/updates the document for the tenant", 
			httpMethod = "POST", notes = "Accepts a JSON document and creates/updates the document for the tenant", 
			response = Response.class
	)	
	public String createDocument(			
			@ApiParam(value = "Project Slug",  required = true)
			@PathVariable("projectslug") String projectSlug) {
		return "";
	}
		
	
	@RequestMapping(value="/projects/{projectslug}/{documentslug}/strings?public=true", method=RequestMethod.PUT)
	@ApiResponses(
			value = {
					@ApiResponse(code = 404, message = "Something went wrong")
	})
	@ApiOperation(
			value = "Adds the set of strings that the translator can enter", 
			httpMethod = "PUT", notes = "Adds the set of strings that the translator can enter (The template stored against the document)", 
			response = Response.class
	)
	public String addStringAndDeleteNotIncludedContent(
			@ApiParam(value = "Project Slug",  required = true)
			@PathVariable("projectslug") String projectSlug, 
			
			@ApiParam(value = "Document Slug",  required = true)
			@PathVariable("documentslug") String documentSlug) {
		
		return "";
		
	}
	
	@RequestMapping(value="/projects/{projectslug}/{documentslug}/strings", method=RequestMethod.POST)
	@ApiResponses(
			value = {
					@ApiResponse(code = 404, message = "Something went wrong")
	})
	@ApiOperation(
			value = "creates/updates strings", 
			httpMethod = "POST", notes = "creates/updates strings it does not delete strings that are not included in the POST", 
			response = Response.class
	)	
	public String addString(
			@ApiParam(value = "Project Slug",  required = true)
			@PathVariable("projectslug") String projectSlug, 
			
			@ApiParam(value = "Document Slug",  required = true)
			@PathVariable("documentslug") String documentSlug) {
		
		return "";
		
	}
	
	@RequestMapping(value="/projects/{projectslug}/{documentslug}/strings", method=RequestMethod.DELETE)
	@ApiOperation(
			value = "Require credential, deletes those records within this document", 
			httpMethod = "DELETE", notes = "Accepts a post of ['login.login.attempts', ‘otherkey’] And deletes those records within this document", 
			response = Response.class
	)		
	public String deleteRecords(
			@ApiParam(value = "Project Slug",  required = true)
			@PathVariable("projectslug") String projectSlug, 
			
			@ApiParam(value = "Document Slug",  required = true)
			@PathVariable("documentslug") String documentSlug) {
		
		return "";
		
	}

	
}
