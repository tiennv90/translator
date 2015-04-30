package com.flipmind.localizationservice.security;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.flipmind.localizationservice.GlobalVariable;
import com.flipmind.localizationservice.models.Tenant;
import com.flipmind.localizationservice.repositories.TenantRepository;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

//	@Autowired
//	private TenantRepository tenantRepository;
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException auth) throws IOException, ServletException {
		System.out.println("================== DEBUGING CustomAuthenticationEntryPoint ========== ");
		System.out.println(auth.getMessage());
		request.getRequestDispatcher("/noauthentication").forward(request, response);
//		String apiKey = request.getHeader(GlobalVariable.API_KEY);
//		
//		boolean isAuthentication = false;
//		
//		if (apiKey != null && !apiKey.isEmpty()) {
//			System.out.println("================== DEBUGING CustomAuthenticationEntryPoint ========== ");
//			List<Tenant> tenants = tenantRepository.findByApiKey(apiKey);
//			
//			if (tenants != null && !tenants.isEmpty()) {
//				isAuthentication = true;
//			}
//			
//		}  
//		
//		if (isAuthentication == false) {
//			request.getRequestDispatcher("/noauthentication").forward(request, response);
//		} else {
//			request.getRequestDispatcher(request.getRequestURI()).forward(request, response);
//		}
	}


}
