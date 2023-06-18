package com.bsep.smart.home.services.user;

import com.bsep.smart.home.repository.PersonRepository;
import com.bsep.smart.home.model.Person;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SaveUser {

    private final PersonRepository userRepository;

    @Transactional(readOnly = false)
    public Person execute(final Person user) {
        return userRepository.save(user);
    }
}
