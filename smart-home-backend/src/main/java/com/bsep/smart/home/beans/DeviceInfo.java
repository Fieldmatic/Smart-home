package com.bsep.smart.home.beans;

import com.bsep.smart.home.model.Device;
import com.bsep.smart.home.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DeviceInfo {
    private Map<UUID, Long> readPeriods;

    private final DeviceRepository deviceRepository;
    

    public Map<UUID, Long> getReadPeriods() {
        if (readPeriods == null) {
            readPeriods = createDeviceReadPeriodMap();
        }
        return readPeriods;
    }

    private Map<UUID, Long> createDeviceReadPeriodMap() {
        Map<UUID, Long> deviceReadPeriods = new HashMap<>();
        List<Device> devices = deviceRepository.findAll(); // Fetch devices from the database
        for (Device device : devices) {
            deviceReadPeriods.put(device.getId(), device.getReadPeriod());
        }
        return deviceReadPeriods;
    }
}
