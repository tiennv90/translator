package com.flipmind.localizationservice.models.json;

public class JSONTranslation {

	private JSONDocument document;
	private String locale;
	private String status;
	private String activeFrom;

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

	public String getActiveFrom() {
		return activeFrom;
	}

	public void setActiveFrom(String activeFrom) {
		this.activeFrom = activeFrom;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
