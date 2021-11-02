package ru.dikanskiy.exam.jwt;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.dikanskiy.exam.persistance.repositories.PersonRepository;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class JwtProviderServiceImplementation implements JwtProviderService {

    private final PersonRepository personRepository;

    @Value("${jwt.secret}")
    private String jwtSecret;

    public String generateToken(String username) {
        Map<String, Object> header = new HashMap<>();
        header.put("typ", "JWT");
        Date date = Date.from(Instant.from(LocalDate.now().plusDays(7)
                .atStartOfDay(ZoneId.systemDefault()).toInstant()));
        return Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(date)
                .setSubject(username)
                .setHeader(header)
                .claim("authorities",
                        personRepository.findPersonByUsername(username).get().getAuthorities())
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException expEx) {
            System.out.println("Token expired");
        } catch (UnsupportedJwtException unsEx) {
            System.out.println("Unsupported jwt");
        } catch (MalformedJwtException mjEx) {
            System.out.println("Malformed jwt");
        } catch (SignatureException sEx) {
            System.out.println("Invalid signature");
        } catch (Exception e) {
            System.out.println("Invalid token");
        }
        return false;
    }

}
