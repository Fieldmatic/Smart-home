package com.bsep.smart.home.services.auth;

import com.bsep.smart.home.converter.UserConverter;
import com.bsep.smart.home.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetSelf {
    private final GetLoggedInUser getLoggedInUser;

    public UserResponse execute() {
        return UserConverter.toUserResponse(getLoggedInUser.execute());
    }
}
