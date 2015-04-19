package com.flipdesk.translator.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


public class ProjectsController extends BaseController {
	

	@RequestMapping(value="/projects", method=RequestMethod.GET)
	public String index(HttpServletRequest request) {
		
		return "";
		
	}
	
	@RequestMapping(value="/projects/{projectslug}", method=RequestMethod.GET)
	public String getProject(@PathVariable("projectslug") String projectslug, HttpServletRequest request) {
		
		return "";
		
	}
	
	@RequestMapping(value="/projects/{projectslug}", method=RequestMethod.DELETE)
	public String deleteProject(@PathVariable("projectslug") String projectslug) {
		
		return "";
		
	}
	
	
	@RequestMapping(value="/projects/", method=RequestMethod.POST)
	public String updateProject(HttpServletRequest request) {
		
		return "";
		
	}
}
