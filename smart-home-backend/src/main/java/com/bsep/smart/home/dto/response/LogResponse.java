package com.bsep.smart.home.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LogResponse {
    String id;
    LocalDateTime createdAt;
    String message;
}
