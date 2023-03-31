package com.bsep.smart.home.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "certificate_type")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
@SuperBuilder
public class CertificateType extends BaseEntity {
    String name;

    @ManyToMany
    @JoinTable(name = "certificate_type_capability",
            joinColumns = @JoinColumn(name = "certificate_type_id"),
            inverseJoinColumns = @JoinColumn(name = "capability_id"))
    @JsonManagedReference
    List<Capabilities> capabilities;
}
