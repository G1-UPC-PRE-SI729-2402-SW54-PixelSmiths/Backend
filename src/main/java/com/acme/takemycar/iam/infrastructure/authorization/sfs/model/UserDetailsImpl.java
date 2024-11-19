package com.acme.takemycar.iam.infrastructure.authorization.sfs.model;

import com.acme.takemycar.iam.domain.model.aggregates.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

import java.util.Collections;
import java.util.stream.Collectors;

@Getter
@EqualsAndHashCode
public class UserDetailsImpl implements UserDetails {

    private final String username;

    @JsonIgnore
    private final String password;

    private final boolean accountNonExpired;
    private final boolean accountNonLocked;
    private final boolean credentialsNonExpired;
    private final boolean enabled;


    public UserDetailsImpl(String email, String password){
        this.username = email;
        this.password = password;
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
    }

    public static UserDetailsImpl build(User user){
        return new UserDetailsImpl(
                user.getEmail(),
                user.getPassword()
        );
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Devuelve una lista vacía porque no se están utilizando roles
        return Collections.emptyList();
    }

}
