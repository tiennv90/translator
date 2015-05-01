package com.flipmind.localizationservice.models.json;

import java.util.ArrayList;
import java.util.List;

public class JSONMessage {
	
	private boolean success;
	private List<String> items = new ArrayList<String>();
	private String message;
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public List<String> getItems() {
		return items;
	}
	public void setItems(List<String> items) {
		this.items = items;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
