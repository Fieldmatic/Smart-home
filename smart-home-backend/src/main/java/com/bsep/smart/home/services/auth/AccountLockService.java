package com.bsep.smart.home.services.auth;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class AccountLockService {
    private final Cache<String, Integer> failedAttemptsCache = Caffeine.newBuilder().expireAfterWrite(3, TimeUnit.MINUTES).build();


    public void increaseFailedAttempts(String username) {
        if (!Objects.isNull(failedAttemptsCache.getIfPresent(username))) {
            failedAttemptsCache.put(username, Objects.requireNonNull(failedAttemptsCache.getIfPresent(username)) + 1);
        } else {
            failedAttemptsCache.put(username, 1);
        }
    }

    public boolean isLocked(String username) {
        Integer failedAttempts = failedAttemptsCache.getIfPresent(username);
        if (Objects.isNull(failedAttempts)) return false;
        else return failedAttempts >= 3;
    }
}
