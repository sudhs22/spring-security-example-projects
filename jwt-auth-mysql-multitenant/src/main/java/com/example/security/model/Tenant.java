package com.example.security.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Tenant {
    private String id;
    private String url;
    private String username;
    private String password;
    private String driverClassName;
}
