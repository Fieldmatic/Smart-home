package com.bsep.smart.home.dto.request.property;

import com.bsep.smart.home.dto.response.UserResponse;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PropertyResponse {
    public UUID uuid;
    public String name;
    public UserResponse owner;
    public String address;
    public List<UserResponse> members;
}
