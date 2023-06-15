package com.bsep.smart.home.controller;


import com.bsep.smart.home.converter.DeviceConverter;
import com.bsep.smart.home.dto.request.property.AddDeviceRuleRequest;
import com.bsep.smart.home.dto.request.users.PageRequest;
import com.bsep.smart.home.dto.response.DeviceResponse;
import com.bsep.smart.home.dto.response.PageResponse;
import com.bsep.smart.home.model.Device;
import com.bsep.smart.home.model.Permission;
import com.bsep.smart.home.rules.CreateDeviceRule;
import com.bsep.smart.home.security.HasAnyPermission;
import com.bsep.smart.home.services.device.GetDevicesByPropertyId;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/device")
public class DeviceController {

    private final GetDevicesByPropertyId getDevicesByPropertyId;
    private final CreateDeviceRule createDeviceRule;

    @GetMapping("/{propertyId}")
    public PageResponse<Device> getAllDevices(@PathVariable UUID propertyId, @Valid final PageRequest pageRequest) {
        return getDevicesByPropertyId.execute(propertyId, pageRequest.getPageNumber(), pageRequest.getPageSize());
    }

    @PutMapping("/add-device-rule")
    @HasAnyPermission({Permission.PROPERTY_MANIPULATION})
    public DeviceResponse addDevice(@Valid @RequestBody AddDeviceRuleRequest addDeviceRuleRequest) {
        return DeviceConverter.toDeviceResponse(createDeviceRule.execute(addDeviceRuleRequest));
    }
}
