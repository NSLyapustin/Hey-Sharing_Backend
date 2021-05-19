package ru.itis.sharing.security.UserDetails;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.itis.sharing.model.ApplicationUser;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

public class ApplicationUserDetails implements UserDetails {

    @Getter
    private ApplicationUser applicationUser;

    public ApplicationUserDetails(ApplicationUser applicationUser) {
        this.applicationUser = applicationUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(applicationUser.getRole().toString()));
    }

    @Override
    public String getPassword() {
        return applicationUser.getPassword();
    }

    @Override
    public String getUsername() {
        return applicationUser.getUsername();
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
