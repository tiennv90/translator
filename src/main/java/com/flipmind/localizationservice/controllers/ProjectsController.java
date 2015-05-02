package com.flipmind.localizationservice.controllers;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.flipmind.localizationservice.GlobalVariable;
import com.flipmind.localizationservice.models.Project;
import com.flipmind.localizationservice.models.Tenant;
import com.flipmind.localizationservice.models.json.JSONMessage;
import com.flipmind.localizationservice.models.json.JSONProject;
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
@RequestMapping("/api/1.0")
@Api(value = "Project", description = "Mange projects")
public class ProjectsController {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private TenantRepository tenantRepository;

	
	@RequestMapping(value="/projects", method=RequestMethod.GET)
	@ResponseBody
	@ApiResponses(
			value = {@ApiResponse(code = 404, message = "Something went wrong")
	})
	@ApiOperation(
			value = "Returns all the projects for the tenant", 
			httpMethod = "GET", notes = "Returns all the projects for the tenant", 
			response = Response.class
	)
	
	public ResponseEntity<String> getProjects(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			String apiKey = request.getHeader(GlobalVariable.API_KEY);
			
			List<Tenant> tenants =  tenantRepository.findByApiKey(apiKey);
			
			if (tenants != null && !tenants.isEmpty()) {
				
				List<Project> projects = projectRepository.findByTenant(tenants.get(0));
			
				HttpHeaders headers = new HttpHeaders();
				headers.add("Content-Type", "application/json; charset=utf-8");
				
				return new ResponseEntity<String>(new JSONSerializer().exclude("*.class", "tenant").serialize(projects), headers, HttpStatus.OK) ;
				
			} else {
				request.getRequestDispatcher(GlobalVariable.NO_AUTHENTICATION_PATH).forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher(GlobalVariable.ERROR_PATH).forward(request, response);
		}
		
		return null;
	}
	
	@RequestMapping(value="/projects/{projectslug}", method=RequestMethod.GET)
	@ResponseBody
	@ApiResponses(
			value = {@ApiResponse(code = 404, message = "Something went wrong")
	})
	@ApiOperation(
			value = "Returns the project with the given ID", 
			httpMethod = "GET", notes = "Returns the project with the given ID", 
			response = Response.class
	)	
	public ResponseEntity<String> getProject(
			@ApiParam(value = "Project Slug",  required = true)
			@PathVariable("projectslug") String projectslug, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			if (projectslug != null && !projectslug.isEmpty()) {
			
				List<Project> projects = projectRepository.findBySlug(projectslug);
				
				if (projects != null && !projects.isEmpty()) {
					
					HttpHeaders headers = new HttpHeaders();
					headers.add("Content-Type", "application/json; charset=utf-8");
					
					return new ResponseEntity<String>(new JSONSerializer().exclude("*.class", "tenant").serialize(projects), headers, HttpStatus.OK) ;
					
				} else {
					request.getRequestDispatcher(GlobalVariable.ERROR_PATH).forward(request, response);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher(GlobalVariable.ERROR_PATH).forward(request, response);
		}
		
		return null;
		
	}
	
	@RequestMapping(value="/projects/{projectslug}", method=RequestMethod.DELETE)
	@ResponseBody
	@ApiResponses(
			value = {@ApiResponse(code = 404, message = "Something went wrong")
	})
	@ApiOperation(
			value = "Sets the date deleted on the project", 
			httpMethod = "DELETE", notes = "Sets the date deleted on the project (if not already deleted)", 
			response = Response.class
	)		
	public ResponseEntity<String> deleteProject(
			@ApiParam(value = "Project Slug",  required = true)
			@PathVariable("projectslug") String projectslug, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			if (projectslug != null && !projectslug.isEmpty()) {
			
				List<Project> projects = projectRepository.findBySlug(projectslug);
				
				if (projects != null && !projects.isEmpty()) {
					
					JSONMessage message = new JSONMessage();
					message.setMessage("Sets the date deleted on the project (if not already deleted)");
					
					for (Project project : projects) {
						
						if (project.getDeletedDate() == null || project.getDeletedDate().toString().isEmpty()) {
							
							project.setDeletedDate(new Date());
							
							projectRepository.save(project);
							message.setSuccess(true);
							message.getItems().add("Project: " + projectslug + " was deleted successfully.");
							
						} else {
							message.getItems().add("Project: " + projectslug + " was deleted by someone.");
						}
						
					}
					HttpHeaders headers = new HttpHeaders();
					headers.add("Content-Type", "application/json; charset=utf-8");
					
					return new ResponseEntity<String>(new JSONSerializer().exclude("*.class").deepSerialize(message), headers, HttpStatus.OK) ;
					
				} else {
					request.getRequestDispatcher(GlobalVariable.ERROR_PATH).forward(request, response);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher(GlobalVariable.ERROR_PATH).forward(request, response);
		}
		
		return null;
		
	}
	
	@RequestMapping(value="/projects", method=RequestMethod.POST)
	@ResponseBody
	@ApiResponses(
			value = {@ApiResponse(code = 404, message = "Something went wrong")
	})
	@ApiOperation(
			value = "Creates/updates the Project for the tenant", 
			httpMethod = "POST", notes = "Accepts a JSON document and creates/updates the Project for the tenant", 
			response = Response.class
	)
	public ResponseEntity<String> updateProject(@RequestBody JSONProject project, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String apiKey = request.getHeader(GlobalVariable.API_KEY);
			JSONMessage message = new JSONMessage();
			message.setMessage("Creates/updates project");
			
			if (apiKey != null && !apiKey.isEmpty()) {
				
				List<Tenant> tenants = tenantRepository.findByApiKey(apiKey);
				
				if (project != null && tenants != null && !tenants.isEmpty()) {
					
					boolean isCreateProject = false;
					
					Project actualProject = projectRepository.findOne(project.getId());
					
					//if project existed then updates project
					if (actualProject != null) {
						
						actualProject.setSlug(project.getSlug());
						actualProject.setTitle(project.getTitle());
						
						projectRepository.save(actualProject);
						
					//if project does not exist then create a new project
					} else if (project.getTenant() != null && project.getTenant().getId() > 0){
						 
						actualProject = new Project();
						Tenant tenant = tenantRepository.findOne(project.getTenant().getId());
						
						//if tenant from JSON request does not exist then use tenant from api_key
						if (tenant == null) {
							tenant = tenants.get(0);
						}
						actualProject.setTenant(tenant);
						actualProject.setSlug(project.getSlug());
						actualProject.setTitle(project.getTitle());
						projectRepository.save(actualProject);
						
						isCreateProject = true;
					}
					
					if (isCreateProject) {
						message.getItems().add("Project: " + project.getSlug() + " was created successfully");
					} else if (project.getId() > 0) {
						message.getItems().add("Project: " + project.getSlug() + " was updated successfully");
					} else {
						message.getItems().add("Project: " + project.getSlug() + " Can not be created. The tenant ID is missing");
					}
					
					HttpHeaders headers = new HttpHeaders();
					headers.add("Content-Type", "application/json; charset=utf-8");
					return new ResponseEntity<String>(new JSONSerializer().exclude("*.class").deepSerialize(message), headers, HttpStatus.OK) ;
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			request.getRequestDispatcher(GlobalVariable.ERROR_PATH).forward(request, response);
		}
		return null;
		
	}
}
