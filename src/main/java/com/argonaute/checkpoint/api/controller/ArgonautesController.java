package com.argonaute.checkpoint.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.argonaute.checkpoint.api.dto.ArgonautesDto;
import com.argonaute.checkpoint.api.model.Argonautes;
import com.argonaute.checkpoint.api.repository.ArgonautesRepository;

@RestController
@RequestMapping("/api/argonautes")
public class ArgonautesController {

	@Autowired
	ArgonautesRepository argonautesRepository;

	@GetMapping
	public List<Argonautes> findAll() {
		return argonautesRepository.findAll();
	}

	@GetMapping("{id}")
	public Optional<Argonautes> findOne(@PathVariable(required = true) Long id) {
		return argonautesRepository.findById(id);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable(required = true) Long id) {
		argonautesRepository.deleteById(id);
	}

	// create type
	@PostMapping
	public Argonautes createArgonaute(@Valid @RequestBody ArgonautesDto argonautesDto) {
		Argonautes argonautes = new Argonautes();
		argonautes.setSurname(argonautesDto.getSurname());
		argonautes.setDob(argonautesDto.getDob());
		argonautes.setDescription(argonautesDto.getDescription());
		return argonautesRepository.save(argonautes);
	}
}
