package com.flipmind.localizationservice.security;

import javax.sql.DataSource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

/**
	http://java.dzone.com/articles/secure-rest-services-using
	http://stackoverflow.com/questions/10826293/restful-authentication-via-spring
**/
@Configuration
@EnableWebMvcSecurity
public class FlipmindWebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource datasource;

	@Autowired
	public void configureFlipmind(AuthenticationManagerBuilder auth) {
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		 http.authorizeRequests()
		 	.antMatchers("*/api/*").authenticated()
		 	.anyRequest().permitAll();

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		
		auth.jdbcAuthentication().dataSource(datasource).usersByUsernameQuery("select username, password "
				+ "from tenant where username = ? and password = ?").passwordEncoder(new StandardPasswordEncoder("53crt3t"));
	}
}
