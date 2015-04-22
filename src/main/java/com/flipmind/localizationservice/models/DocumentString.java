package com.flipmind.localizationservice.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "documentstring")
public class DocumentString {

	@Id
	@GeneratedValue
	@Column(name = "documentstring_id", nullable = false)
	private long id;

	@Column(name = "string_key", length = 100, nullable = false)
	private String stringKey;

	@Column(name = "string_value", length = 500, nullable = false)
	private String stringValue;

	@Column(name = "description", length = 500, nullable = false)
	private String description;

	@Column(name = "is_public", nullable = false)
	private boolean isPublic;
	
	private int plurals;
	
	@ManyToOne
	@JoinColumn(name = "document_id", insertable = false, updatable = false, nullable = false)
	private Document document;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStringKey() {
		return stringKey;
	}

	public void setStringKey(String stringKey) {
		this.stringKey = stringKey;
	}

	public String getStringValue() {
		return stringValue;
	}

	public void setStringValue(String stringValue) {
		this.stringValue = stringValue;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isPublic() {
		return isPublic;
	}

	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}

	public int getPlurals() {
		return plurals;
	}

	public void setPlurals(int plurals) {
		this.plurals = plurals;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}
	
}
