package com.bsep.smart.home.dto.request.certificate;

import com.bsep.smart.home.model.Extension;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCertificateRequest {
    String certificateType;
    List<Extension> extensions;
    Date start;
    Date end;
    UUID csrId;
    String serialNumber;
    String caAlias;
}
