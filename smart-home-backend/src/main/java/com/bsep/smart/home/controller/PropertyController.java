package com.bsep.smart.home.controller;

import com.bsep.smart.home.converter.PropertyConverter;
import com.bsep.smart.home.dto.request.property.AddMemberRequest;
import com.bsep.smart.home.dto.request.property.CreatePropertyRequest;
import com.bsep.smart.home.dto.request.property.PropertyResponse;
import com.bsep.smart.home.dto.request.property.RemoveMemberRequest;
import com.bsep.smart.home.model.Permission;
import com.bsep.smart.home.security.HasAnyPermission;
import com.bsep.smart.home.services.property.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/property")
@RequiredArgsConstructor
public class PropertyController {
    private final CreateProperty createProperty;
    private final AddMember addMember;
    private final GetAccessiblePropertiesForUser getAccessiblePropertiesForUser;
    private final GetAccessibleProperties getAccessibleProperties;
    private final RemoveMember removeMember;
    private final DeleteProperty deleteProperty;

    @PostMapping
    @HasAnyPermission({Permission.PROPERTY_MANIPULATION})
    public PropertyResponse create(@Valid @RequestBody CreatePropertyRequest createPropertyRequest) {
        return PropertyConverter.toPropertyResponse(createProperty.execute(createPropertyRequest));
    }

    @DeleteMapping("/{id}")
    @HasAnyPermission({Permission.PROPERTY_MANIPULATION})
    public void delete(@PathVariable UUID id) {
        deleteProperty.execute(id);
    }

    @PutMapping("/add-member")
    @HasAnyPermission({Permission.PROPERTY_MANIPULATION})
    public PropertyResponse addMember(@Valid @RequestBody AddMemberRequest addMemberRequest) {
        return PropertyConverter.toPropertyResponse(addMember.execute(addMemberRequest));
    }

    @PutMapping("/remove-member")
    @HasAnyPermission({Permission.PROPERTY_MANIPULATION})
    public PropertyResponse addMember(@Valid @RequestBody RemoveMemberRequest removeMemberRequest) {
        return PropertyConverter.toPropertyResponse(removeMember.execute(removeMemberRequest));
    }

    @GetMapping("/accessible/{userId}")
    @HasAnyPermission({Permission.PROPERTY_MANIPULATION})
    public List<PropertyResponse> getAccessiblePropertiesForUser(@Valid @PathVariable UUID userId) {
        return PropertyConverter.toPropertiesResponse(getAccessiblePropertiesForUser.execute(userId));
    }

    @GetMapping("/accessible")
    public List<PropertyResponse> getAccessibleProperties() {
        return PropertyConverter.toPropertiesResponse(getAccessibleProperties.execute());
    }
}
