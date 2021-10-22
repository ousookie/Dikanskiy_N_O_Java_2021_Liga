package ru.dikanskiy.security.jwt;

public class UsernameAndPasswordAuthorizationRequest {

    private String username;

    private String password;

    public UsernameAndPasswordAuthorizationRequest() {
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
