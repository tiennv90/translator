package com.flipmind.localizationservice.models.json;

import com.flipmind.localizationservice.models.Status;
import com.flipmind.localizationservice.models.Translation;

public class JSONTranslation {
	
	private Translation translation;
	private JSONDocument document;
	private String locale;
	private Status status;
	private String activeFrom;
	
	public Translation getTranslation() {
		return translation;
	}
	public void setTranslation(Translation translation) {
		this.translation = translation;
	}
	public JSONDocument getDocument() {
		return document;
	}
	public void setDocument(JSONDocument document) {
		this.document = document;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getActiveFrom() {
		return activeFrom;
	}
	public void setActiveFrom(String activeFrom) {
		this.activeFrom = activeFrom;
	}
	
}
