package com.bsep.smart.home.services.registration;

import com.bsep.smart.home.configProperties.CustomProperties;
import com.bsep.smart.home.dto.request.registration.RegistrationRequest;
import com.bsep.smart.home.exception.UserAlreadyExistsException;
import com.bsep.smart.home.model.EmailDetails;
import com.bsep.smart.home.model.Person;
import com.bsep.smart.home.repository.PersonRepository;
import com.bsep.smart.home.services.jwt.JwtGenerateToken;
import com.bsep.smart.home.services.mail.SendMail;
import com.bsep.smart.home.services.role.GetRoleByName;
import com.bsep.smart.home.services.user.UserExistsByEmail;
import com.bsep.smart.home.translations.Codes;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.KeyStoreException;
import java.util.ArrayList;

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
    private final GetRoleByName getRoleByName;

    public Person execute(RegistrationRequest registrationRequest) throws KeyStoreException {
        if (userExistsByEmail.execute(registrationRequest.getEmail())) {
            throw new UserAlreadyExistsException();
        }

        final Person person = Person.builder()
                .email(registrationRequest.getEmail())
                .passwordHash(passwordEncoder.encode(registrationRequest.getPassword()))
                .verified(false)
                .role(getRoleByName.execute(registrationRequest.getRole()))
                .ownedProperties(new ArrayList<>())
                .build();

        final String activateEmailUrl = constructActivateEmailUrl(person.getEmail());
        final EmailDetails emailDetails = new EmailDetails(person.getEmail(), toLocale(Codes.SIGN_UP_ACTIVATION_EMAIL, new String[]{activateEmailUrl}),
                toLocale(Codes.SIGN_UP_ACTIVATION_EMAIL_SUBJECT));
        sendMail.execute(emailDetails);

        return personRepository.save(person);
    }

    private String constructActivateEmailUrl(final String email) {
        final String authToken = jwtGenerateToken.execute(email, customProperties.getJwtActivateEmailTokenExpiration());
        return customProperties.getClientUrl().concat(EMAIL_ACTIVATION_PATH).concat(authToken);
    }
}
