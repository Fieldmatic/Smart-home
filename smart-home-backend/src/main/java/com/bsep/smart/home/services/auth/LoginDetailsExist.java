package com.bsep.smart.home.services.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginDetailsExist {
    private final AuthenticationManager authenticationManager;
    private final GeneratePin generatePin;
    private final MFACacheService mfaCacheService;

    public boolean execute(String email, String password) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );
        } catch (final Exception e) {
            throw new BadCredentialsException("Bad login credentials");
        }
        String pin = generatePin.execute();
        mfaCacheService.setUserPin(email, pin);
        //TODO SEND EMAIL WITH PIN
        return true;
    }
}
