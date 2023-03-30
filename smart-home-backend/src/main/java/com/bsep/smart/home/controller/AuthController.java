package com.bsep.smart.home.controller;

import com.bsep.smart.home.dto.request.auth.LoginRequest;
import com.bsep.smart.home.dto.request.registration.RegistrationRequest;
import com.bsep.smart.home.dto.response.AuthTokenResponse;
import com.bsep.smart.home.dto.response.UserResponse;
import com.bsep.smart.home.services.auth.GetSelf;
import com.bsep.smart.home.services.auth.LogInUser;
import com.bsep.smart.home.services.mail.ActivateEmail;
import com.bsep.smart.home.services.registration.RegisterNewUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.KeyStoreException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final LogInUser loginUser;
    private final GetSelf getSelf;
    private final RegisterNewUser registerNewUser;
    private final ActivateEmail activateEmail;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/login")
    public AuthTokenResponse login(@Valid @RequestBody final LoginRequest loginRequest) {
        return loginUser.execute(loginRequest.getEmail(), loginRequest.getPassword());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/self")
    public UserResponse getSelf() {
        return getSelf.execute();
    }

    @PostMapping("/register")
    public void register(@RequestBody RegistrationRequest registrationRequest) throws KeyStoreException {
        registerNewUser.execute(registrationRequest);
    }

    @PutMapping("/activateEmail/{token}")
    public void activateEmail(@PathVariable("token") final String token) {
        activateEmail.execute(token);
    }

}
