package com.bsep.smart.home.dto.response;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    @Email
    @NotEmpty
    String email;

    @NotEmpty
    private String name;

    @NotEmpty
    private String surname;
}
