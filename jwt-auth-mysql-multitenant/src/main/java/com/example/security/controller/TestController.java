package com.example.security.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping(value = "/testwithrole")
    @Secured("ROLE_ADMIN")
    public String testWithRole(){
        return "Successfully called with Role";
    }

    @GetMapping(value = "/testwithoutrole")
    public String testWithoutRole(){
        return "Successfully called without Role";
    }
}
