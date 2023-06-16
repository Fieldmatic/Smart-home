package com.bsep.devices.service;

import com.bsep.devices.repository.DeviceRepository;
import com.bsep.smart.home.model.Device;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SetAttackDeviceState {
    private final DeviceRepository deviceRepository;

    public boolean execute(UUID uuid) {
        Device device = deviceRepository.getReferenceById(uuid);
        device.setAttack(!device.isAttack());
        device = deviceRepository.save(device);
        return device.isAttack();
    }
}
