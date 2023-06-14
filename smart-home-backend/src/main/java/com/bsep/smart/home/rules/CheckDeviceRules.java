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
            if (rule.getDeviceType().equals(device.getDeviceType())) {
                if (device.getValue() > rule.getMaxValue() || device.getValue() < rule.getMinValue()) {
                    Alarm alarmFact = Alarm.builder().deviceId(deviceId).build();
                    if (device.getDeviceType().equals(DeviceType.THERMOMETER)) {
                        alarmFact.setAlarmType(AlarmType.DEGREES);
                    } else {
                        alarmFact.setAlarmType(AlarmType.PRESSURE);
                    }
                    kieSession.insert(alarmFact);
                    kieSession.fireAllRules();
                }
            }
        }
    }
}
