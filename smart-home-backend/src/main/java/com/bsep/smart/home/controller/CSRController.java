package com.bsep.smart.home.controller;


import com.bsep.smart.home.dto.request.csr.CSRRequest;
import com.bsep.smart.home.model.CSR;
import com.bsep.smart.home.model.CSRStatus;
import com.bsep.smart.home.services.csr.CreateCSR;
import com.bsep.smart.home.services.csr.GetAllCSRByStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.KeyStoreException;
import java.util.List;

@RestController
@RequestMapping("/csr")
@RequiredArgsConstructor
public class CSRController {
    private final CreateCSR createCSR;

    private final GetAllCSRByStatus findCSRByStatus;

    @PostMapping
    // @HasAnyPermission({Permission.SEND_CSR_REQUEST})
    public void create(@RequestBody CSRRequest csrRequest) throws KeyStoreException {
        createCSR.execute(csrRequest);
    }

    @GetMapping("/pending")
    // @HasAnyPermission({Permission.CSR_MANIPULATION})
    public List<CSR> getPendingRequests() {
        return findCSRByStatus.execute(CSRStatus.PENDING);
    }
}
