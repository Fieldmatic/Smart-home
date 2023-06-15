package com.bsep.devices.beans;

import com.bsep.devices.repository.DeviceRepository;
import com.bsep.smart.home.model.Device;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class DeviceInfo {
    private Map<Device, Long> readPeriods;

    private final DeviceRepository deviceRepository;
    

    public Map<Device, Long> getReadPeriods() {
        if (readPeriods == null) {
            readPeriods = createDeviceReadPeriodMap();
        }
        return readPeriods;
    }

    private Map<Device, Long> createDeviceReadPeriodMap() {
        Map<Device, Long> deviceReadPeriods = new HashMap<>();
        List<Device> devices = deviceRepository.findAll(); // Fetch devices from the database
        for (Device device : devices) {
            deviceReadPeriods.put(device, device.getReadPeriod());
        }
        return deviceReadPeriods;
    }
}
