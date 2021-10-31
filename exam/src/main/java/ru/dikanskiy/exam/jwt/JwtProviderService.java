package ru.dikanskiy.exam.jwt;

public interface JwtProviderService {

    String generateToken(String username);

    boolean isTokenValid(String token);

}
