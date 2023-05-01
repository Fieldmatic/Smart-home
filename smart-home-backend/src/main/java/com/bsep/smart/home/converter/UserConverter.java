package com.bsep.smart.home.converter;

import com.bsep.smart.home.dto.info.UserPageInfo;
import com.bsep.smart.home.dto.response.UserResponse;
import com.bsep.smart.home.exception.InvalidSortUserFieldException;
import com.bsep.smart.home.jpaspecification.*;
import com.bsep.smart.home.model.BaseEntity;
import com.bsep.smart.home.model.Person;
import org.apache.logging.log4j.util.Strings;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class UserConverter {

    public static UserResponse toUserResponse(final Person user) {
        return UserResponse.builder()
                .id(user.getId())
                .role(user.getRole().getName())
                .email(user.getEmail())
                .ownedProperties(user.getOwnedProperties().stream().map((BaseEntity::getId)).toList())
                .build();
    }

    public static List<UserResponse> toUsersResponse(final List<Person> users) {
        return users.stream().map(UserConverter::toUserResponse).toList();
    }

    public static UserPageInfo toUserPageInfo(final int page, final int size, final String search,
                                              final String sortField, final SortDirection sortDirection,
                                              final String userRole) {

        final UserPageInfo userPageInfo = UserPageInfo.builder().page(page)
                .size(size)
                .search(Objects.requireNonNullElse(search, Strings.EMPTY))
                .build();

        if (!Objects.isNull(sortField) && !Objects.isNull(sortDirection))
            userPageInfo.setSortRequest(constructSortRequest(sortField, sortDirection));
        if (!Objects.isNull(userRole)) userPageInfo.setUserRoleFilters(constructUserRoleFilterRequest(userRole));

        return userPageInfo;
    }

    private static SortRequest constructSortRequest(final String sortField, final SortDirection sortDirection) {
        if (!(Arrays.asList(UserPageInfo.SORT_FIELDS).contains(sortField)))
            throw new InvalidSortUserFieldException();

        return new SortRequest(sortField, sortDirection);
    }

    private static List<FilterRequest> constructUserRoleFilterRequest(final String userRole) {
        final String[] roles = userRole.split(",");
        return Arrays.stream(roles).map(role -> new FilterRequest(Person.Fields.role, Operator.EQUAL, FieldType.USER_ROLE, role)).toList();
    }
}
