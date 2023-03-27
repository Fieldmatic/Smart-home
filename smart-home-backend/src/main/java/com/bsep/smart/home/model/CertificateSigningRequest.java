package com.bsep.smart.home.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "csr")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
public class CertificateSigningRequest extends BaseEntity {

    @Column(name = "csr", nullable = false)
    @NotNull
    byte[] csr;

    @Column(nullable = false)
    String subject;
}
