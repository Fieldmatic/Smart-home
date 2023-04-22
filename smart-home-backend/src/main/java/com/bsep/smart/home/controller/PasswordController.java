package com.bsep.smart.home.controller;

import com.bsep.smart.home.dto.request.password.ChangePasswordRequest;
import com.bsep.smart.home.services.password.ChangePassword;
import com.bsep.smart.home.services.password.SendPasswordResetEmail;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;


@RestController
@RequestMapping("/password")
@RequiredArgsConstructor
public class PasswordController {
    private final SendPasswordResetEmail sendPasswordResetEmail;
    private final ChangePassword changePassword;

    @GetMapping("/request-change")
    public String requestPasswordChange(@Email @RequestParam("email") String email) {
        return sendPasswordResetEmail.execute(email);
    }

    @PutMapping("/change")
    public void changePassword(@Valid @RequestBody ChangePasswordRequest changePasswordDTO) {
        changePassword.execute(changePasswordDTO);
    }
}
