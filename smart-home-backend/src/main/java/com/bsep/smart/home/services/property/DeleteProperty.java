package com.bsep.smart.home.services.property;

import com.bsep.smart.home.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteProperty {

    private final PropertyRepository propertyRepository;
    private final GetPropertyById getPropertyById;

    @Transactional
    public void execute(UUID id) {
        propertyRepository.delete(getPropertyById.execute(id));
    }
}
