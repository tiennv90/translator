package com.flipdesk.translator.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TenantController {
	
    @RequestMapping(value="/greeting", method=RequestMethod.GET)
    public String greeting() {
        return "hello world";
    }
    
}
