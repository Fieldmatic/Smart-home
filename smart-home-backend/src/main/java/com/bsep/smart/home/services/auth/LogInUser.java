package com.bsep.smart.home.services.auth;

import com.bsep.smart.home.configProperties.CustomProperties;
import com.bsep.smart.home.exception.EmailNotVerifiedException;
import com.bsep.smart.home.model.Person;
import com.bsep.smart.home.model.UserCertificateStatus;
import com.bsep.smart.home.services.jwt.JwtGenerateTokenWithCertificateStatus;
import com.bsep.smart.home.services.user.GetUserByEmail;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;

@Service
@RequiredArgsConstructor
public class LogInUser {
    private final AuthenticationManager authenticationManager;

    private final JwtGenerateTokenWithCertificateStatus jwtGenerateToken;

    private final GetUserByEmail getUserByEmail;

    private final CustomProperties customProperties;
    private final GetUserCertificateStatus getUserCertificateStatus;


    public String execute(final String email, final String password) throws CertificateNotYetValidException, UnrecoverableKeyException, CertificateExpiredException, KeyStoreException, NoSuchAlgorithmException {
        final Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );
        } catch (final Exception e) {
            throw new BadCredentialsException("Bad login credentials");
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        final Person user = getUserByEmail.execute(userDetails.getUsername());
        if (!user.isVerified()) throw new EmailNotVerifiedException();
        UserCertificateStatus userCertificateStatus = getUserCertificateStatus.execute(user);
        return jwtGenerateToken.execute(user.getEmail(), userCertificateStatus, user.getRole().getName(), customProperties.getAuthTokenExpirationMilliseconds());
    }
}
