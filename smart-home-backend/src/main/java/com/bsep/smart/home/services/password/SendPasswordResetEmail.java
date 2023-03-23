package com.bsep.smart.home.services.password;

import com.bsep.smart.home.configProperties.CustomProperties;
import com.bsep.smart.home.constants.LinkConstants;
import com.bsep.smart.home.translations.Codes;
import com.bsep.smart.home.translations.Translator;
import com.bsep.smart.home.model.EmailDetails;
import com.bsep.smart.home.model.Person;
import com.bsep.smart.home.services.jwt.JwtGenerateToken;
import com.bsep.smart.home.services.mail.SendMail;
import com.bsep.smart.home.services.user.GetUserByEmail;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.bsep.smart.home.translations.Translator.toLocale;

@Service
@RequiredArgsConstructor
public class SendPasswordResetEmail {
    private final SendMail sendMail;
    private final JwtGenerateToken jwtGenerateToken;
    private final CustomProperties customProperties;
    private final GetUserByEmail getUserByEmail;

    @Transactional(readOnly = true)
    public String execute(final String email) {
        final Person user = getUserByEmail.execute(email);
        //if (!Objects.equals(user.get, UserStatus.ACTIVE)) throw new UserNotActiveException();
        final String resetPasswordUrl = constructResetPasswordUrl(user);
        final EmailDetails emailDetails = new EmailDetails(email, Translator.toLocale(
			Codes.PASSWORD_RESET_LINK, new String[]{resetPasswordUrl}), Translator.toLocale(Codes.PASSWORD_RESET_EMAIL_SUBJECT));
        sendMail.execute(emailDetails);
        return Translator.toLocale(Codes.PASSWORD_RESET_REQUEST_SUCCESS);
    }

    private String constructResetPasswordUrl(final Person user) {
        final String authToken = jwtGenerateToken.execute(user.getEmail(), customProperties.getJwtForgotPasswordExpiration());
        return customProperties.getClientUrl().concat(LinkConstants.PASSWORD_RESET_PATH).concat(authToken);
    }
}
