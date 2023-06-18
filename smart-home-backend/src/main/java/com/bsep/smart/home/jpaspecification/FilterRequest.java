package com.bsep.smart.home.jpaspecification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilterRequest {
    private String key;

    private Operator operator;

    private FieldType fieldType;

    private transient Object value;
}
