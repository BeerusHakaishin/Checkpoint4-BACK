package com.argonaute.checkpoint.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.argonaute.checkpoint.api.model.Argonaute;

public interface ArgonautesRepository extends JpaRepository<Argonaute, Long> {
	Optional<Argonaute> findByname(String name);

	Boolean existsByName(String name);
}
