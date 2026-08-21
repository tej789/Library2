package com.example.library.security;

import com.example.library.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;


@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public JwtAuthenticationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
           HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");



        if (authHeader != null && authHeader.startsWith("Bearer ")) {

            String token = authHeader.substring(7);


            //////////////////////////////////////////////
        // for adding token expiry we need this
//            if (jwtService.isTokenExpired(token)) {
//                // Short-circuit the request and return a structured 401 error
//                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                response.setContentType("application/json");
//                response.getWriter().write("{\"error\": \"Unauthorized\", \"message\": \"Token has expired or is invalid\"}");
//                return; // Stop the filter chain execution right here
//            }
           ///////////////////////////////////////////////
            String username = jwtService.extractUsername(token);
            String role = jwtService.extractRole(token);

            List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_"+role));

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            username,
                            null,
                            authorities
                    );

            System.out.println("Authenticated user: " + username + ", Role: " + role +"Authority :"+ authorities);


            // here we store current  request's authenticated user
            SecurityContextHolder.getContext()
                    .setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);


    }
}

