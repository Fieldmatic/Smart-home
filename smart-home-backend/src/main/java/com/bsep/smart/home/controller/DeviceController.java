package com.bsep.smart.home.controller;


import com.bsep.smart.home.converter.DeviceConverter;
import com.bsep.smart.home.dto.request.property.AddDeviceRuleRequest;
import com.bsep.smart.home.dto.response.DeviceResponse;
import com.bsep.smart.home.model.Permission;
import com.bsep.smart.home.rules.CreateDeviceRule;
import com.bsep.smart.home.security.HasAnyPermission;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/device")
public class DeviceController {

    private final CreateDeviceRule createDeviceRule;

    @PutMapping("/add-device-rule")
    @HasAnyPermission({Permission.PROPERTY_MANIPULATION})
    public DeviceResponse addDevice(@Valid @RequestBody AddDeviceRuleRequest addDeviceRuleRequest) {
        return DeviceConverter.toDeviceResponse(createDeviceRule.execute(addDeviceRuleRequest));
    }
}
