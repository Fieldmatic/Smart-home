package com.bsep.smart.home.converter;

import com.bsep.smart.home.dto.request.property.PropertyResponse;
import com.bsep.smart.home.model.Property;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class PropertyConverter {
    public static PropertyResponse toPropertyResponse(Property property) {
        return PropertyResponse.builder()
                .uuid(property.getId())
                .address(property.getAddress())
                .name(property.getName())
                .owner(UserConverter.toUserResponse(property.getOwner()))
                .members(UserConverter.toUsersResponse(property.getMembers()))
                .devices(DeviceConverter.toDevicesResponse(property.getDevices()))
                .build();
    }

    public static List<PropertyResponse> toPropertiesResponse(List<Property> properties) {
        if (Objects.isNull(properties)) return Collections.emptyList();
        return properties.stream().map(PropertyConverter::toPropertyResponse).toList();
    }
}
