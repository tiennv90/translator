package com.flipmind.localizationservice.models.json;


public class JSONLocale {
	private String code;
	private String title;

	public JSONLocale(String code, String title) {
		this.code = code;
		this.title = title;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof JSONLocale))
			return false;
		if (obj == this)
			return true;

		JSONLocale locale = (JSONLocale) obj;

		if (this.code.equals(locale.getCode())
				&& this.title.equals(locale.getTitle())) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return 31 + code != null ? code.hashCode() : 0 + title != null ? title
				.hashCode() : 0;
	}
}
