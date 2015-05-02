package com.flipmind.localizationservice.models.json;

import java.util.ArrayList;
import java.util.List;

public class JSONDocument {
	
	private long id;
	private JSONProject project;
	private String slug;
	private String title;
	
	private List<JSONLocale> publishedLocales = new ArrayList<JSONLocale>();
	
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
	public JSONProject getProject() {
		return project;
	}
	public void setProject(JSONProject project) {
		this.project = project;
	}
	public List<JSONLocale> getPublishedLocales() {
		return publishedLocales;
	}
	public void setPublishedLocales(List<JSONLocale> publishedLocales) {
		this.publishedLocales = publishedLocales;
	}
}
