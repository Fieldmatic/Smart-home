package com.bsep.smart.home.dto.request.certificate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCertificateRequest {
    List<CapabilityRequest> capabilities;
    Date start;
    Date end;
    @NotBlank
    UUID csrId;
    @NotBlank
    String serialNumber;
    @NotBlank
    String caAlias;
}
