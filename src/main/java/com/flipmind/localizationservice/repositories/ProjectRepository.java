package com.flipmind.localizationservice.repositories;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.flipmind.localizationservice.models.Project;
import com.flipmind.localizationservice.models.Tenant;


public interface ProjectRepository extends CrudRepository<Project, Long> {
	
	List<Project> findByTenant(Tenant tenant);
	List<Project> findBySlug(String slug);
}
