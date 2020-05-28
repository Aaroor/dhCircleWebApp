package com.dhcircle.dhCircleWebApp.model.user;

import java.util.HashMap;
import java.util.Map;

public class Mail {
	private String email;
	private String object;
	private String message;

	public Mail() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
