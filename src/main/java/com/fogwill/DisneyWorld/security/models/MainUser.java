package com.fogwill.DisneyWorld.security.models;

import java.util.Collection;
import java.util.List;
import java.util.stream.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MainUser implements UserDetails {
    private String name;
    private String username;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    

    public MainUser(String name, String username, String email, String password, Collection<?extends GrantedAuthority> authorities) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static MainUser build(User user){
        List<GrantedAuthority> authorities = 
        user.getRoles().stream().map(role -> new SimpleGrantedAuthority(
            role.getRoleName().name())).collect(Collectors.toList());
            return new MainUser(user.getName(),user.getUsername(),user.getEmail(),user.getPassword(),authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public String getUsername() {
        return username;
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

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }
   
    
}
