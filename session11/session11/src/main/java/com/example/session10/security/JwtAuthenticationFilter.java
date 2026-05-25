package com.example.session10.security;

import com.example.session10.service.UserDetailsServiceCustom;
import com.example.session10.utility.JwtProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;
    private final UserDetailsServiceCustom userDetailsService;

    public JwtAuthenticationFilter(JwtProvider jwtProvider, UserDetailsServiceCustom userDetailsService) {
        this.jwtProvider = jwtProvider;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        //System.out.println("AUTH HEADER = " + request.getHeader("Authorization"));
        // lấy Authorization header
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;

        // kiểm tra Bearer token
        if (authHeader != null && authHeader.startsWith("Bearer ")){
            token = authHeader.substring(7);
            username = jwtProvider.getUsernameFromToken(token);
        }

        // nếu có username và chưa authenticate
        if (username != null &&
                SecurityContextHolder.getContext().getAuthentication() == null){
            // load user từ database
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            // validate token
            if (jwtProvider.validateToken(token)){
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // set vào security context
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        // cho request đi tiếp
        filterChain.doFilter(request,response);
    }

}
