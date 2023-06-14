package com.bsep.smart.home.rules;

import com.bsep.smart.home.model.Device;
import com.bsep.smart.home.model.DeviceRule;
import com.bsep.smart.home.repository.DeviceRuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CreateDeviceRule {
    private final DeviceRuleRepository deviceRuleRepository;

    @Transactional
    public DeviceRule execute(Device device, double maxValue, double minValue) {
        DeviceRule deviceRule = new DeviceRule(device, maxValue, minValue);
        return deviceRuleRepository.save(deviceRule);
    }
}
