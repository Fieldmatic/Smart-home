package com.bsep.smart.home.cronjobs;

import com.bsep.smart.home.beans.DeviceInfo;
import com.bsep.smart.home.model.Device;
import com.bsep.smart.home.model.Log;
import com.bsep.smart.home.mongorepository.LogRepository;
import com.bsep.smart.home.repository.DeviceRepository;
import com.bsep.smart.home.services.device.GetDeviceMessage;
import com.bsep.smart.home.util.Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
@Slf4j
public class DeviceMessageCronJob {
    private final DeviceInfo deviceInfo;
    private final DeviceRepository deviceRepository;
    private final GetDeviceMessage getDeviceMessage;
    private final LogRepository logRepository;
    Logger logger = LoggerFactory.getLogger(DeviceMessageCronJob.class);


    @Scheduled(fixedRate = 10000)
    public void processMessagesForDevices() {
        for (Device device : deviceInfo.getReadPeriods().keySet()) {
            if (isReadPeriodElapsed(device)) {
                processMessages(device);
                resetLastProcessTime(device);
            }
        }
    }

    @Scheduled(fixedRate = 30000)
    public void logMessagesForDevices() {
        for (Device device : deviceInfo.getReadPeriods().keySet()) {
            if (Util.getRandomBoolean()) {
                logMessageForDevice(device);
            }
        }
    }

    @Transactional
    public void processMessages(Device device) {
        List<Log> logs = logRepository.getLogsByRegexAndPropertyIdAndDeviceId(Pattern.compile(device.getMessageRegex()), String.valueOf(device.getProperty().getId()), String.valueOf(device.getId()));
        logs.forEach(log -> {
            //obrada alarma
            logger.info("Log with message { " + log.getMessage() + " } has been processed");
            log.setProcessed(true);
        });
        logRepository.saveAll(logs);
    }

    private boolean isReadPeriodElapsed(Device device) {
        long readPeriod = deviceInfo.getReadPeriods().get(device);
        return Objects.isNull(device.getLastLogged()) || Duration.between(device.getLastLogged(), LocalDateTime.now()).toMinutes() >= readPeriod;
    }

    public void logMessageForDevice(Device device) {
        String message = getDeviceMessage.execute(device);
        logger.info(message);
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
                .processed(false)
                .build();
    }

    public void resetLastProcessTime(Device device) {
        device.setLastLogged(LocalDateTime.now());
        deviceRepository.save(device);
    }

}
