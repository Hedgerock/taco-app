package com.hedgerock.spirng.spring_in_action.view_taco_app.model.security;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@Table(name="users")
public class User implements UserDetails {
    private static final Long SERIAL_VERSION_U_ID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name="username")
    private final String username;

    @Column(name="password")
    private final String password;

    @Column(name="fullname")
    private final String fullName;

    @Column(name="street")
    private final String street;

    @Column(name="city")
    private final String city;

    @Column(name="state")
    private final String state;

    @Column(name="zip")
    private final String zip;

    @Column(name="phone_number")
    private final String phoneNumber;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
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
}
