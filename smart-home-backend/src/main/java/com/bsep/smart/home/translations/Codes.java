package com.bsep.smart.home.translations;

public enum Codes implements Translation {
    PASSWORD_RESET_LINK("password_reset_link"),
    PASSWORD_RESET_EMAIL_SUBJECT("password_reset_email_subject"),
    PASSWORD_RESET_REQUEST_SUCCESS("password_reset_request_success"),
    EMAIL_ACTIVATION_SUCCESS("email_activation_success"),
    SIGN_UP_ACTIVATION_EMAIL_SUBJECT("sign_up_activation_email_subject"),
    SIGN_UP_ACTIVATION_EMAIL("sign_up_activation_email");



    private final String code;

    Codes(String code) {
        this.code = code;
    }

    @Override
    public String getCode() {
        return code;
    }
}
