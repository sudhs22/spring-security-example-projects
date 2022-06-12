package com.example.security.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.xml.bind.DatatypeConverter;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class JwtUtil implements Serializable {

    @Value("${jwt.secret}")
    private String secret;


    public String generateJwtToken(Authentication authentication){
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        Claims claims = Jwts.claims().setSubject(principal.getUsername());
        claims.put("auth", principal.getAuthorities());

        String token = Jwts.builder().setSubject(principal.getUsername())
                .signWith(SignatureAlgorithm.HS256, secret)
                .setIssuedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(LocalDateTime.now().plusHours(1).atZone(ZoneId.systemDefault()).toInstant()))
                .setClaims(claims)
                .compact();
        System.out.println("Token :: " + token);
        return token;
    }

    public String validate(String token){
        token = token.substring(6, token.length());
        System.out.println("JWT Token :: " + token);
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(secret))
                .parseClaimsJws(token).getBody();
        System.out.println("ID: " + claims.getId());
        System.out.println("Subject: " + claims.getSubject());
        System.out.println("Issuer: " + claims.getIssuer());
        System.out.println("Expiration: " + claims.getExpiration());
        return claims.getSubject();
    }
}