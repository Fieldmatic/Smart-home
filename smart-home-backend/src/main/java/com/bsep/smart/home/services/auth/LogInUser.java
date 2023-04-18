package com.bsep.smart.home.services.auth;

import com.bsep.smart.home.configProperties.CustomProperties;
import com.bsep.smart.home.dto.request.auth.LoginRequest;
import com.bsep.smart.home.dto.response.AuthTokenResponse;
import com.bsep.smart.home.exception.EmailNotVerifiedException;
import com.bsep.smart.home.exception.InvalidPinException;
import com.bsep.smart.home.model.Person;
import com.bsep.smart.home.model.UserCertificateStatus;
import com.bsep.smart.home.services.fingerprint.GenerateFingerprint;
import com.bsep.smart.home.services.jwt.JwtGenerateTokenWithCertificateStatus;
import com.bsep.smart.home.services.user.GetUserByEmail;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class LogInUser {
    private final AuthenticationManager authenticationManager;

    private final JwtGenerateTokenWithCertificateStatus jwtGenerateToken;

    private final GetUserByEmail getUserByEmail;

    private final CustomProperties customProperties;
    private final GetUserCertificateStatus getUserCertificateStatus;
    private final GenerateFingerprint generateFingerprint;
    private final MFACacheService mfaCacheService;


    public ResponseEntity<AuthTokenResponse> execute(final LoginRequest loginRequest) throws CertificateNotYetValidException, UnrecoverableKeyException, CertificateExpiredException, KeyStoreException, NoSuchAlgorithmException {
        final Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );
        } catch (final Exception e) {
            throw new BadCredentialsException("Bad login credentials");
        }
        String pin = mfaCacheService.getUserPin(loginRequest.getEmail());
        if (Objects.isNull(pin) || !pin.equals(loginRequest.getPin()))
            throw new InvalidPinException();

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        final Person user = getUserByEmail.execute(userDetails.getUsername());
        if (!user.isVerified()) throw new EmailNotVerifiedException();
        UserCertificateStatus userCertificateStatus = getUserCertificateStatus.execute(user);
        String fingerprint = generateFingerprint.execute();
        String authToken = jwtGenerateToken.execute(user.getEmail(), userCertificateStatus, user.getRole().getName(), customProperties.getAuthTokenExpirationMilliseconds(), fingerprint);
        HttpHeaders headers = createHeadersWithFingerprintCookie(fingerprint);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(new AuthTokenResponse(authToken));
    }

    private HttpHeaders createHeadersWithFingerprintCookie(String fingerprint) {
        String cookie = "Fingerprint=" + fingerprint + "; HttpOnly; Path=/";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Set-Cookie", cookie);
        return headers;
    }
}
