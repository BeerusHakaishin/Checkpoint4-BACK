package com.argonaute.checkpoint.api.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class ArgonautesDto {
	@NotBlank
	@Size(max = 20)
	private String name;

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date dob;

	@NotBlank
	@Size(min = 5)
	private String description;
}
