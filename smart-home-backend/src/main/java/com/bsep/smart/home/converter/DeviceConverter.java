package com.bsep.smart.home.converter;

import com.bsep.smart.home.dto.response.DeviceResponse;
import com.bsep.smart.home.model.Device;

import java.util.List;

public class DeviceConverter {

    public static DeviceResponse toDeviceResponse(final Device device) {
        DeviceResponse deviceResponse = DeviceResponse.builder()
                .name(device.getName())
                .deviceType(device.getDeviceType())
                .messageRegex(device.getMessageRegex())
                .readPeriod(device.getReadPeriod())
                .alarms(AlarmConverter.toAlarmsResponse(device.getAlarms()))
                .build();
        if (device.getRule() != null)
            deviceResponse.setDeviceRule(DeviceRuleConverter.toDeviceRuleResponse(device.getRule()));
        return deviceResponse;
    }

    public static List<DeviceResponse> toDevicesResponse(final List<Device> devices) {
        return devices.stream().map(DeviceConverter::toDeviceResponse).toList();
    }
}
