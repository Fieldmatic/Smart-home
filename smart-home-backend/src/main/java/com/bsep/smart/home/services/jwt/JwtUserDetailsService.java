package com.bsep.smart.home.services.jwt;

import com.bsep.smart.home.model.Person;
import com.bsep.smart.home.services.user.GetUserByEmail;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {
    private final GetUserByEmail getUserByEmail;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final Person user = getUserByEmail.execute(username);

        final List<SimpleGrantedAuthority> authorities = user.getRole().getPermissions().stream().map(permission -> new SimpleGrantedAuthority(permission.name())).toList();

        return new org.springframework.security.core.userdetails.User(username, user.getPasswordHash(), authorities);
    }
}

