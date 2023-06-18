package com.bsep.smart.home.services.certificate;

import com.bsep.smart.home.model.CertificateType;
import com.bsep.smart.home.repository.CertificateTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetCertificateTypes {
    private final CertificateTypeRepository certificateTypeRepository;

    public List<CertificateType> execute() {
        return certificateTypeRepository.findAll();
    }
}
