package com.bsep.smart.home.repository;

import com.bsep.smart.home.model.facts.Alarm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface AlarmRepository extends JpaRepository<Alarm, UUID>, JpaSpecificationExecutor<Alarm> {

    Page<Alarm> findByDeviceIdIsNull(Pageable pageable);

    List<Alarm> findAllByDevicePropertyIdAndCreatedAtBetween(UUID id, LocalDateTime start, LocalDateTime end);

    Long countAlarmsByTimeBetween(LocalDateTime start, LocalDateTime end);

}
