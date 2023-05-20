package com.bsep.smart.home.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "device")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
@SuperBuilder
public class Device extends BaseEntity {
    String name;
    Boolean activated;
    Double value;
    @ManyToOne
    @JsonIgnore
    Property property;
    Long readPeriod;
    LocalDateTime lastLogged;
    String messageRegex;
    @Enumerated(EnumType.STRING)
    DeviceType deviceType;
}
