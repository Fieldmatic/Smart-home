package com.bsep.smart.home.controller;


import com.bsep.smart.home.dto.request.csr.CSRRejectionRequest;
import com.bsep.smart.home.dto.request.csr.CSRRequest;
import com.bsep.smart.home.model.CSR;
import com.bsep.smart.home.model.CSRStatus;
import com.bsep.smart.home.model.Permission;
import com.bsep.smart.home.security.HasAnyPermission;
import com.bsep.smart.home.services.csr.CreateCSR;
import com.bsep.smart.home.services.csr.GetAllCSRByStatus;
import com.bsep.smart.home.services.csr.RejectCSR;
import lombok.RequiredArgsConstructor;
import org.bouncycastle.operator.OperatorCreationException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.List;

@RestController
@RequestMapping("/csr")
@RequiredArgsConstructor
public class CSRController {
    private final CreateCSR createCSR;

    private final GetAllCSRByStatus getAllCSRByStatus;
    private final RejectCSR rejectCSR;

    @PostMapping
    @HasAnyPermission({Permission.SEND_CSR_REQUEST})
    public void create(@RequestBody @Valid CSRRequest csrRequest) throws KeyStoreException, CertificateException, NoSuchAlgorithmException, SignatureException, InvalidKeyException, OperatorCreationException, IOException {
        createCSR.execute(csrRequest);
    }

    @GetMapping("/pending")
    @HasAnyPermission({Permission.CSR_MANIPULATION})
    public List<CSR> getPendingRequests() throws UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException {
        return getAllCSRByStatus.execute(CSRStatus.PENDING);
    }

    @PutMapping("/reject")
    @HasAnyPermission({Permission.CSR_MANIPULATION})
    public void rejectCSR(@RequestBody CSRRejectionRequest csrRejectionRequest) {
        rejectCSR.execute(csrRejectionRequest);
    }
}
