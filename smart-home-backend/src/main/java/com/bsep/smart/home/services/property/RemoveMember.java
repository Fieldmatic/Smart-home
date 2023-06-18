package com.bsep.smart.home.services.property;

import com.bsep.smart.home.dto.request.property.RemoveMemberRequest;
import com.bsep.smart.home.model.Person;
import com.bsep.smart.home.model.Property;
import com.bsep.smart.home.repository.PropertyRepository;
import com.bsep.smart.home.services.user.GetUserById;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RemoveMember {
    private final GetUserById getUserById;
    private final GetPropertyById getPropertyById;
    private final PropertyRepository propertyRepository;

    @Transactional
    public Property execute(RemoveMemberRequest removeMemberRequest) {
        Person user = getUserById.execute(UUID.fromString(removeMemberRequest.getUserId()));
        Property property = getPropertyById.execute(UUID.fromString(removeMemberRequest.getPropertyId()));
        property.getMembers().remove(user);
        return propertyRepository.save(property);
    }
}
