package com.flipmind.localizationservice.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "locale")
public class Locale {

	@Id
	@GeneratedValue
	@Column(name = "locale_id", nullable = false)
	private long id;

	@Column(name = "plural_forms_expression", nullable = false, length = 200)
	private String pluralFormsExpression;

	@Column(nullable = false, length = 100)
	private String title;

	@Column(name = "locale_code", nullable = false, length = 5)
	private String localeCode;
	
	@OneToMany(mappedBy = "locale")
	private List<Translation> translations;
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPluralFormsExpression() {
		return pluralFormsExpression;
	}

	public void setPluralFormsExpression(String pluralFormsExpression) {
		this.pluralFormsExpression = pluralFormsExpression;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLocaleCode() {
		return localeCode;
	}

	public void setLocaleCode(String localeCode) {
		this.localeCode = localeCode;
	}

	public List<Translation> getTranslations() {
		return translations;
	}

	public void setTranslations(List<Translation> translations) {
		this.translations = translations;
	}
}
