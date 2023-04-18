package com.bsep.smart.home.services.auth;

import com.bsep.smart.home.services.jwt.TokenBlacklistService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogoutUser {

    private final TokenBlacklistService tokenBlacklistService;

    public void execute() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String token = (String) authentication.getCredentials();
        tokenBlacklistService.addToBlacklist(token);
    }
}
