package com.flipmind.localizationservice.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;
import com.wordnik.swagger.models.Response;

@Controller
@RequestMapping("/api/1.0")
@Api(value = "Project", description = "Mange projects")
public class ProjectsController {
	

	@RequestMapping(value="/projects", method=RequestMethod.GET)
	@ApiResponses(
			value = {@ApiResponse(code = 404, message = "Something went wrong")
	})
	@ApiOperation(
			value = "Returns all the projects for the tenant", 
			httpMethod = "GET", notes = "Returns all the projects for the tenant", 
			response = Response.class
	)	
	public String index() {
		
		return "";
		
	}
	
	@RequestMapping(value="/projects/{projectslug}", method=RequestMethod.GET)
	@ApiResponses(
			value = {@ApiResponse(code = 404, message = "Something went wrong")
	})
	@ApiOperation(
			value = "Returns the project with the given ID", 
			httpMethod = "GET", notes = "Returns the project with the given ID", 
			response = Response.class
	)	
	public String getProject(
			@ApiParam(value = "Project Slug",  required = true)
			@PathVariable("projectslug") String projectslug) {
		
		return "";
		
	}
	
	@RequestMapping(value="/projects/{projectslug}", method=RequestMethod.DELETE)
	@ApiResponses(
			value = {@ApiResponse(code = 404, message = "Something went wrong")
	})
	@ApiOperation(
			value = "Sets the date deleted on the project", 
			httpMethod = "DELETE", notes = "Sets the date deleted on the project (if not already deleted)", 
			response = Response.class
	)		
	public String deleteProject(
			@ApiParam(value = "Project Slug",  required = true)
			@PathVariable("projectslug") String projectslug) {
		
		return "";
		
	}
	
	
	@RequestMapping(value="/projects", method=RequestMethod.POST)
	@ApiResponses(
			value = {@ApiResponse(code = 404, message = "Something went wrong")
	})
	@ApiOperation(
			value = "Creates/updates the Project for the tenant", 
			httpMethod = "POST", notes = "Accepts a JSON document and creates/updates the Project for the tenant", 
			response = Response.class
	)
	public String updateProject(HttpServletRequest request) {
		
		return "";
		
	}
}
