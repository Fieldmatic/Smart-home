package com.bsep.smart.home.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "capabilities")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
public class Capabilities extends BaseEntity {
    String name;
    @ManyToOne
    @JsonBackReference
    Extension extension;
    @ManyToMany(mappedBy = "capabilities")
    @JsonBackReference
    List<CertificateType> certificateType;
}
