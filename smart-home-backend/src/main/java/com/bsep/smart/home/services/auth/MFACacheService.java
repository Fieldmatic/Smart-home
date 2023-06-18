package com.bsep.smart.home.services.auth;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class MFACacheService {

    private final Cache<String, String> pinCache = Caffeine.newBuilder().expireAfterWrite(2, TimeUnit.MINUTES).build();


    public void setUserPin(String username, String pin) {
        pinCache.put(username, pin);
    }

    public String getUserPin(String username) {
        return pinCache.getIfPresent(username);
    }

}
