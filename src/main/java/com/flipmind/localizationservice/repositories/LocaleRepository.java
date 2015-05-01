package com.flipmind.localizationservice.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.flipmind.localizationservice.models.Locale;

public interface LocaleRepository extends CrudRepository<Locale, Long> {
	List<Locale> findByLocaleCode(String code);
}
