package com.example.security.controller;

import com.example.security.model.JWTRequest;
import com.example.security.service.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JWTAuthenticationController {
    @Autowired
    private DaoAuthenticationProvider authenticationProvider;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/authenticate")
    public String authenticate(@RequestBody JWTRequest request){
        System.out.println("Calling Authenticate !!!");
        Authentication auth = null;
        try {
            auth = authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
        } catch (AuthenticationException e) {
            System.out.println("Authentication Exception : " + e.getMessage());
            throw e;
        }
        return jwtUtil.generateJwtToken(auth);
    }
}
