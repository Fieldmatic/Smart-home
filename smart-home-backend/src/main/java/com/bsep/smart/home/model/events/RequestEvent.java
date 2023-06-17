package com.bsep.smart.home.model.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

@Role(Role.Type.EVENT)
@Expires("1m")
@Getter
@Setter
public class RequestEvent {
    private String ipAddress;
    private String userEmail;

    public RequestEvent(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public RequestEvent(String ipAddress, String userEmail) {
        this(ipAddress);
        this.userEmail = userEmail;
    }
}
