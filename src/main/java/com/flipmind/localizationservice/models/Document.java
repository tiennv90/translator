package com.flipmind.localizationservice.models;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "document")
public class Document extends AbstractTimestampEntity {

	@Id
	@GeneratedValue
	@Column(name = "document_id", nullable = false)
	private long id;

	@Column(length = 100, nullable = false)
	private String slug;

	@Column(length = 100, nullable = false)
	private String title;

	@Column(length = 5000, nullable = false)
	private String potContent;
	
	@ManyToOne
	@JoinColumn(name = "project_id", insertable = false, updatable = false, nullable = false)
	private Project project;
	
	@OneToMany(mappedBy = "document")
	private List<Translation> translations;

	@OneToMany(mappedBy = "document")
	private List<DocumentString> documentStrings;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPotContent() {
		return potContent;
	}

	public void setPotContent(String potContent) {
		this.potContent = potContent;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public List<Translation> getTranslations() {
		return translations;
	}

	public void setTranslations(List<Translation> translations) {
		this.translations = translations;
	}
	
}
