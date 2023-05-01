package com.bsep.smart.home.services.property;

import com.bsep.smart.home.exception.PropertyNotFoundException;
import com.bsep.smart.home.model.Property;
import com.bsep.smart.home.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetPropertyById {

    private final PropertyRepository propertyRepository;

    @Transactional(readOnly = true)
    public Property execute(final UUID id) {
        return propertyRepository.findById(id).orElseThrow(PropertyNotFoundException::new);
    }

}
