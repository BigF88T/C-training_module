package ru.safonov.trainingmodule.BackEnd.security;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.safonov.trainingmodule.BackEnd.models.User;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;

public class UsersDetails implements UserDetails {
    private final User user;

    public UsersDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO: возвращает роль у пользователя
        return Collections.singletonList(new SimpleGrantedAuthority(user.getRole()));
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
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

    //Нужно, чтобы получать данные аутентифицированного пользователя
    public User getUser(){
        return this.user;
    }

    public User setLastVisit() {
        user.setLastVisit(new Date());
        return this.user;
    }
}
