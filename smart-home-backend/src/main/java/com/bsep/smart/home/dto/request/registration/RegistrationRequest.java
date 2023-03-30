package com.bsep.smart.home.dto.request.registration;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegistrationRequest {
    private String email;
    private String password;
    private String role;
}
