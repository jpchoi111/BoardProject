package com.mycorp.boardproject.member.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * Represents a member (user) entity in the application.
 * Implements Spring Security's UserDetails interface for authentication and authorization.
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
@Table(name = "members")
public class Member implements UserDetails {

    /**
     * Unique identifier for each member.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * First name of the member.
     */
    @Column(nullable = false, length = 30)
    private String firstName;

    /**
     * Last name of the member.
     */
    @Column(nullable = false, length = 30)
    private String lastName;

    /**
     * Email address used as the login ID.
     */
    @Column(nullable = false, unique = true, length = 50)
    private String email;

    /**
     * Encrypted password of the member.
     */
    @Column(nullable = false)
    private String password;

    /**
     * Residential address of the member.
     */
    @Column(nullable = false, length = 100)
    private String address;

    /**
     * Role of the member (e.g., USER, ADMIN).
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    /**
     * Returns granted authorities (roles) of the user.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(role);
    }

    /**
     * Returns the username used to authenticate the user.
     * In this case, the email is used as the username.
     */
    @Override
    public String getUsername() {
        return email;
    }

    /**
     * Indicates whether the user's account has expired.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is locked or unlocked.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indicates whether the user's credentials (password) have expired.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is enabled or disabled.
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
