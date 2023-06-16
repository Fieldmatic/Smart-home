package com.bsep.devices.service;

import com.bsep.devices.repository.DeviceRepository;
import com.bsep.smart.home.model.Device;
import com.bsep.smart.home.model.DeviceRule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class GetDeviceMessage {
    public String execute(Device device) {
        String deviceType = device.getDeviceType().toString().substring(0, 1).toUpperCase() + device.getDeviceType().toString().substring(1).toLowerCase();
        Random random = new Random();
        String message;
        switch (device.getDeviceType()) {
            case DOOR -> {
                if (device.getActivated()) {
                    message = "closed";
                } else {
                    message = "opened";
                }
                device.setActivated(!device.getActivated());
                if (device.isAttack()) {
                    message += " by force";
                }
                return device.getName() + " of type " + deviceType + " has been " + message + "!";
            }
            case LIGHT -> {
                if (device.isAttack()) return "The power went out.";
                if (device.getActivated()) {
                    message = "off";
                } else {
                    message = "on";
                }
                device.setActivated(!device.getActivated());
                return device.getName() + " of type " + deviceType + " went " + message + "!";
            }
            case CAMERA -> {
                if (device.isAttack()) return "An unknown object was spotted on the camera.";
                if (device.getActivated()) {
                    message = "deactivated";
                } else {
                    message = "activated";
                }
                device.setActivated(!device.getActivated());
                return device.getName() + " of type " + deviceType + " has been " + message + "!";

            }
            case THERMOMETER -> {
                double value;
                if (device.isAttack() && device.getRule() != null) {
                    value = device.getRule().getMaxValue() + 0.1 + random.nextDouble() * 0.4;
                } else {
                    random = new Random();
                    double degrees = 15 + random.nextDouble() * 15; //15-30
                    value = Math.round(degrees * 100.0) / 100.0;
                }
                device.setValue(value);
                return device.getName() + " of type " + deviceType + " detected a temperature of " + value + " degrees!";
            }
            case BAROMETER -> {
                double value;
                if (device.isAttack() && device.getRule() != null) {
                    value = device.getRule().getMaxValue() + 0.1 + random.nextDouble() * 0.4;
                } else {
                    random = new Random();
                    double pressure = 0.75 + random.nextDouble() * 0.4; // 0.75-1.15
                    value = Math.round(pressure * 100.0) / 100.0;
                }
                device.setValue(value);
                return device.getName() + " of type " + deviceType + " detected a pressure of " + value + " bar!";
            }
            default -> {
                return "";
            }
        }
    }
}
