package com.bsep.smart.home.model;

import com.bsep.smart.home.model.facts.Alarm;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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
    @Builder.Default
    Boolean activated = false;
    Double value;
    @ManyToOne
    @JsonIgnore
    Property property;
    Long readPeriod;
    LocalDateTime lastLogged;
    String messageRegex;
    @Enumerated(EnumType.STRING)
    DeviceType deviceType;
    @OneToMany(mappedBy = "device", cascade = CascadeType.ALL)
    List<Alarm> alarms;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rule_id", referencedColumnName = "id")
    DeviceRule rule;
    boolean attack;
}
