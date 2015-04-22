package com.flipmind.localizationservice.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "exportformat")
public class ExportFormat {

	@Id
	@GeneratedValue
	@Column(name = "exportformat_id", nullable = false)
	private long id;

	@Column(length = 100)
	private String title;

	@Column(name = "classname", length = 45)
	private String className;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	
}
