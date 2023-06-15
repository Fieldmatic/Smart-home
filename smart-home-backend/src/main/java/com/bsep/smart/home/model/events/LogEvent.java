package com.bsep.smart.home.model.events;

import com.bsep.smart.home.model.DeviceType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

@Role(Role.Type.EVENT)
@Expires("30m")
@Getter
@Setter
@Builder
public class LogEvent {
    private String deviceId;
    private String deviceName;
    private boolean activated;
    private Double value;
    DeviceType deviceType;
}
