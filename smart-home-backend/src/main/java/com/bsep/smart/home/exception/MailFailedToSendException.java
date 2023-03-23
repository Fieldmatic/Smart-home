package com.bsep.smart.home.exception;

public class MailFailedToSendException extends CustomRuntimeException {
    public MailFailedToSendException() {
        super(ExceptionKeys.MAIL_FAILED);
    }
}
