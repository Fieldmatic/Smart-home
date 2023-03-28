package com.bsep.smart.home.services.mail;

import com.bsep.smart.home.exception.UserNotFoundException;
import com.bsep.smart.home.model.Person;
import com.bsep.smart.home.services.jwt.JwtValidateAndGetUsername;
import com.bsep.smart.home.services.user.GetUserByEmail;
import com.bsep.smart.home.services.user.SaveUser;
import com.bsep.smart.home.services.user.UserExistsByEmail;
import com.bsep.smart.home.translations.Codes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.bsep.smart.home.translations.Translator.toLocale;

@Service
@RequiredArgsConstructor
public class ActivateEmail {
    private final JwtValidateAndGetUsername jwtValidateAndGetUsername;

    private final GetUserByEmail getUserByEmail;

    private final UserExistsByEmail userExistsByEmail;

    private final SaveUser saveUser;

    public String execute(String token) {
        final String email = jwtValidateAndGetUsername.execute(token);
        if (!userExistsByEmail.execute(email)) {
            throw new UserNotFoundException();
        }
        final Person person = getUserByEmail.execute(email);
        person.setVerified(true);
        saveUser.execute(person);
        return toLocale(Codes.EMAIL_ACTIVATION_SUCCESS);
    }
}
