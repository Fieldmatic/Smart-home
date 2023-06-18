package com.bsep.devices.simulation;

import com.bsep.devices.mongorepository.LogRepository;
import com.bsep.devices.repository.DeviceRepository;
import com.bsep.devices.service.GetDeviceMessage;
import com.bsep.devices.util.Util;

import com.bsep.smart.home.model.Device;
import com.bsep.smart.home.model.Log;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DeviceLoggingCronJob {
    private final DeviceRepository deviceRepository;
    private final GetDeviceMessage getDeviceMessage;
    private final LogRepository logRepository;
    Logger logger = LoggerFactory.getLogger(DeviceLoggingCronJob.class);

    @Scheduled(fixedRate = 60000)
    public void logMessagesForDevices() {
        List<Device> devices = deviceRepository.findAll();
        for (Device device : devices) {
            logMessageForDevice(device);
        }
    }
    public void logMessageForDevice(Device device) {
        String message = getDeviceMessage.execute(device);
        logger.info(message);
        Log log = getLog(device, message);
        logRepository.save(log);
        deviceRepository.save(device);
    }

    private static Log getLog(Device device, String message) {
        return Log.builder()
                .message(message)
                .deviceId(String.valueOf(device.getId()))
                .createdAt(LocalDateTime.now())
                .propertyId(String.valueOf(device.getProperty().getId()))
                .processed(false)
                .build();
    }

}
