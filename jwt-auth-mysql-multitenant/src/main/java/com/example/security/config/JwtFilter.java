package com.example.security.config;

import com.example.security.model.TenantContext;
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
import java.util.Objects;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String TENANT_HEADER = "X-Tenant";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("Intercepted once per request filter");
        if(Objects.nonNull(request.getHeader(AUTHORIZATION_HEADER))) {
            String authHeader = request.getHeader(AUTHORIZATION_HEADER);
            boolean hasBearerToken = authHeader.substring(0, 6).equals("Bearer");
            if (hasBearerToken) {
                //Validate the JWT Token and get the username
                String username = jwtUtil.validate(authHeader);
                System.out.println("username :: " + username);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        if(Objects.nonNull(request.getHeader(TENANT_HEADER))) {
            String tenant = request.getHeader(TENANT_HEADER);
System.out.println("Tenant :: " + tenant);
            TenantContext.setCurrentTenant(tenant);
        }
        filterChain.doFilter(request, response);
    }

}
