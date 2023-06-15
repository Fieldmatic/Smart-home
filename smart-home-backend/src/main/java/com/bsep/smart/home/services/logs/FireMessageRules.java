package com.bsep.smart.home.services.logs;

import com.bsep.smart.home.model.Device;
import com.bsep.smart.home.model.DeviceType;
import com.bsep.smart.home.model.Log;
import com.bsep.smart.home.model.events.LogEvent;
import com.bsep.smart.home.services.device.GetDeviceById;
import lombok.RequiredArgsConstructor;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FireMessageRules {

    private final KieSession kieSession;
    private final GetDeviceById getDeviceById;

    public void execute(Log log) {
        Device device = getDeviceById.execute(UUID.fromString(log.getDeviceId()));
        LogEvent logEvent = LogEvent.builder().deviceId(String.valueOf(device.getId()))
                .deviceName(device.getName()).deviceType(device.getDeviceType()).build();
        if (device.getDeviceType().equals(DeviceType.THERMOMETER)) {
            logEvent.setValue(device.getValue());
        } else {
            logEvent.setActivated(device.getActivated());
        }
        kieSession.insert(logEvent);
        kieSession.fireAllRules();
    }
}
