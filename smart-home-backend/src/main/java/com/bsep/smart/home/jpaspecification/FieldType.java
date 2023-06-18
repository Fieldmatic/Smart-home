package com.bsep.smart.home.jpaspecification;


import com.bsep.smart.home.model.Role;

public enum FieldType {
    STRING {
        @Override
        public Object parse(final String value) {
            return value;
        }
    },
    USER_ROLE {
        @Override
        public Object parse(final String value) {
            final Role role = new Role();
            role.setId(java.util.UUID.fromString(value));
            return role;
        }
    };

    public abstract Object parse(String value);
}