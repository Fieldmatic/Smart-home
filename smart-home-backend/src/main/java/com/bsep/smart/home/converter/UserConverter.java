package com.bsep.smart.home.converter;

import com.bsep.smart.home.dto.response.UserResponse;
import com.bsep.smart.home.model.BaseEntity;
import com.bsep.smart.home.model.Person;

import java.util.List;

public class UserConverter {

    public static UserResponse toUserResponse(final Person user) {
        return UserResponse.builder()
                .id(user.getId())
                .role(user.getRole().getName())
                .email(user.getEmail())
                .ownedProperties(user.getOwnedProperties().stream().map((BaseEntity::getId)).toList())
                .build();
    }

    public static List<UserResponse> toUsersResponse(final List<Person> users) {
        return users.stream().map(UserConverter::toUserResponse).toList();
    }
}
