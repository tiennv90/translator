package com.flipmind.localizationservice.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.flipmind.localizationservice.GlobalVariable;

import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RestController
public class IndexController implements ErrorController {

	@Override
	public String getErrorPath() {
		return GlobalVariable.ERROR_PATH;
	}
	
    @RequestMapping(value = "/error", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> error() {
		return new ResponseEntity<String>("{ error :  { code : '404', message: 'something went wrong' } }"
				, HttpStatus.FORBIDDEN);
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