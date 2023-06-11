package com.bsep.smart.home.model;

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
@Table(name = "property")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
@SuperBuilder
public class Property extends BaseEntity {
    String name;
    String address;
    @ManyToOne
    Person owner;
    @ManyToMany
    List<Person> members;
    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
    List<Device> devices;

}
