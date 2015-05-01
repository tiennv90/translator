package com.flipmind.localizationservice.repositories;

import org.springframework.data.repository.CrudRepository;

import com.flipmind.localizationservice.models.Document;

public interface DocumentRepository extends CrudRepository<Document, Long> {

}
