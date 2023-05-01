package com.bsep.smart.home.repository;

import com.bsep.smart.home.model.Person;
import com.bsep.smart.home.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PropertyRepository extends JpaRepository<Property, UUID> {

    List<Property> findAllByMembersContaining(Person person);
}
