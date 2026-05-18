package com.example.session10.security;

import com.example.session10.model.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserPrincipal implements UserDetails {
    // user từ database
    private User user;

    // danh sách quyền
    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(User user) {

        this.user = user;

        // chuyển role String -> authority
        this.authorities = List.of(
                new SimpleGrantedAuthority(user.getRole())
        );
    }

    // trả về quyền
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    // trả về password
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    // trả về username
    @Override
    public String getUsername() {
        return user.getUsername();
    }

    // tài khoản hết hạn chưa
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // tài khoản bị khóa chưa
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // credential hết hạn chưa
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // tài khoản enable không
    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }

    // getter user nếu cần dùng thêm
    public User getUser() {
        return user;
    }
}
