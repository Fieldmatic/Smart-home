package com.bsep.smart.home.repository;

import com.bsep.smart.home.model.Person;
import com.bsep.smart.home.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PersonRepository extends JpaRepository<Person, UUID> {

    Optional<Person> findByEmail(String email);

    List<Person> findAllByRoleNot(Role role);
}
