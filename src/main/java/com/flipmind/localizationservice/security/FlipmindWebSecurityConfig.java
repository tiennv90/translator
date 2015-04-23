package com.flipmind.localizationservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

@Configuration
@EnableWebMvcSecurity
public class FlipmindWebSecurityConfig extends WebSecurityConfigurerAdapter {

	
	@Autowired
	public void configureFlipmind(AuthenticationManagerBuilder auth) {
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		//http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
		

	}
	
	
}
