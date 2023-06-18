package com.bsep.smart.home.dto.request.certificate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static com.bsep.smart.home.util.ValidationPatterns.UUID_PATTERN;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCertificateRequest {
    List<CapabilityRequest> capabilities;
    Date start;
    Date end;
    @Pattern(regexp = UUID_PATTERN, message = "Bad uuid format!")
    String csrId;
    @NotEmpty
    String serialNumber;
    @NotEmpty
    String caAlias;
}
