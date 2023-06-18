package com.bsep.smart.home.services.alarm;

import com.bsep.smart.home.model.facts.Alarm;
import com.bsep.smart.home.repository.AlarmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class SaveAlarm {
    private final AlarmRepository alarmRepository;

    @Transactional
    public Alarm execute(Alarm alarm) {
        return alarmRepository.save(alarm);
    }
}
