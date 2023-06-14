package com.bsep.smart.home.model.facts;

import com.bsep.smart.home.model.AlarmType;
import com.bsep.smart.home.model.BaseEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Alarm extends BaseEntity {
    AlarmType alarmType;
    String userEmail;
    String errorMessage;
    String deviceId;

    public Alarm(AlarmType alarmType) {
        this.alarmType = alarmType;
    }

    public Alarm(String userEmail, AlarmType alarmType) {
        this.userEmail = userEmail;
        this.alarmType = alarmType;
    }

    public Alarm(AlarmType alarmType, String errorMessage) {
        this.errorMessage = errorMessage;
        this.alarmType = alarmType;
    }
}
