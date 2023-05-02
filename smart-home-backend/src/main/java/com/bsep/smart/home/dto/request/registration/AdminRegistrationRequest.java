package com.bsep.smart.home.dto.request.registration;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;

@Data
@NoArgsConstructor
public class AdminRegistrationRequest {
    @Email(message = "Email must be a well-formed email address")
    private String email;
    private String role;
}
