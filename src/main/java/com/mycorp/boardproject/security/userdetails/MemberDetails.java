package com.mycorp.boardproject.security.userdetails;

import com.mycorp.boardproject.member.domain.Member;
import com.mycorp.boardproject.member.domain.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Implementation of Spring Security's UserDetails interface
 * that represents authenticated user's information.
 */
public class MemberDetails implements UserDetails {

    private final Member member;

    public MemberDetails(Member member) {
        this.member = member;
    }

    /**
     * Returns authorities granted to the user.
     * Converts Role enum to a GrantedAuthority with "ROLE_" prefix.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Role role = member.getRole();
        // Convert Role enum to Spring Security authority string format "ROLE_ROLENAME"
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * Returns the wrapped Member entity.
     */
    public Member getMember() {
        return member;
    }
}
