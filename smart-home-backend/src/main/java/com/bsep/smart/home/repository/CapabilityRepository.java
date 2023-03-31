package com.bsep.smart.home.repository;

import com.bsep.smart.home.model.Capabilities;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CapabilityRepository extends JpaRepository<Capabilities, UUID> {
}
