package com.flipmind.localizationservice.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/test")
public class TestController {
	

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> index(HttpServletRequest request) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");
		
		return new ResponseEntity<String>("{ message: 'test message'}",
				headers, HttpStatus.OK);
	}
}
