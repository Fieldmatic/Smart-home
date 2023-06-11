package com.bsep.smart.home.converter;

import com.bsep.smart.home.dto.response.DeviceResponse;
import com.bsep.smart.home.model.Device;

import java.util.List;

public class DeviceConverter {

    public static DeviceResponse toDeviceResponse(final Device device) {
        return DeviceResponse.builder()
                .name(device.getName())
                .deviceType(device.getDeviceType())
                .messageRegex(device.getMessageRegex())
                .build();
    }

    public static List<DeviceResponse> toDevicesResponse(final List<Device> devices) {
        return devices.stream().map(DeviceConverter::toDeviceResponse).toList();
    }
}
