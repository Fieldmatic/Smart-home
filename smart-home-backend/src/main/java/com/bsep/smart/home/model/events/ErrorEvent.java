package com.bsep.smart.home.model.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

@Role(Role.Type.EVENT)
@Expires("1d")
@Getter
@Setter
@Builder
public class ErrorEvent {
    private String message;
    private String userEmail;
}
