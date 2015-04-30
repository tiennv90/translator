package com.flipmind.localizationservice.security;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.flipmind.localizationservice.GlobalVariable;
import com.flipmind.localizationservice.models.Tenant;
import com.flipmind.localizationservice.repositories.TenantRepository;

@Service
public class FlipmindUserDetailsService implements UserDetailsService {

	@Autowired HttpServletRequest request;
	
	@Autowired
	private TenantRepository tenantRepository;
	
	@Override
	public UserDetails loadUserByUsername(String arg0)
			throws UsernameNotFoundException {
		
		String apiKey = request.getHeader(GlobalVariable.API_KEY);
		System.out.println("================== DEBUGING FlipmindUserDetailsService 1========== ");
		boolean isAuthentication = false;
		
		if (apiKey != null && !apiKey.isEmpty()) {
			System.out.println("================== DEBUGING FlipmindUserDetailsService 2========== ");
			List<Tenant> tenants = tenantRepository.findByApiKey(apiKey);
			
			if (tenants != null && !tenants.isEmpty()) {
				isAuthentication = true;
			}
			
		}  		
		
		if (isAuthentication) {
			Collection<? extends GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_USER");
			return new User("user", "password", authorities);
		}
		
		return new User("user", "password", AuthorityUtils.createAuthorityList("ROLE_GUEST"));
	}

}
