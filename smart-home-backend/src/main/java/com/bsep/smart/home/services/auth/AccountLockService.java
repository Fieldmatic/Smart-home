package com.bsep.smart.home.services.auth;

import com.bsep.smart.home.filter.AuthTokenFilter;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountLockService {
    private final Cache<String, Integer> failedAttemptsCache = Caffeine.newBuilder().expireAfterWrite(3, TimeUnit.MINUTES).build();
    private final Logger logger = LoggerFactory.getLogger(AccountLockService.class);

    public void increaseFailedAttempts(String username) {
        if (!Objects.isNull(failedAttemptsCache.getIfPresent(username))) {
            failedAttemptsCache.put(username, Objects.requireNonNull(failedAttemptsCache.getIfPresent(username)) + 1);
            if (Objects.requireNonNull(failedAttemptsCache.getIfPresent(username)) >= 3) {
                logger.info("User " + username + " has been locked for 3 minutes because of failed attempts.");
            }
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
