package com.example.security.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class JWTRequest implements Serializable {
    private String username;
    private String password;
}
