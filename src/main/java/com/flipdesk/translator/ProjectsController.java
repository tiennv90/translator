package com.flipdesk.translator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/1.0/projects")
public class ProjectsController {
	

	@RequestMapping(method=RequestMethod.GET)
	public String index(HttpServletRequest request) {
		
		return "";
		
	}
	
	@RequestMapping(value="/{projectslug}", method=RequestMethod.GET)
	public String getProject(@PathVariable("projectslug") String projectslug, HttpServletRequest request) {
		
		return "";
		
	}
	
	@RequestMapping(value="/{projectslug}", method=RequestMethod.DELETE)
	public String deleteProject(@PathVariable("projectslug") String projectslug) {
		
		return "";
		
	}
	
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	public String updateProject(HttpServletRequest request) {
		
		return "";
		
	}
}
