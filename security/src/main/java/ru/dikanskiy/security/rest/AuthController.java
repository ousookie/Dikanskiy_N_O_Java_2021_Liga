package ru.dikanskiy.security.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AuthController {

    @GetMapping
    public String index() {
        return "Index page";
    }

    @GetMapping("/user")
    public String helloUser(HttpServletRequest request) {
        return "Hello, user " + request.getHeader("Authorization");
    }

    @GetMapping("/admin")
    public String helloAdmin(HttpServletRequest request) {
        return "Hello, admin " + request.getHeader("Authorization");
    }

}
