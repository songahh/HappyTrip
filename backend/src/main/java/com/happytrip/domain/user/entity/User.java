package com.happytrip.domain.user.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Entity
@Table(name="USER")
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails, OAuth2User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length=15, nullable = false)
    @Setter
    private String name;

    @Column(length=30, nullable = false, unique = true)
    private String email;

    @Column
    @Setter
    private String img;

    @Column
    @Setter
    private String refreshToken;

    @CreationTimestamp
    @Column(nullable = false)
    private Timestamp joinDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserType userType;

    @Override
    public <A> A getAttribute(String name) {
        return OAuth2User.super.getAttribute(name);
    }

    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> auth = new ArrayList<>();
        auth.add(new SimpleGrantedAuthority(userType.name()));
        return  auth;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.email;
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
