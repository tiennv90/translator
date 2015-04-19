package com.flipdesk.translator.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class DocumentController extends BaseController {

	@RequestMapping(value="/{projectslug}/documents", method=RequestMethod.GET)
	public String getAllDocuments(@PathVariable("projectslug") String projectSlug) {
		
		return "";
		
	}
	
	@RequestMapping(value="/{projectslug}/{documentslug}", method=RequestMethod.GET)
	public String getDocument(@PathVariable("projectslug") String projectSlug, @PathVariable("documentslug") String documentSlug) {
		
		return "";
		
	}
	
	@RequestMapping(value="/{projectslug}/{documentslug}", method=RequestMethod.DELETE)
	public String deleteDocument(@PathVariable("projectslug") String projectSlug, @PathVariable("documentslug") String documentSlug) {
		
		return "";
		
	}
	
	@RequestMapping(value="/{projectslug}", method=RequestMethod.POST)
	public String createDocument() {
		return "";
	}
		
	
	@RequestMapping(value="/{projectslug}/{documentslug}/strings?public=true", method=RequestMethod.PUT)
	public String addStringAndDeleteNotIncludedContent(@PathVariable("projectslug") String projectSlug, @PathVariable("documentslug") String documentSlug) {
		
		return "";
		
	}
	
	@RequestMapping(value="/{projectslug}/{documentslug}/strings", method=RequestMethod.POST)
	public String addString(@PathVariable("projectslug") String projectSlug, @PathVariable("documentslug") String documentSlug) {
		
		return "";
		
	}
	
	@RequestMapping(value="/{projectslug}/{documentslug}/strings", method=RequestMethod.DELETE)
	public String deleteRecords(@PathVariable("projectslug") String projectSlug, @PathVariable("documentslug") String documentSlug) {
		
		return "";
		
	}

	
}
