package com.bsep.smart.home.services.auth;

import com.bsep.smart.home.model.events.LoginFailedEvent;
import lombok.RequiredArgsConstructor;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FireLoginFailedRule {

    private final KieSession kieSession;

    public void execute(String email) {
        LoginFailedEvent loginFailed = new LoginFailedEvent(email);
        kieSession.insert(loginFailed);
        kieSession.fireAllRules();
    }
}
