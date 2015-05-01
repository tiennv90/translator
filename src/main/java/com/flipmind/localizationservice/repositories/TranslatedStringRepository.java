package com.flipmind.localizationservice.repositories;

import org.springframework.data.repository.CrudRepository;

import com.flipmind.localizationservice.models.TranslatedString;

public interface TranslatedStringRepository extends CrudRepository<TranslatedString, Long> {

}
