package com.bsep.smart.home.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    UUID id;
    @Email
    @NotEmpty
    String email;
    String role;
    List<UUID> ownedProperties;
    @JsonIgnore
    List<UUID> accessibleProperties;
}
