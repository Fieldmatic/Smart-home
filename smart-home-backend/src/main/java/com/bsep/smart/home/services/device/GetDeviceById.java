package com.bsep.smart.home.services.device;

import com.bsep.smart.home.model.Device;
import com.bsep.smart.home.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetDeviceById {
    private final DeviceRepository deviceRepository;

    @Transactional
    public Device execute(UUID deviceId) {
        return deviceRepository.getReferenceById(deviceId);
    }
}
