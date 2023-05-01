package com.bsep.smart.home.services.user;

import com.bsep.smart.home.converter.UserConverter;
import com.bsep.smart.home.dto.info.UserPageInfo;
import com.bsep.smart.home.dto.response.PageResponse;
import com.bsep.smart.home.dto.response.UserResponse;
import com.bsep.smart.home.exception.RoleNotFoundException;
import com.bsep.smart.home.jpaspecification.*;
import com.bsep.smart.home.model.BaseEntity;
import com.bsep.smart.home.model.Person;
import com.bsep.smart.home.model.Role;
import com.bsep.smart.home.repository.PersonRepository;
import com.bsep.smart.home.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetUsers {
    private final PersonRepository personRepository;
    private final RoleRepository roleRepository;

    public PageResponse<UserResponse> execute(UserPageInfo userPageInfo) {
        convertUserRoleNameToId(userPageInfo);

        final List<FilterRequest> searchFilters = constructFiltersForSearch(userPageInfo, UserPageInfo.SEARCH_FIELDS);

        final SortRequest defaultSortRequest = constructDefaultSortRequest();

        final SearchRequest searchRequest = constructSearchRequest(userPageInfo, searchFilters, defaultSortRequest);

        final UserFilterSpecification<Person> specification = new UserFilterSpecification<>(searchRequest, userPageInfo.getUserRoleFilters());

        final Pageable pageable = UserFilterSpecification.getPageable(searchRequest.getPage(), searchRequest.getSize());

        final Page<Person> usersPage = personRepository.findAll(specification, pageable);

        final List<UserResponse> userResponses = usersPage.getContent().stream().map(UserConverter::toUserResponse).toList();

        return PageResponse.<UserResponse>builder()
                .pageNumber(userPageInfo.getPage())
                .pageSize(userPageInfo.getSize())
                .items(userResponses)
                .totalPages(usersPage.getTotalPages())
                .numberOfElements(usersPage.getNumberOfElements())
                .build();
    }

    private void convertUserRoleNameToId(final UserPageInfo userPageInfo) {
        if (userPageInfo.getUserRoleFilters() == null) return;
        for (final FilterRequest roleFilter : userPageInfo.getUserRoleFilters()) {
            final String name = roleFilter.getValue().toString();
            final Optional<Role> role = roleRepository.getByName(name);

            if (role.isEmpty()) {
                throw new RoleNotFoundException();
            }

            roleFilter.setValue(role.get().getId());
        }
    }

    private static SortRequest constructDefaultSortRequest() {
        return SortRequest.builder()
                .key(BaseEntity.Fields.createdAt)
                .direction(SortDirection.DESC)
                .build();
    }

    private static SearchRequest constructSearchRequest(final UserPageInfo userPageInfo, final List<FilterRequest> searchFilters, final SortRequest defaultSortRequest) {
        return SearchRequest.builder()
                .filters(searchFilters)
                .page(userPageInfo.getPage())
                .size(userPageInfo.getSize())
                .sort(Objects.requireNonNullElse(userPageInfo.getSortRequest(), defaultSortRequest))
                .build();
    }

    private static List<FilterRequest> constructFiltersForSearch(final UserPageInfo userPageInfo, final String[] searchFields) {
        return Arrays.stream(searchFields)
                .map((field) -> FilterRequest.builder()
                        .key(field)
                        .operator(Operator.LIKE)
                        .fieldType(FieldType.STRING)
                        .value(userPageInfo.getSearch())
                        .build())
                .toList();
    }
}
