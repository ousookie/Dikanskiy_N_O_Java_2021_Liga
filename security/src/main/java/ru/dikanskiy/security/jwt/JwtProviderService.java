package ru.dikanskiy.security.jwt;

public interface JwtProviderService {

    String generateToken(String username);

    boolean isTokenValid(String token);

}
