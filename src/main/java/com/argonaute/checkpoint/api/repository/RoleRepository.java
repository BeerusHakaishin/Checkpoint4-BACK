package com.argonaute.checkpoint.api.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.argonaute.checkpoint.api.model.ERole;
import com.argonaute.checkpoint.api.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(ERole name);

	Optional<Set<Role>> findAllByNameIn(Set<ERole> name);
}
