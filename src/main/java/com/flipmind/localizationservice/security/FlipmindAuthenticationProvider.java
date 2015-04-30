package com.flipmind.localizationservice.security;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import com.flipmind.localizationservice.GlobalVariable;
import com.flipmind.localizationservice.models.Tenant;
import com.flipmind.localizationservice.repositories.TenantRepository;

public class FlipmindAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private TenantRepository tenantRepository;
	
	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		
		String apiKey = request.getHeader(GlobalVariable.API_KEY);
		System.out.println("================== DEBUGING FlipmindAuthenticationProvider 1========== ");
		boolean isAuthentication = false;
		
		if (apiKey != null && !apiKey.isEmpty()) {
			System.out.println("================== DEBUGING FlipmindAuthenticationProvider 2========== ");
			List<Tenant> tenants = tenantRepository.findByApiKey(apiKey);
			
			if (tenants != null && !tenants.isEmpty()) {
				isAuthentication = true;
			}
			
		}  		
		
		if (isAuthentication) {
			Collection<? extends GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_USER");
			return new AnonymousAuthenticationToken(apiKey, null, authorities);
		}
		
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return false;
	}

}
