package com.bsep.smart.home.services.property;

import com.bsep.smart.home.model.Person;
import com.bsep.smart.home.model.Property;
import com.bsep.smart.home.repository.PropertyRepository;
import com.bsep.smart.home.services.auth.GetLoggedInUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAccessibleProperties {
    private final PropertyRepository propertyRepository;
    private final GetLoggedInUser getLoggedInUser;

    @Transactional
    public List<Property> execute() {
        Person user = getLoggedInUser.execute();
        return propertyRepository.findAllByMembersContaining(user);
    }
}
