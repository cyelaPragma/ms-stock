package com.acelera.ti.stock.infrastructure.helpers.jwt;

import com.acelera.ti.stock.infrastructure.helpers.jwt.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    private final List<String> excludedPrefixes = Arrays.asList("/auth/**", "/swagger-ui/**");
    private final AntPathMatcher pathMatcher = new AntPathMatcher();
    @Autowired
    private JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String token = getToken(req);
            if (token != null && jwtProvider.validateToken(token) && jwtProvider.getNombreUsuarioFromToken(token) != null) {
                System.out.println("valida token");
                UserDetails userDetails = getUserDetails(token);
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities()
                );
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
            filterChain.doFilter(req, res);
        } catch (Exception e) {
            log.error("fail en el método doFilter " + e);
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
        }
    }

    private UserDetails getUserDetails(String token) {
        UserDetails userDetails = new User(0L, jwtProvider.getNombreUsuarioFromToken(token), jwtProvider.getRole(token));
        return userDetails;
    }

    private String getToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null) {
            return header.replace("Bearer ", "");
        }
        return null;
    }
}
