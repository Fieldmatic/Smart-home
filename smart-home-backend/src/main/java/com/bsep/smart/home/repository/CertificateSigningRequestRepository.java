package com.bsep.smart.home.repository;

import com.bsep.smart.home.model.CertificateSigningRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CertificateSigningRequestRepository extends JpaRepository<CertificateSigningRequest, UUID> {
}
