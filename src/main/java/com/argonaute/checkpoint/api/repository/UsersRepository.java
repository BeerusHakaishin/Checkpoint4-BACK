package com.argonaute.checkpoint.api.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.argonaute.checkpoint.api.model.Role;
import com.argonaute.checkpoint.api.model.User;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);

	Boolean existsByUsername(String username);

	List<User> findDistinctAllByRolesIn(Optional<Set<Role>> roles);
}
