package com.bsep.smart.home.services.user;

import com.bsep.smart.home.exception.UserNotFoundException;
import com.bsep.smart.home.repository.PersonRepository;
import com.bsep.smart.home.model.Person;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class GetUserByEmail {
    private final PersonRepository userRepository;

    @Transactional(readOnly = true)
    public Person execute(final String email) {
        return userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
    }
}
