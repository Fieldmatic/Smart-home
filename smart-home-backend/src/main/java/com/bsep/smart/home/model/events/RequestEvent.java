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
@AllArgsConstructor
public class RequestEvent {
    public String userEmail;
}
