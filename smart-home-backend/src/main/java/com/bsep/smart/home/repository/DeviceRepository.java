package com.bsep.smart.home.repository;

import com.bsep.smart.home.model.Device;
import com.bsep.smart.home.model.Property;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DeviceRepository extends JpaRepository<Device, UUID> {
    Page<Device> getAllByProperty(Property property, Pageable pageable);
}
