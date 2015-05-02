package com.flipmind.localizationservice.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "translatedstring")
public class TranslatedString {
	
	@Id
	@GeneratedValue
	@Column(name = "translatedstring_id", nullable = false)
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "translation_id")
	private Translation translation;
	
	@Column(name = "string_key", nullable = false, length = 100)
	private String key;
	
	@Column(name = "string_value", nullable = false, length = 500)
	private String value;

	private int plurals;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Translation getTranslation() {
		return translation;
	}

	public void setTranslation(Translation translation) {
		this.translation = translation;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getPlurals() {
		return plurals;
	}

	public void setPlurals(int plurals) {
		this.plurals = plurals;
	}
}
