package com.bsep.smart.home.controller;

import com.bsep.smart.home.converter.PropertyConverter;
import com.bsep.smart.home.dto.request.property.CreatePropertyRequest;
import com.bsep.smart.home.dto.request.property.PropertyResponse;
import com.bsep.smart.home.model.Permission;
import com.bsep.smart.home.security.HasAnyPermission;
import com.bsep.smart.home.services.property.CreateProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/property")
@RequiredArgsConstructor
public class PropertyController {
    private final CreateProperty createProperty;

    @PostMapping
    @HasAnyPermission({Permission.PROPERTY_MANIPULATION})
    public PropertyResponse create(@Valid @RequestBody CreatePropertyRequest createPropertyRequest) {
        return PropertyConverter.toPropertyResponse(createProperty.execute(createPropertyRequest));
    }
}
