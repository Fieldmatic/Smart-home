package com.bsep.smart.home.services.device;

import com.bsep.smart.home.model.Device;
import com.bsep.smart.home.repository.DeviceRepository;
import com.bsep.smart.home.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetDevicesByPropertyId {

    private final DeviceRepository deviceRepository;
    private final PropertyRepository propertyRepository;

    @Transactional
    public List<Device> execute(UUID propertyId) {
        return deviceRepository.getAllByProperty(propertyRepository.getReferenceById(propertyId));
    }
}
