package com.argonaute.checkpoint.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.argonaute.checkpoint.api.model.User;
import com.argonaute.checkpoint.api.repository.UsersRepository;

@RestController
@CrossOrigin
@RequestMapping("api/users")
public class UsersController {
	@Autowired
	UsersRepository userRepository;

	// find all
	@GetMapping
	public List<User> getAll() {
		return userRepository.findAll();
	}

	// find by id
	@GetMapping("{id}")
	public User get(@PathVariable(required = true) Long id) {
		Optional<User> optUser = userRepository.findById(id);
		if (optUser.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		return optUser.get();
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable(required = true) Long id) {
		userRepository.deleteById(id);
	}
}
