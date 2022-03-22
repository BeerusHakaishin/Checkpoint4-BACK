package com.argonaute.checkpoint.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.argonaute.checkpoint.api.model.Argonautes;

public interface ArgonautesRepository extends JpaRepository<Argonautes, Long> {
	Optional<Argonautes> findBySurname(String surname);

	Boolean existsBySurname(String surname);
}
