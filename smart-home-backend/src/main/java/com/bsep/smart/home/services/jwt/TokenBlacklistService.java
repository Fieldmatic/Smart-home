package com.bsep.smart.home.services.jwt;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class TokenBlacklistService {

    private final Cache<String, Boolean> tokenCache = Caffeine.newBuilder().expireAfterWrite(1, TimeUnit.DAYS).build();


    public void addToBlacklist(String token) {
        tokenCache.put(token, true);
    }

    public boolean isBlacklisted(String token) {
        return tokenCache.getIfPresent(token) != null;
    }

}

