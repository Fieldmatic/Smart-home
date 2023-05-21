package com.bsep.smart.home.services.device;

import com.bsep.smart.home.dto.response.PageResponse;
import com.bsep.smart.home.jpaspecification.PagingUtil;
import com.bsep.smart.home.model.Device;
import com.bsep.smart.home.repository.DeviceRepository;
import com.bsep.smart.home.repository.PropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetDevicesByPropertyId {

    private final DeviceRepository deviceRepository;
    private final PropertyRepository propertyRepository;

    @Transactional
    public PageResponse<Device> execute(UUID propertyId, int pageNumber, int pageSize) {
        Pageable pageable = PagingUtil.getPageable(pageNumber, pageSize);
        Page<Device> devicePage = deviceRepository.getAllByProperty(propertyRepository.getReferenceById(propertyId), pageable);
        return PageResponse.<Device>builder()
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .items(devicePage.getContent())
                .totalPages(devicePage.getTotalPages())
                .numberOfElements(devicePage.getNumberOfElements())
                .totalElements(devicePage.getTotalElements())
                .build();
    }
}
