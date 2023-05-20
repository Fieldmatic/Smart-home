package com.bsep.smart.home.controller;


import com.bsep.smart.home.model.Device;
import com.bsep.smart.home.services.device.GetDevicesByPropertyId;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/device")
public class DeviceController {

    private final GetDevicesByPropertyId getDevicesByPropertyId;

    @GetMapping("/{propertyId}")
    public List<Device> getAllDevices(@PathVariable UUID propertyId) {
        return getDevicesByPropertyId.execute(propertyId);
    }
}
