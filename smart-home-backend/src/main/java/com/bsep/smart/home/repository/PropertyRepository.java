package com.bsep.smart.home.repository;

import com.bsep.smart.home.model.Person;
import com.bsep.smart.home.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PropertyRepository extends JpaRepository<Property, UUID> {

    List<Property> findAllByMembersContaining(Person person);

    @Query("SELECT DISTINCT p FROM Property p LEFT JOIN FETCH p.members WHERE p.id = :propertyId")
    Property fetchPropertyWithMembers(@Param("propertyId") UUID propertyId);
}
