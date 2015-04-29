package com.flipmind.localizationservice.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.flipmind.localizationservice.models.Tenant;

public interface TenantRepository extends CrudRepository<Tenant, Long> {
	
	List<Tenant> findByApiKey(String apiKey);

}
