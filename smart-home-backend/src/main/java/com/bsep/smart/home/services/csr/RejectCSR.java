package com.bsep.smart.home.services.csr;

import com.bsep.smart.home.model.CSR;
import com.bsep.smart.home.model.CSRStatus;
import com.bsep.smart.home.repository.CSRRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RejectCSR {
    private final CSRRepository csrRepository;

    public void execute(UUID id) {
        CSR csr = csrRepository.getReferenceById(id);
        if (csr.getStatus().equals(CSRStatus.PENDING)) csr.setStatus(CSRStatus.REJECTED);
        csrRepository.save(csr);
    }
}
