package com.bsep.smart.home.model;

import org.springframework.security.core.GrantedAuthority;

public enum Permission implements GrantedAuthority {
    SEND_CSR_REQUEST,
    CSR_MANIPULATION,
    CERTIFICATE_MANIPULATION,
    USER_MANIPULATION,
    PROPERTY_MANIPULATION,
    REPORT_GENERATING;

    @Override
    public String getAuthority() {
        return name();
    }
}
