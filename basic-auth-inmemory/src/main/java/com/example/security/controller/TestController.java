package com.example.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @PreAuthorize("hasRole('ROLE_GOD')")
    @GetMapping(value = "/test")
    public String test(){
        return "Test Passed";
    }

    @GetMapping(value = "/nosecurity")
    public String noSecurity(){
        return "This page does not require login credentials";
    }
}
