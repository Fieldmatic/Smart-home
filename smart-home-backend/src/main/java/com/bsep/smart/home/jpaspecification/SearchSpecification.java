package com.bsep.smart.home.jpaspecification;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.NonNull;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
public class SearchSpecification<T> implements Specification<T> {
    private final transient SearchRequest searchRequest;

    @Override
    public Predicate toPredicate(@NonNull final Root<T> root, @NonNull final CriteriaQuery<?> query, @NonNull final CriteriaBuilder criteriaBuilder) {
        final List<Predicate> predicates = new ArrayList<>();

        for (final FilterRequest filterRequest : searchRequest.getFilters()) {
            predicates.add(filterRequest.getOperator().build(root, criteriaBuilder, filterRequest));
        }

        final Predicate predicate = criteriaBuilder.or(predicates.toArray(Predicate[]::new));

        final SortRequest sort = searchRequest.getSort();

        if (!Objects.isNull(sort)) {
            final Order sortOrder = sort.getDirection().build(root, criteriaBuilder, sort);

            query.orderBy(sortOrder);
        }

        return predicate;
    }

    public static Pageable getPageable(final int page, final int size) {
        return PageRequest.of(page, size);
    }
}
