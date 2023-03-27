package com.bsep.smart.home.services.csr;

import com.bsep.smart.home.model.CertificateSigningRequest;
import com.bsep.smart.home.repository.CertificateSigningRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SaveCSR {

    private final CertificateSigningRequestRepository certificateSigningRequestRepository;

    @Transactional
    public void execute(CertificateSigningRequest csr) {
        certificateSigningRequestRepository.save(csr);
    }
}
