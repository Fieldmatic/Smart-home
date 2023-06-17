package com.bsep.smart.home.interceptor;

import com.bsep.smart.home.exception.UnauthorizedException;
import com.bsep.smart.home.model.Person;
import com.bsep.smart.home.model.events.RequestEvent;
import com.bsep.smart.home.services.auth.GetLoggedInUser;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.apache.coyote.Request;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Component
public class RequestInterceptor implements HandlerInterceptor {
    private final GetLoggedInUser getLoggedInUser;
    private final KieSession kieSession;

    @Autowired
    public RequestInterceptor(final GetLoggedInUser getLoggedInUser, final KieSession kieSession) {
        this.getLoggedInUser = getLoggedInUser;
        this.kieSession = kieSession;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        RequestEvent requestEvent = new RequestEvent(request.getRemoteAddr());
        try {
            Person loggedInUser = getLoggedInUser.execute();
            requestEvent = new RequestEvent(request.getRemoteAddr(), loggedInUser.getEmail());
            return true;
        } catch (UnauthorizedException e) {
            return true;
        } finally {
            kieSession.insert(requestEvent);
            kieSession.fireAllRules();
        }
    }
}
