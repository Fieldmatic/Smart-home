package com.bsep.smart.home.repository;

import com.bsep.smart.home.model.CertificateType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CertificateTypeRepository extends JpaRepository<CertificateType, UUID> {
}
