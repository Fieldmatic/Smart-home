package com.bsep.smart.home.model.events;

import com.bsep.smart.home.model.Device;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

@Role(Role.Type.EVENT)
@Expires("1d")
@Getter
@Setter
@AllArgsConstructor
public class DeviceAttackEvent {
    private Device device;
    private String message;
}
