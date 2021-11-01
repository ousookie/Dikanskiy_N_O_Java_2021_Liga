package ru.dikanskiy.exam.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtUsernameAndPasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    private final JwtProviderService jwtProviderService;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {

        try {

            UsernameAndPasswordAuthorizationRequest usernameAndPasswordAuthorizationRequest =
                    new ObjectMapper().readValue(request.getInputStream(), UsernameAndPasswordAuthorizationRequest.class);

            Authentication authenticate =
                    new UsernamePasswordAuthenticationToken(usernameAndPasswordAuthorizationRequest.getUsername(),
                            usernameAndPasswordAuthorizationRequest.getPassword());


            return authenticationManager.authenticate(authenticate);

        } catch (Exception exception) {
            throw new RuntimeException();
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        String username = authResult.getName();

        response.setHeader("Authorization", "Bearer " + jwtProviderService.generateToken(username));

    }

}
