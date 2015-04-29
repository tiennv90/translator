package com.flipmind.localizationservice.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
public class IndexController implements ErrorController {

	private static final String PATH = "/error";

	@Override
	public String getErrorPath() {
		return PATH;
	}
	
    @RequestMapping(value = PATH)
    public String error() {
        return "Error handling";
    }

	@RequestMapping(value = "/noauthentication", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> notAuthentication(HttpServletRequest request) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");
		
		return new ResponseEntity<String>("{ message: 'forbidden resource'}",
				headers, HttpStatus.FORBIDDEN);
	}
}