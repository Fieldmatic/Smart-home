package com.bsep.smart.home.interceptor;

import com.bsep.smart.home.model.Person;
import com.bsep.smart.home.model.events.RequestEvent;
import com.bsep.smart.home.services.auth.GetLoggedInUser;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        Person loggedInUser = getLoggedInUser.execute();
        if (loggedInUser != null) {
            RequestEvent requestEvent = new RequestEvent(loggedInUser.getEmail());
            kieSession.insert(requestEvent);
            kieSession.fireAllRules();
        }
        return true;
    }
}
