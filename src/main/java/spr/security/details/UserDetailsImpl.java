package spr.security.details;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import spr.model.State;
import spr.model.User;

public class UserDetailsImpl implements org.springframework.security.core.userdetails.UserDetails {

    private User user;

    public UserDetailsImpl(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
@JsonIgnore
    @Override
    public java.util.Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getRoles();
    }
    @JsonIgnore
    @Override
    public String getPassword() {
        return user.getPassword();
    }
    @JsonIgnore
    @Override
    public String getUsername() {
        return user.getName();
    }
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return !user.getState().equals(State.BANNED);
    }
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return user.getState().equals(State.ACTIVE);
    }
}
