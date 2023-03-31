package com.bsep.smart.home.repository;

import com.bsep.smart.home.model.Extension;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ExtensionRepository extends JpaRepository<Extension, UUID> {
}
