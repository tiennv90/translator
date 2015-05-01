package com.flipmind.localizationservice.repositories;

import org.springframework.data.repository.CrudRepository;

import com.flipmind.localizationservice.models.Translation;

public interface TranslationRepository extends CrudRepository<Translation, Long> {

}
