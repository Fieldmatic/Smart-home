package com.bsep.smart.home.services.alarm;

import com.bsep.smart.home.model.Device;
import com.bsep.smart.home.model.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotifyUserAboutAlarm {

    private final SimpMessagingTemplate simpMessagingTemplate;

    public void execute(Device device, String message) {
        for (Person person : device.getProperty().getMembers()) {
            simpMessagingTemplate.convertAndSend("/topic/user/" + person.getEmail(), message);
        }
    }
}
