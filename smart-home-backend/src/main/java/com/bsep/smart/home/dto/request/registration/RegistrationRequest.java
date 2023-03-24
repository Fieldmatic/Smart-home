package com.bsep.smart.home.dto.request.registration;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RegistrationRequest {
    private String name;
    private String surname;
    private String email;
    private String password;
}
