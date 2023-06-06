package com.bsep.smart.home.services.device;

import com.bsep.smart.home.model.Device;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetDeviceMessage {


    public String execute(Device device) {
        String deviceType = device.getDeviceType().toString().substring(0, 1).toUpperCase() + device.getDeviceType().toString().substring(1).toLowerCase();
        switch (device.getDeviceType()) {
            case DOOR -> {
                if (device.getActivated()) {
                    return device.getName() + " of type " + deviceType + " has been closed!";
                } else {
                    return device.getName() + " of type " + deviceType + " has been opened!";
                }
            }
            case LIGHT -> {
                if (device.getActivated()) {
                    return device.getName() + " of type " + deviceType + " went off";
                } else {
                    return device.getName() + " of type " + deviceType + " went on";
                }

            }
            case CAMERA -> {
                if (device.getActivated()) {
                    return device.getName() + " of type " + deviceType + " has been activated!";
                } else {
                    return device.getName() + " of type " + deviceType + " has been deactivated!";
                }

            }
            default -> {
                return "";
            }
        }
    }
}
