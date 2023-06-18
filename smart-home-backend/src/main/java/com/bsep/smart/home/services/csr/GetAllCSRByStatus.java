package com.bsep.smart.home.services.csr;

import com.bsep.smart.home.model.CSR;
import com.bsep.smart.home.model.CSRStatus;
import com.bsep.smart.home.repository.CSRRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllCSRByStatus {
    private final CSRRepository csrRepository;

    public List<CSR> execute(CSRStatus status) throws UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException {
        return csrRepository.findAllByStatus(status);
    }
}
