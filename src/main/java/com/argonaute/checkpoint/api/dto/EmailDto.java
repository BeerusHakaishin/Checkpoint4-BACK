package com.argonaute.checkpoint.api.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.lang.Nullable;

public class EmailDto {

	@NotBlank
	@Size(min = 2, max = 15)
	private String name;

	@NotBlank
	@Size(min = 4, max = 255)
	private String object;

	@Email
	private String email;

	@NotBlank
	private String message;

	@Nullable
	@Size(max = 0)
	private String areyouarobot;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
