package com.flipmind.localizationservice.models.json;

import java.util.ArrayList;
import java.util.List;

import com.flipmind.localizationservice.models.Locale;
import com.flipmind.localizationservice.models.Project;

public class JSONDocument {
	
	private long id;
	private Project project;
	private String slug;
	private String title;
	
	private List<Locale> publishedLocales = new ArrayList<Locale>();
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public String getSlug() {
		return slug;
	}
	public void setSlug(String slug) {
		this.slug = slug;
	}
	public List<Locale> getPublishedLocales() {
		return publishedLocales;
	}
	public void setPublishedLocales(List<Locale> publishedLocales) {
		this.publishedLocales = publishedLocales;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
		
}
