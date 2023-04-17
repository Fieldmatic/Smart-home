package com.bsep.smart.home.dto.request.registration;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

import static com.bsep.smart.home.util.ValidationPatterns.PASSWORD_PATTERN;

@Data
@NoArgsConstructor
public class RegistrationRequest {
    @Email(message = "Email must be a well-formed email address")
    private String email;
    @Pattern(regexp = PASSWORD_PATTERN, message = "Password must contain at least 1 uppercase letter, 1 lowercase letter, 1 digit, 1 special character, and be at least 8 characters long")
    private String password;
    private String role;
}
