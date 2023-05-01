package com.bsep.smart.home.jpaspecification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SortRequest {
    private String key;
    private SortDirection direction;
}
