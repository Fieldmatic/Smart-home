package com.bsep.smart.home.services.password;


import com.bsep.smart.home.exception.PasswordMismatchException;

import org.springframework.stereotype.Service;

@Service
public class CheckPasswordConfirmation {

    public void execute(String password, String passwordConfirmation) {
        if (!(password.equals(passwordConfirmation))) throw new PasswordMismatchException();
    }

}
