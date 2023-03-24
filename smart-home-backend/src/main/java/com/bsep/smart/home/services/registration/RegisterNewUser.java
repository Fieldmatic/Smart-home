package com.bsep.smart.home.services.registration;

import com.bsep.smart.home.dto.request.registration.RegistrationRequest;
import com.bsep.smart.home.exception.UserAlreadyExistsException;
import com.bsep.smart.home.model.Person;
import com.bsep.smart.home.repository.PersonRepository;
import com.bsep.smart.home.services.keys.CreateKeyPair;
import com.bsep.smart.home.services.user.UserExistsByEmail;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.KeyStoreException;

@Service
@RequiredArgsConstructor
public class RegisterNewUser {

    private final UserExistsByEmail userExistsByEmail;

    private final PasswordEncoder passwordEncoder;
    private final PersonRepository personRepository;

    private final CreateKeyPair createKeyPair;

    public Person execute(RegistrationRequest registrationRequest) throws KeyStoreException {
        if (userExistsByEmail.execute(registrationRequest.getEmail())) {
            throw new UserAlreadyExistsException();
        }

        final Person person = Person.builder()
                .email(registrationRequest.getEmail())
                .name(registrationRequest.getName())
                .surname(registrationRequest.getSurname())
                .passwordHash(passwordEncoder.encode(registrationRequest.getPassword()))
                .build();

        return personRepository.save(person);

    }
}
