package com.bsep.smart.home.model.facts;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginFailedAlarm {
    private String userEmail;
    private String message;
    private AlarmType alarmType;

    public LoginFailedAlarm(String userEmail, AlarmType alarmType) {
        this.userEmail = userEmail;
        this.alarmType = alarmType;
    }

    public LoginFailedAlarm(AlarmType alarmType, String message) {
        this.message = message;
        this.alarmType = alarmType;
    }
}
