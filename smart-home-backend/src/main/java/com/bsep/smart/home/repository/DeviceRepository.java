package com.bsep.smart.home.repository;

import com.bsep.smart.home.model.Device;
import com.bsep.smart.home.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DeviceRepository extends JpaRepository<Device, UUID> {
    List<Device> getAllByProperty(Property property);
}
