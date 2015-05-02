package com.flipmind.localizationservice.models.json;

public class JSONProject {
	
	private long id;
	private JSONTenant tenant;
	private String title;
	private String slug;
	
	public JSONProject() {
		
	}
	
	public JSONProject(long id) {
		this.id = id;
	}
	
	public JSONTenant getTenant() {
		return tenant;
	}
	public void setTenant(JSONTenant tenant) {
		this.tenant = tenant;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSlug() {
		return slug;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public void setSlug(String slug) {
		this.slug = slug;
	}
	
}
