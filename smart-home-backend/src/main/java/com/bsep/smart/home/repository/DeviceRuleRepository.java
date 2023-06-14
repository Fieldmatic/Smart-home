package com.bsep.smart.home.repository;

import com.bsep.smart.home.model.DeviceRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DeviceRuleRepository extends JpaRepository<DeviceRule, UUID> {
}
