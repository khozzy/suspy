package com.pwr.teamfinder.dto;

import java.util.Collection;
import java.util.HashSet;

import com.pwr.teamfinder.domain.Role;
import com.pwr.teamfinder.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContext;


public class UserDetailsImpl implements UserDetails {


    private User user;

    public UserDetailsImpl(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>(1);
        if(user.getRole().equals(Role.ADMIN))
            authorities.add(new SimpleGrantedAuthority("ADMIN"));
        if(user.getRole().equals(Role.GYM_OWNER))
            authorities.add(new SimpleGrantedAuthority("GYM_OWNER"));
        if(user.getRole().equals(Role.SPORTSMAN))
            authorities.add(new SimpleGrantedAuthority("SPORTSMAN"));
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
