package com.bsep.smart.home.dto.info;

import com.bsep.smart.home.jpaspecification.FilterRequest;
import com.bsep.smart.home.jpaspecification.SortRequest;
import com.bsep.smart.home.model.Person;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserPageInfo {
    public final static String[] SORT_FIELDS = {Person.Fields.email, Person.Fields.role};

    public final static String[] SEARCH_FIELDS = {Person.Fields.email};

    private int page;

    private int size;

    private String search;

    private SortRequest sortRequest;

    private List<FilterRequest> userRoleFilters;
}
