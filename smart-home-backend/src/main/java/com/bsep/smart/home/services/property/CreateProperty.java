package com.bsep.smart.home.services.property;

import com.bsep.smart.home.dto.request.property.CreatePropertyRequest;
import com.bsep.smart.home.model.Person;
import com.bsep.smart.home.model.Property;
import com.bsep.smart.home.repository.PersonRepository;
import com.bsep.smart.home.repository.PropertyRepository;
import com.bsep.smart.home.services.user.GetUserById;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateProperty {
    private final PropertyRepository propertyRepository;
    private final PersonRepository personRepository;
    private final GetUserById getUserById;

    @Transactional
    public Property execute(CreatePropertyRequest createPropertyRequest) {
        Person owner = getUserById.execute(UUID.fromString(createPropertyRequest.getOwnerId()));
        Property property = Property.builder()
                .name(createPropertyRequest.getName())
                .address(createPropertyRequest.getAddress())
                .owner(owner)
                .members(Collections.singletonList(owner))
                .build();
        return propertyRepository.save(property);
    }
}
