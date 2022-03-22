package com.argonaute.checkpoint.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.argonaute.checkpoint.api.model.Argonaute;
import com.argonaute.checkpoint.api.repository.ArgonautesRepository;

@RestController
@RequestMapping("/api/argonautes")
public class ArgonautesController {

	@Autowired
	ArgonautesRepository argonautesRepository;

	@GetMapping
	public List<Argonaute> findAll() {
		return argonautesRepository.findAll();
	}

	@GetMapping("{id}")
	public Optional<Argonaute> findOne(@PathVariable(required = true) Long id) {
		return argonautesRepository.findById(id);
	}
}
