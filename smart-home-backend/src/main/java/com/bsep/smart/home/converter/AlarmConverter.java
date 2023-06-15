package com.bsep.smart.home.converter;

import com.bsep.smart.home.dto.response.AlarmResponse;
import com.bsep.smart.home.model.facts.Alarm;

import java.util.List;

public class AlarmConverter {

    public static AlarmResponse toAlarmResponse(final Alarm alarm) {
        return AlarmResponse.builder()
                .alarmType(alarm.getAlarmType())
                .value(alarm.getValue())
                .time(alarm.getTime())
                .errorMessage(alarm.getErrorMessage())
                .userEmail(alarm.getUserEmail())
                .build();
    }

    public static List<AlarmResponse> toAlarmsResponse(final List<Alarm> alarms) {
        return alarms.stream().map(AlarmConverter::toAlarmResponse).toList();
    }
}
