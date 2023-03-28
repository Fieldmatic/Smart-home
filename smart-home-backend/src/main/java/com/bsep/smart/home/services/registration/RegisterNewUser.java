package com.bsep.smart.home.services.registration;

import com.bsep.smart.home.configProperties.CustomProperties;
import com.bsep.smart.home.dto.request.registration.RegistrationRequest;
import com.bsep.smart.home.exception.UserAlreadyExistsException;
import com.bsep.smart.home.model.EmailDetails;
import com.bsep.smart.home.model.Person;
import com.bsep.smart.home.repository.PersonRepository;
import com.bsep.smart.home.services.jwt.JwtGenerateToken;
import com.bsep.smart.home.services.mail.SendMail;
import com.bsep.smart.home.services.user.UserExistsByEmail;
import com.bsep.smart.home.translations.Codes;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.KeyStoreException;

import static com.bsep.smart.home.constants.LinkConstants.EMAIL_ACTIVATION_PATH;
import static com.bsep.smart.home.translations.Translator.toLocale;

@Service
@RequiredArgsConstructor
public class RegisterNewUser {

    private final UserExistsByEmail userExistsByEmail;

    private final PasswordEncoder passwordEncoder;
    private final PersonRepository personRepository;
    private final JwtGenerateToken jwtGenerateToken;
    private final CustomProperties customProperties;
    private final SendMail sendMail;

    public Person execute(RegistrationRequest registrationRequest) throws KeyStoreException {
        if (userExistsByEmail.execute(registrationRequest.getEmail())) {
            throw new UserAlreadyExistsException();
        }

        final Person person = Person.builder()
                .email(registrationRequest.getEmail())
                .name(registrationRequest.getName())
                .surname(registrationRequest.getSurname())
                .passwordHash(passwordEncoder.encode(registrationRequest.getPassword()))
                .verified(false)
                .build();

        final String activateEmailUrl = constructActivateEmailUrl(person.getEmail());
        final EmailDetails emailDetails = new EmailDetails(person.getEmail(), toLocale(Codes.SIGN_UP_ACTIVATION_EMAIL, new String[]{activateEmailUrl}),
                toLocale(Codes.SIGN_UP_ACTIVATION_EMAIL_SUBJECT));
        sendMail.execute(emailDetails);

        return personRepository.save(person);
    }

    private String constructActivateEmailUrl(final String passengerEmail) {
        final String authToken = jwtGenerateToken.execute(passengerEmail, customProperties.getJwtActivateEmailTokenExpiration());
        return customProperties.getClientUrl().concat(EMAIL_ACTIVATION_PATH).concat(authToken);
    }
}
