package com.bsep.smart.home.services.alarm;

import com.bsep.smart.home.model.Device;
import com.bsep.smart.home.model.Person;
import com.bsep.smart.home.model.Property;
import com.bsep.smart.home.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NotifyUserAboutAlarm {

    private final SimpMessagingTemplate simpMessagingTemplate;
    private final PropertyRepository propertyRepository;

    @Transactional
    public void execute(Device device, String message) {
        Property property = propertyRepository.fetchPropertyWithMembers(device.getProperty().getId());
        for (Person person : property.getMembers()) {
            simpMessagingTemplate.convertAndSend("/topic/user/" + person.getEmail(), message);
        }
    }
}
