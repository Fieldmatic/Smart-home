package com.bsep.smart.home.model.facts;

import com.bsep.smart.home.model.AlarmType;
import com.bsep.smart.home.model.BaseEntity;
import com.bsep.smart.home.model.Device;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

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
    double value;
    LocalDateTime time;
    @ManyToOne
    Device device;

    public Alarm(AlarmType alarmType, double value, LocalDateTime time) {
        this.alarmType = alarmType;
        this.value = value;
        this.time = time;
    }

    public Alarm(String userEmail, AlarmType alarmType, String errorMessage) {
        this.userEmail = userEmail;
        this.alarmType = alarmType;
        this.errorMessage = errorMessage;
        this.time = LocalDateTime.now();
    }

    public Alarm(AlarmType alarmType, String errorMessage) {
        this.errorMessage = errorMessage;
        this.alarmType = alarmType;
        this.time = LocalDateTime.now();
    }
}
