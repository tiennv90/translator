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
@Table(name = "project")
public class Project extends AbstractTimestampEntity {

	@Id
	@GeneratedValue
	@Column(name = "project_id", nullable = false)
	private long id;

	@Column(length = 100, nullable = false)
	private String slug;

	@Column(length = 100, nullable = false)
	private String title;

	@ManyToOne
	@JoinColumn(name = "tenant_id", nullable = false)
	private Tenant tenant;
	
	@OneToMany(mappedBy = "project")
	private List<Document> documents;
	
	public Project() {}
	
	public Project(long id) {
		this.id = id;
	}

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

	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}

	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

}
