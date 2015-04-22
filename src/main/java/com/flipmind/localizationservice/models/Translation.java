package com.flipmind.localizationservice.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "translation")
public class Translation extends AbstractTimestampEntity {

	@Id
	@GeneratedValue
	@Column(name = "translation_id", nullable = false)
	private long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "published_date")
	private Date publishedDate;

	@Column(nullable = false)
	private Status status;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "active_from_date")
	private Date activeFromDate;

	@ManyToOne
	@JoinColumn(name = "document_id", insertable = false, updatable = false, nullable = false)
	private Document document;
	
	@ManyToOne
	@JoinColumn(name="locale_id", insertable = false, updatable = false, nullable = false)
	private Locale locale;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Date getActiveFromDate() {
		return activeFromDate;
	}

	public void setActiveFromDate(Date activeFromDate) {
		this.activeFromDate = activeFromDate;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	
}
