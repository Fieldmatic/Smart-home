package com.bsep.smart.home.model;


import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.time.LocalDateTime;


@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Builder
@Document("logs")
@FieldNameConstants
public class Log {
    @Id
    String id;
    @Column(name = "created_at", nullable = false)
    LocalDateTime createdAt;
    String deviceId;
    String message;
    String propertyId;
}
