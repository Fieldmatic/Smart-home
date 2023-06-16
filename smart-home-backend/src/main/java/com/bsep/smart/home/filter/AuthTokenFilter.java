package com.bsep.smart.home.filter;

import com.bsep.smart.home.model.Person;
import com.bsep.smart.home.services.fingerprint.GetFingerprintFromCookie;
import com.bsep.smart.home.services.jwt.JwtValidateWithFingerprintAndGetUsername;
import com.bsep.smart.home.services.user.GetUserByEmail;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthTokenFilter extends OncePerRequestFilter {
    private final JwtValidateWithFingerprintAndGetUsername jwtValidateWithFingerprintAndGetUsername;
    private final GetUserByEmail getUserByEmail;
    private final GetFingerprintFromCookie getFingerprintFromCookie;
    private final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);


    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws
            ServletException, IOException {
        final String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String token = header.substring("Bearer ".length());
        final String username = jwtValidateWithFingerprintAndGetUsername.execute(token, getFingerprintFromCookie.execute(request));

        if (username == null) {
            filterChain.doFilter(request, response);
            return;
        }

        final Person user = getUserByEmail.execute(username);
        final List<SimpleGrantedAuthority> authorities = user.getRole().getPermissions().stream().map(permission -> new SimpleGrantedAuthority(permission.name())).toList();
        final UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, token, authorities);

        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request, response);
    }
}
