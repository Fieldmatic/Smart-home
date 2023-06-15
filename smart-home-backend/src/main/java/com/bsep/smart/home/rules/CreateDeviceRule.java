package com.bsep.smart.home.rules;

import com.bsep.smart.home.dto.request.property.AddDeviceRuleRequest;
import com.bsep.smart.home.model.Device;
import com.bsep.smart.home.model.DeviceRule;
import com.bsep.smart.home.repository.DeviceRepository;
import com.bsep.smart.home.repository.DeviceRuleRepository;
import com.bsep.smart.home.services.device.GetDeviceById;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateDeviceRule {
    private final DeviceRuleRepository deviceRuleRepository;
    private final DeviceRepository deviceRepository;
    private final GetDeviceById getDeviceById;

    @Transactional
    public Device execute(AddDeviceRuleRequest addDeviceRuleRequest) {
        Device device = getDeviceById.execute(UUID.fromString(addDeviceRuleRequest.getDeviceId()));
        if (device.getRule() != null) deviceRuleRepository.delete(device.getRule());
        DeviceRule deviceRule = DeviceRule.builder().
                maxValue(addDeviceRuleRequest.getMaxValue())
                .minValue(addDeviceRuleRequest.getMinValue())
                .device(device)
                .build();
        deviceRuleRepository.save(deviceRule);
        device.setRule(deviceRule);
        return deviceRepository.save(device);
    }
}
