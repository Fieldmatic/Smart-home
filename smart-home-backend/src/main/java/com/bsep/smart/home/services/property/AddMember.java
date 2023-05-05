package com.bsep.smart.home.services.property;

import com.bsep.smart.home.dto.request.property.AddMemberRequest;
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
public class AddMember {
    private final GetUserById getUserById;
    private final GetPropertyById getPropertyById;
    private final PropertyRepository propertyRepository;

    @Transactional
    public Property execute(AddMemberRequest addMemberRequest) {
        Person user = getUserById.execute(UUID.fromString(addMemberRequest.getUserId()));
        Property property = getPropertyById.execute(UUID.fromString(addMemberRequest.getPropertyId()));
        property.getMembers().add(user);
        return propertyRepository.save(property);
    }
}
