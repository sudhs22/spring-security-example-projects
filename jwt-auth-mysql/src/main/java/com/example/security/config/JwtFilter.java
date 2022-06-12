package com.example.security.config;

import com.example.security.service.CustomUserDetailsService;
import com.example.security.service.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    private static final String AUTHORIZATION_HEADER = "Authorization";
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("Intercepted once per request filter");
        if(request.getHeader(AUTHORIZATION_HEADER) != null) {
            String authHeader = request.getHeader(AUTHORIZATION_HEADER);
            if (authHeader.substring(0, 6).equals("Bearer")) {
System.out.println("Header is present");
                //Validate the JWT Token and get the username
                String username = jwtUtil.validate(authHeader);
System.out.println("username :: " + username);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities()));
System.out.println("DONE");
            }
        }
        filterChain.doFilter(request, response);
    }

}
