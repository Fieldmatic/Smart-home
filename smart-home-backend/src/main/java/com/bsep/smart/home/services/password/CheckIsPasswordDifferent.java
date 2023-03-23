package com.bsep.smart.home.services.password;

import com.bsep.smart.home.exception.PasswordSameException;
import com.bsep.smart.home.model.Person;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CheckIsPasswordDifferent {
    private final PasswordEncoder passwordEncoder;

    public void execute(String password, Person user) {
        if (passwordEncoder.matches(password, user.getPasswordHash())) throw new PasswordSameException();
    }
}