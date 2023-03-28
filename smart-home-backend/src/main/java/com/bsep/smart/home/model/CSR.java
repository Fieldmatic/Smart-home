package com.bsep.smart.home.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "csr")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
@SuperBuilder
public class CSR extends BaseEntity {
    @Column(name = "email", nullable = false)
    String email;
    @Column(name = "common_name", nullable = false)
    String commonName;
    @Column(name = "organization", nullable = true)
    String organization;
    @Column(name = "organizational_unit", nullable = true)
    String organizationalUnit;
    @Column(name = "city", nullable = true)
    String city;
    @Column(name = "state", nullable = true)
    String state;
    @Column(name = "country", nullable = true)
    String country;
    @Column(name = "key_size", nullable = false)
    int keySize;
    @Column(name = "algorithm", nullable = false)
    String algorithm;
    @Column(name = "publicKey", nullable = false)
    byte[] publicKey;
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    CSRStatus status;
}
