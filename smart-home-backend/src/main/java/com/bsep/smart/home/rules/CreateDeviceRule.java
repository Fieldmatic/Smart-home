package com.bsep.smart.home.rules;

import com.bsep.smart.home.model.DeviceRule;
import com.bsep.smart.home.model.DeviceType;
import com.bsep.smart.home.repository.DeviceRuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CreateDeviceRule {
    private final DeviceRuleRepository deviceRuleRepository;

    @Transactional
    public DeviceRule execute(DeviceType deviceType, double maxValue, double minValue) {
        DeviceRule deviceRule = new DeviceRule(deviceType, maxValue, minValue);
        return deviceRuleRepository.save(deviceRule);
    }
}
