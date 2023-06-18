package com.bsep.smart.home.dto.response;

import com.bsep.smart.home.model.AlarmType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlarmResponse {
    private AlarmType alarmType;
    private LocalDateTime time;
    private double value;
    private String userEmail;
    private String errorMessage;
}
