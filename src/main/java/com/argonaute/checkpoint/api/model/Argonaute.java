package com.argonaute.checkpoint.api.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Argonaute {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(min=2, max=255)
	private String name;
	
	@Column(columnDefinition = "TEXT")
	@NotBlank
	@Size(min=2)
	private String description;
	
	@Column(name = "dob")
	@JsonFormat(pattern = "dd/MM/YYYY")
	private Date dob;
}
