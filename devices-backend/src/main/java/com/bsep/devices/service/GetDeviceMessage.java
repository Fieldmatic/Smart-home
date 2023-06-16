package com.bsep.devices.service;

import com.bsep.smart.home.model.Device;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class GetDeviceMessage {
    public String execute(Device device) {
        String deviceType = device.getDeviceType().toString().substring(0, 1).toUpperCase() + device.getDeviceType().toString().substring(1).toLowerCase();
        String message;
        switch (device.getDeviceType()) {
            case DOOR -> {
                if (device.getActivated()) {
                    message = "closed";
                } else {
                    message = "opened";
                }
                device.setActivated(!device.getActivated());
                return device.getName() + " of type " + deviceType + " has been " + message + "!";
            }
            case LIGHT -> {
                if (device.getActivated()) {
                    message = "off";
                } else {
                    message = "on";
                }
                device.setActivated(!device.getActivated());
                return device.getName() + " of type " + deviceType + " went " + message + "!";
            }
            case CAMERA -> {
                if (device.getActivated()) {
                    message = "deactivated";
                } else {
                    message = "activated";
                }
                device.setActivated(!device.getActivated());
                return device.getName() + " of type " + deviceType + " has been " + message + "!";

            }
            case THERMOMETER -> {
                Random random = new Random();
                double degrees = 15 + random.nextDouble() * 15; //15-30
                degrees = Math.round(degrees * 100.0) / 100.0;
                device.setValue(degrees);
                return device.getName() + " of type " + deviceType + " detected a temperature of " + degrees + " degrees!";
            }
            case BAROMETER -> {
                Random random = new Random();
                double pressure = 0.75 + random.nextDouble() * 0.4; // 0.75-1.15
                pressure = Math.round(pressure * 100.0) / 100.0;
                device.setValue(pressure);
                return device.getName() + " of type " + deviceType + " detected a pressure of " + pressure + " bar!";
            }
            default -> {
                return "";
            }
        }
    }
}
