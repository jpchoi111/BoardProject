package com.mycorp.boardproject.member.domain;

import org.springframework.security.core.GrantedAuthority;

/**
 * Enum defining user roles used for authorization.
 */
public enum Role implements GrantedAuthority {
    ROLE_USER,
    ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
