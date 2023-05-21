package com.bsep.smart.home.controller;


import com.bsep.smart.home.dto.request.users.PageRequest;
import com.bsep.smart.home.dto.response.PageResponse;
import com.bsep.smart.home.model.Device;
import com.bsep.smart.home.services.device.GetDevicesByPropertyId;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/device")
public class DeviceController {

    private final GetDevicesByPropertyId getDevicesByPropertyId;

    @GetMapping("/{propertyId}")
    public PageResponse<Device> getAllDevices(@PathVariable UUID propertyId, @Valid final PageRequest pageRequest) {
        return getDevicesByPropertyId.execute(propertyId, pageRequest.getPageNumber(), pageRequest.getPageSize());
    }
}
