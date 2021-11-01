package ru.dikanskiy.exam.jwt;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtTokenValidationFilter extends OncePerRequestFilter {

    @Setter
    private JwtProviderService jwtProviderService;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        return "/signup".equals(path);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        try {

            String token = request.getHeader("Authorization");

            token = token.replace("Bearer ", "");

            if (jwtProviderService.isTokenValid(token)) {

                Jws<Claims> claimsJws = Jwts.parser()
                        .setSigningKey(jwtSecret).parseClaimsJws(token);

                String username = claimsJws.getBody().getSubject();

                List<Map<String, String>> authorities = (List<Map<String, String>>) claimsJws.getBody().get("authorities");

                List<? extends GrantedAuthority> grantedAuthorities =
                        authorities.stream().map(a ->
                                new SimpleGrantedAuthority(a.get("authority"))).collect(Collectors.toList());

                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        username,
                        null,
                        grantedAuthorities
                );

                SecurityContextHolder.getContext().setAuthentication(authentication);

            }

        } catch (Exception e) {
            throw new JwtException(e.getMessage());
        }

        filterChain.doFilter(request, response);
    }

}
