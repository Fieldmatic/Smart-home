package com.bsep.smart.home.jpaspecification;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
public class UserFilterSpecification<T> implements Specification<T> {
    private final transient SearchRequest searchRequest;
    private final transient List<FilterRequest> userTypeFilters;

    @Override
    public Predicate toPredicate(@NonNull final Root<T> root, @NonNull final CriteriaQuery<?> query, @NonNull final CriteriaBuilder criteriaBuilder) {
        Predicate predicate = applySearch(root, criteriaBuilder);

        if (!Objects.isNull(userTypeFilters))
            predicate = applyFilters(this.userTypeFilters, root, criteriaBuilder, predicate);

        applySort(root, query, criteriaBuilder);

        return predicate;
    }

    private void applySort(final Root<T> root, final CriteriaQuery<?> query, final CriteriaBuilder criteriaBuilder) {
        final SortRequest sort = searchRequest.getSort();
        final Order sortOrder = sort.getDirection().build(root, criteriaBuilder, sort);
        query.orderBy(sortOrder);
    }

    private Predicate applySearch(final Root<T> root, final CriteriaBuilder criteriaBuilder) {
        final List<Predicate> predicates = searchRequest.getFilters().stream().map(request -> request.getOperator().build(root, criteriaBuilder, request)).toList();
        return criteriaBuilder.or(predicates.toArray(Predicate[]::new));
    }

    private Predicate applyFilters(final List<FilterRequest> filterRequests, final Root<T> root, final CriteriaBuilder criteriaBuilder, Predicate predicate) {
        final List<Predicate> filterPredicates = filterRequests.stream().map(request -> request.getOperator().build(root, criteriaBuilder, request)).toList();
        final Predicate filterPredicate = criteriaBuilder.or(filterPredicates.toArray(Predicate[]::new));
        return criteriaBuilder.and(predicate, filterPredicate);
    }

    public static Pageable getPageable(final int page, final int size) {
        return PageRequest.of(page, size);
    }
}
