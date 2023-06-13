package com.bsep.smart.home.model.facts;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Alarm {
    private String userEmail;
    private String message;
    private AlarmType alarmType;

    public Alarm(String userEmail, AlarmType alarmType) {
        this.userEmail = userEmail;
        this.alarmType = alarmType;
    }

    public Alarm(AlarmType alarmType, String message) {
        this.message = message;
        this.alarmType = alarmType;
    }
}
