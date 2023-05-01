package com.bsep.smart.home.dto.request.property;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddMemberRequest {
    @org.hibernate.validator.constraints.UUID(message = "User id not valid!")
    private UUID userId;
    @org.hibernate.validator.constraints.UUID(message = "Property id not valid!")
    private UUID propertyId;
}
