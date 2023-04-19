package com.bsep.smart.home.exception;

import com.bsep.smart.home.translations.Translation;
import lombok.Getter;

@Getter
public enum ExceptionKeys implements Translation {
    USER_NOT_FOUND("user_not_found"),
    USER_NOT_ACTIVE("user_not_active"),

    USER_BLOCKED("user_blocked"),
    UNAUTHORIZED("unauthorized"),
    AUTH_TOKEN_EXPIRED("auth_token_expired"),

    BAD_LOGIN_CREDENTIALS("bad_login_credentials"),

    USER_ALREADY_EXISTS("user_already_exists"),
    MISSING_AUTHENTICATION("missing_authentication"),
    ROLE_NOT_FOUND("role_not_found"),
    INSUFFICIENT_PERMISSIONS("insufficient_permissions"),
    AUTH_TOKEN_INVALID("auth_token_invalid"),
    MAIL_FAILED("mail_failed"),
    PASSWORD_SAME("password_same"),
    PASSWORD_MISMATCH("password_mismatch"),
    USER_HAS_PENDING_CSR("user_has_pending_csr"),
    EMAIL_NOT_VERIFIED("email_not_verified"),
    FINGERPRINT_INVALID("fingerprint_invalid"),
    SIGNING_ALGORITHM_INVALID("signing_algorithm_invalid"),
    INVALID_PIN("invalid_pin"),
    LOCKED_ACCOUNT_EXCEPTION("locked_account_exception");

    private final String code;

    ExceptionKeys(final String code) {
        this.code = code;
    }

    @Override
    public String getCode() {
        return code;
    }
}
