package com.bsep.smart.home.services.property;

import com.bsep.smart.home.model.Person;
import com.bsep.smart.home.model.Property;
import com.bsep.smart.home.repository.PropertyRepository;
import com.bsep.smart.home.services.user.GetUserById;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetAccessiblePropertiesForUser {
    private final PropertyRepository propertyRepository;
    private final GetUserById getUserById;

    @Transactional
    public List<Property> execute(UUID userId) {
        Person user = getUserById.execute(userId);
        return propertyRepository.findAllByMembersContaining(user);
    }
}
