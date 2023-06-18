package com.bsep.smart.home.jpaspecification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public enum Operator {
    LIKE {
        @Override
        public <T> Predicate build(final Root<T> root, final CriteriaBuilder criteriaBuilder, final FilterRequest filterRequest) {
            final Expression<String> key = root.get(filterRequest.getKey());

            final String pattern = "%".concat(filterRequest.getValue().toString().toLowerCase()).concat("%");

            return criteriaBuilder.like(criteriaBuilder.lower(key), pattern);
        }
    },

    EQUAL {
        @Override
        public <T> Predicate build(final Root<T> root, final CriteriaBuilder criteriaBuilder, final FilterRequest filterRequest) {
            final Object value = filterRequest.getFieldType().parse(filterRequest.getValue().toString());
            final Expression<?> key = root.get(filterRequest.getKey());
            return criteriaBuilder.and(criteriaBuilder.equal(key, value));
        }
    };

    public abstract <T> Predicate build(Root<T> root, CriteriaBuilder criteriaBuilder, FilterRequest filterRequest);
}
