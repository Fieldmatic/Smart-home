package com.bsep.smart.home.services.certificate;

import com.bsep.smart.home.model.Extension;
import com.bsep.smart.home.repository.ExtensionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetCertificateExtensions {
    private final ExtensionRepository extensionRepository;

    public List<Extension> execute() {
        return extensionRepository.findAll();
    }
}
