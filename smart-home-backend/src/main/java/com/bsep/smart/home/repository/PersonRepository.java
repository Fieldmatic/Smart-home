package com.bsep.smart.home.repository;

import com.bsep.smart.home.model.Person;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, UUID> {

	Optional<Person> findByEmail(String email);
}
