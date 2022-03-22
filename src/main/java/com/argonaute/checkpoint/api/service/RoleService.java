package com.argonaute.checkpoint.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.argonaute.checkpoint.api.model.ERole;
import com.argonaute.checkpoint.api.model.Role;
import com.argonaute.checkpoint.api.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	RoleRepository roleRepository;

	public Role findByName(ERole eRole) {
		Optional<Role> optRole = roleRepository.findByName(eRole);
		if (optRole.isPresent()) {
			return optRole.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}
}
