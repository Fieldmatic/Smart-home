package com.bsep.smart.home.cronjobs;

import com.bsep.smart.home.beans.DeviceInfo;
import com.bsep.smart.home.model.Device;
import com.bsep.smart.home.model.Log;
import com.bsep.smart.home.mongorepository.LogRepository;
import com.bsep.smart.home.repository.DeviceRepository;
import com.bsep.smart.home.services.device.GetDeviceMessage;
import com.bsep.smart.home.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class DeviceMessageCronJob {
    private final DeviceInfo deviceInfo;
    private final DeviceRepository deviceRepository;
    private final GetDeviceMessage getDeviceMessage;
    private final LogRepository logRepository;

    @Scheduled(fixedRate = 60000)
    public void logMessagesForDevices() {
        for (Device device : deviceInfo.getReadPeriods().keySet()) {
            if (isReadPeriodElapsed(device) && Util.getRandomBoolean()) {
                logMessageForDevice(device);
                resetLastLogTime(device);
            }
        }
    }

    private boolean isReadPeriodElapsed(Device device) {
        long readPeriod = deviceInfo.getReadPeriods().get(device);
        return Objects.isNull(device.getLastLogged()) || Duration.between(device.getLastLogged(), LocalDateTime.now()).toMinutes() >= readPeriod;
    }

    public void logMessageForDevice(Device device) {
        String message = getDeviceMessage.execute(device);
        Log log = getLog(device, message);
        logRepository.save(log);
        device.setActivated(!device.getActivated());
        deviceRepository.save(device);
    }

    private static Log getLog(Device device, String message) {
        return Log.builder()
                .message(message)
                .deviceId(String.valueOf(device.getId()))
                .createdAt(LocalDateTime.now())
                .propertyId(String.valueOf(device.getProperty().getId()))
                .build();
    }

    public void resetLastLogTime(Device device) {
        device.setLastLogged(LocalDateTime.now());
        deviceRepository.save(device);
    }

}
