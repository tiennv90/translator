package com.flipmind.localizationservice.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.util.AntPathRequestMatcher;

/**
 * http://java.dzone.com/articles/secure-rest-services-using
 * http://stackoverflow.com/questions/10826293/restful-authentication-via-spring
 * http://jaxenter.com/rest-api-spring-java-8-112289.html
 **/
@Configuration
@EnableWebMvcSecurity
public class FlipmindWebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static final String LOGIN_PATH = "/login";
	private static final String USERNAME = "user";
	private static final String PASSWORD = "password";
	
	// @Autowired
	// private DataSource datasource;

	@Autowired
	private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

	@Autowired
	private FlipmindUserDetailsService userDetailsService;
	
	@Autowired
	private AuthSuccessHandler authSuccessHandler;
	
	@Autowired
	private AuthFailureHandler authFailureHandler;
	
	@Autowired
	private HttpLogoutSuccessHandler logoutSuccessHandler;
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(new ShaPasswordEncoder());

		return authenticationProvider;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// http.authorizeRequests()
		// .antMatchers("*/api/*").authenticated()
		// .anyRequest().permitAll();
		// http.csrf().disable().exceptionHandling()
		// .authenticationEntryPoint(customAuthenticationEntryPoint).and()
		// .authorizeRequests().antMatchers("*/api/*").fullyAuthenticated();

		// http.authorizeRequests().antMatchers("/","/api/**",
		// "/home").authenticated();

		http.csrf()
				.disable()
				.authenticationProvider(authenticationProvider())
				.exceptionHandling()
				.authenticationEntryPoint(customAuthenticationEntryPoint)
				.and()
				.formLogin()
				.permitAll()
				.loginProcessingUrl(LOGIN_PATH)
				.usernameParameter(USERNAME)
				.passwordParameter(PASSWORD)
				.successHandler(authSuccessHandler)
				.failureHandler(authFailureHandler)
				.and()
				.logout()
				.permitAll()
				.logoutRequestMatcher(
						new AntPathRequestMatcher(LOGIN_PATH, "DELETE"))
				.logoutSuccessHandler(logoutSuccessHandler).and()
				.sessionManagement().maximumSessions(1);

		http.authorizeRequests().antMatchers("/","/api/**").authenticated();

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}
}
