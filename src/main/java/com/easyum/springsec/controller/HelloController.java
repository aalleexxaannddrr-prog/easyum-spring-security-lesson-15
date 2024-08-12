package com.easyum.springsec.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping
    public String hello() {
        return "hello";
    }
}
