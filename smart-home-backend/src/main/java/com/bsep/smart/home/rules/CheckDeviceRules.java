package com.bsep.smart.home.rules;

import com.bsep.smart.home.model.AlarmType;
import com.bsep.smart.home.model.Device;
import com.bsep.smart.home.model.DeviceRule;
import com.bsep.smart.home.model.DeviceType;
import com.bsep.smart.home.model.facts.Alarm;
import com.bsep.smart.home.repository.DeviceRuleRepository;
import com.bsep.smart.home.services.device.GetDeviceById;
import lombok.RequiredArgsConstructor;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CheckDeviceRules {
    private final DeviceRuleRepository deviceRuleRepository;
    private final GetDeviceById getDeviceById;
    private final KieSession kieSession;

    @Transactional
    public void execute(String deviceId) {
        Device device = getDeviceById.execute(UUID.fromString(deviceId));
        List<DeviceRule> deviceRules = deviceRuleRepository.findAll();
        for (DeviceRule rule : deviceRules) {
            if (rule.getDevice().getDeviceType().equals(device.getDeviceType())) {
                Alarm alarmFact = Alarm.builder().device(device)
                        .value(device.getValue())
                        .time(LocalDateTime.now()).build();
                if (device.getValue() > rule.getMaxValue()) {
                    setAlarmType(alarmFact, device.getDeviceType(), false);
                } else if (device.getValue() < rule.getMinValue()) {
                    setAlarmType(alarmFact, device.getDeviceType(), true);
                }

            }
        }
    }

    private void setAlarmType(Alarm alarmFact, DeviceType deviceType, boolean low) {
        if (deviceType.equals(DeviceType.THERMOMETER)) {
            if (low) alarmFact.setAlarmType(AlarmType.LOW_TEMPERATURE);
            else alarmFact.setAlarmType(AlarmType.HIGH_TEMPERATURE);
        } else {
            if (low) alarmFact.setAlarmType(AlarmType.LOW_PRESSURE);
            else alarmFact.setAlarmType(AlarmType.HIGH_PRESSURE);
        }
        kieSession.insert(alarmFact);
        kieSession.fireAllRules();
    }
}
