package com.bsep.smart.home.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Builder
@Entity
@Table(name = "device_rule")
public class DeviceRule extends BaseEntity {
    @OneToOne(mappedBy = "rule")
    Device device;
    double maxValue;
    double minValue;
}
