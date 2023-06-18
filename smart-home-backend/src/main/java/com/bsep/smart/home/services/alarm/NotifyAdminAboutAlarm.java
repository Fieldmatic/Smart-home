package com.bsep.smart.home.services.alarm;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotifyAdminAboutAlarm {

    private final SimpMessagingTemplate simpMessagingTemplate;

    public void execute(String message) {
        simpMessagingTemplate.convertAndSend("/topic/admin", message);
    }
}
