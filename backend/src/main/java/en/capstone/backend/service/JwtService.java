package en.capstone.backend.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;

@Service
public class JwtService {

    public String createJwtToken(String username) {
        Instant now = Instant.now();
        Date iat = Date.from(now);
        Date exp = Date.from(now.plus(Duration.ofMinutes(30)));
        return Jwts.builder()
                .setClaims(new HashMap<>())
                .setSubject(username)
                .setIssuedAt(iat)
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS256, "secret")
                .compact();
    }
}
