package com.argonaute.checkpoint.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.argonaute.checkpoint.api.model.BlackList;

public interface BlackListRepository extends JpaRepository<BlackList, Long> {
	Optional<BlackList> findByToken(String token);
}
