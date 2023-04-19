package com.bsep.smart.home.services.auth;

import com.bsep.smart.home.model.EmailDetails;
import com.bsep.smart.home.services.mail.SendMail;
import com.bsep.smart.home.translations.Codes;
import com.bsep.smart.home.translations.Translator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginDetailsExist {
    private final AuthenticationManager authenticationManager;
    private final GeneratePin generatePin;
    private final MFACacheService mfaCacheService;
    private final SendMail sendMail;

    public boolean execute(String email, String password) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );
        } catch (final Exception e) {
            throw new BadCredentialsException("Bad login credentials");
        }
        String pin = generatePin.execute();
        mfaCacheService.setUserPin(email, pin);
        final EmailDetails emailDetails = new EmailDetails(email, Translator.toLocale(
                Codes.LOGIN_PIN_MESSAGE, new String[]{pin}), Translator.toLocale(Codes.LOGIN_PIN_SUBJECT));
        sendMail.execute(emailDetails);
        return true;
    }
}
