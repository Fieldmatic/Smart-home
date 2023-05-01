package com.bsep.smart.home.converter;

import com.bsep.smart.home.dto.request.property.PropertyResponse;
import com.bsep.smart.home.model.Property;

import java.util.List;

public class PropertyConverter {
    public static PropertyResponse toPropertyResponse(Property property) {
        return PropertyResponse.builder()
                .uuid(property.getId())
                .address(property.getAddress())
                .name(property.getName())
                .owner(UserConverter.toUserResponse(property.getOwner()))
                .members(UserConverter.toUsersResponse(property.getMembers()))
                .build();
    }

    public static List<PropertyResponse> toPropertiesResponse(List<Property> properties) {
        return properties.stream().map(PropertyConverter::toPropertyResponse).toList();
    }
}
