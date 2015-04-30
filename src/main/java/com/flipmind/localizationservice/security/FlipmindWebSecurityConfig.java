package com.flipmind.localizationservice.security;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

/**
 * http://java.dzone.com/articles/secure-rest-services-using
 * http://stackoverflow.com/questions/10826293/restful-authentication-via-spring
 * http://jaxenter.com/rest-api-spring-java-8-112289.html
 **/
@Configuration
@EnableWebMvcSecurity
public class FlipmindWebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().anyRequest().permitAll();
		
	}

}
