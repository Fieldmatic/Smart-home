package com.bsep.smart.home.repository;

import com.bsep.smart.home.model.CSR;
import com.bsep.smart.home.model.CSRStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CSRRepository extends JpaRepository<CSR, UUID> {
    List<CSR> findAllByStatus(CSRStatus status);

    CSR findByEmail(String email);
}
