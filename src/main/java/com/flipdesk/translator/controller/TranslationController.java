package com.flipdesk.translator.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/1.0")
public class TranslationController {

	@RequestMapping(value = "/projects/{projectslug}/{documentslug}", method = RequestMethod.POST)
	public String addTranslation(
			@PathVariable("projectslug") String projectSlug,
			@PathVariable("documentslug") String documentSlug) {

		return "";

	}

	@RequestMapping(value = "/projects/{projectslug}/{documentslug}/{localeCode}/strings", method = RequestMethod.DELETE)
	public String deleteRecordsByLocale(
			@PathVariable("projectslug") String projectSlug,
			@PathVariable("documentslug") String documentSlug,
			@PathVariable("localeCode") String localeCode) {

		return "";

	}
	
	@RequestMapping(value = "/projects/{projectslug}/{documentslug}/{localeCode}/strings", method = RequestMethod.POST)
	public String updateRecordsByLocale(
			@PathVariable("projectslug") String projectSlug,
			@PathVariable("documentslug") String documentSlug,
			@PathVariable("localeCode") String localeCode) {

		return "";

	}
	
	@RequestMapping(value = "/projects/{projectslug}/{documentslug}/{export_format}/{locale}", method = RequestMethod.GET)
	public String exportStringByFormatAndLocale(
			@PathVariable("projectslug") String projectSlug,
			@PathVariable("documentslug") String documentSlug,
			@PathVariable("export_format") String exportFormat,
			@PathVariable("localeCode") String localeCode) {

		return "";

	}	
	
	@RequestMapping(value = "/auth/{appkey}projects/{projectslug}/{documentslug}/{export_format}/{locale}", method = RequestMethod.GET)
	public String authenticationExportStringByFormatAndLocale(
			@PathVariable("appkey") String appKey,
			@PathVariable("projectslug") String projectSlug,
			@PathVariable("documentslug") String documentSlug,
			@PathVariable("export_format") String exportFormat,
			@PathVariable("localeCode") String localeCode) {

		return "";

	}	
}
