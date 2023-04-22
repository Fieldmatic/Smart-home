package com.bsep.smart.home.converter;

import com.bsep.smart.home.dto.response.UserResponse;
import com.bsep.smart.home.model.Person;
import org.modelmapper.ModelMapper;

public class UserConverter {
    private final static ModelMapper modelMapper = new ModelMapper();

    public static UserResponse toUserResponse(final Person user) {
        UserResponse userResponse = modelMapper.map(user, UserResponse.class);
        userResponse.setRole(user.getRole().getName());
        return userResponse;
    }
}
