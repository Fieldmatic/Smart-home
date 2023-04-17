package com.bsep.smart.home.services.fingerprint;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetFingerprintFromCookie {

    public String execute(HttpServletRequest request) {
        String userFingerprint = null;
        if (request.getCookies() != null && request.getCookies().length > 0) {
            List<Cookie> cookies = Arrays.stream(request.getCookies()).toList();
            Optional<Cookie> cookie = cookies.stream().filter(c -> "Fingerprint".equals(c.getName())).findFirst();
            if (cookie.isPresent()) {
                userFingerprint = cookie.get().getValue();
            }
        }
        return userFingerprint;
    }
}
