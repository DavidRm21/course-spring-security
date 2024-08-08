package com.curso_api.persistence.entity;

import com.curso_api.persistence.util.Role;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

@Entity
@Table(name = "\"user\"")
@Getter
@Setter
@Builder
public class User implements UserDetails { // UserDetails es el usuario logeado

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String name;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role; // Clase para la base de datos, más dinamica

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == null) return null;
        if (this.role.getPermissions() == null) return null;

        return role.getPermissions().stream()
                .map(Enum::name)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
//            .map( each -> {
//            String permission = each.name();
//            return new SimpleGrantedAuthority(permission);
//        }).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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
