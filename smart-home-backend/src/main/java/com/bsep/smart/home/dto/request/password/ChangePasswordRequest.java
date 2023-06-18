package com.bsep.smart.home.dto.request.password;

import com.bsep.smart.home.validation.CommonPasswordValidation;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

import static com.bsep.smart.home.util.ValidationPatterns.PASSWORD_PATTERN;

@Data
@NoArgsConstructor
public class ChangePasswordRequest {
    @CommonPasswordValidation
    @Pattern(regexp = PASSWORD_PATTERN, message = "Password must contain at least 1 uppercase letter, 1 lowercase letter, 1 digit, 1 special character, and be at least 12 characters long")
    private String newPassword;
    @CommonPasswordValidation
    @Pattern(regexp = PASSWORD_PATTERN, message = "Password must contain at least 1 uppercase letter, 1 lowercase letter, 1 digit, 1 special character, and be at least 12 characters long")
    private String newPasswordConfirmation;
    private String authToken;
}
