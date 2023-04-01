package com.bsep.smart.home.services.csr;

import com.bsep.smart.home.dto.request.csr.CSRRejectionRequest;
import com.bsep.smart.home.model.CSR;
import com.bsep.smart.home.model.CSRStatus;
import com.bsep.smart.home.model.EmailDetails;
import com.bsep.smart.home.repository.CSRRepository;
import com.bsep.smart.home.services.mail.SendMail;
import com.bsep.smart.home.translations.Codes;
import com.bsep.smart.home.translations.Translator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RejectCSR {
    private final CSRRepository csrRepository;
    private final SendMail sendMail;

    public void execute(CSRRejectionRequest csrRejectionRequest) {
        CSR csr = csrRepository.getReferenceById(csrRejectionRequest.getId());
        if (csr.getStatus().equals(CSRStatus.PENDING)) csr.setStatus(CSRStatus.REJECTED);
        csrRepository.save(csr);
        final EmailDetails emailDetails = new EmailDetails(csr.getEmail(), Translator.toLocale(
                Codes.CSR_REJECTION_MESSAGE, new String[]{csrRejectionRequest.getReason()}), Translator.toLocale(Codes.CSR_REJECTION_SUBJECT));
        sendMail.execute(emailDetails);
    }
}
