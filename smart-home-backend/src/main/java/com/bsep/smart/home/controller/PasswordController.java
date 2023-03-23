package com.bsep.smart.home.controller;

import com.bsep.smart.home.dto.request.password.ChangePasswordRequest;
import com.bsep.smart.home.services.password.ChangePassword;
import com.bsep.smart.home.services.password.SendPasswordResetEmail;

import javax.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


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
    public void changePassword(@RequestBody ChangePasswordRequest changePasswordDTO) {
        changePassword.execute(changePasswordDTO);
    }
}
