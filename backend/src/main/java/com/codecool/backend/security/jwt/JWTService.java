package com.codecool.backend.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;

import static java.time.temporal.ChronoUnit.DAYS;
@Service
public class JWTService {
    private static final String SECRET_KEY =
            "SECRET_007_SECRET_007_SECRET_007_SECRET_007_SECRET_007_SECRET_007_SECRET_007_SECRET_007_SECRET_007_SECRET_007_SECRET_007_SECRET_007_SECRET";




    public String issueToken(
            String subject) {
        String token = Jwts
                .builder()
                .setSubject(subject)
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(
                        Date.from(
                                Instant.now().plus(1, DAYS)
                        )
                )
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
        return token;
    }
    public String issueTokenEmail(String subject, long amountToAdd, TemporalUnit unit) {
        Date expirationDate = Date.from(Instant.now().plus(amountToAdd, unit));

        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(expirationDate)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    public String getSubject(String token) {
        return getClaims(token).getSubject();
    }

    private Claims getClaims(String token) {
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public boolean isTokenValid(String jwt, String username) {
        String subject = getSubject(jwt);
        return subject.equals(username) && !isTokenExpired(jwt);
    }

    private boolean isTokenExpired(String jwt) {
        Date today = Date.from(Instant.now());
        return getClaims(jwt).getExpiration().before(today);
    }

    public boolean isTokenLinkExpired(String jwt) {
        Date expirationDate = Date.from(Instant.now().plus(10, ChronoUnit.MINUTES));

        return getClaims(jwt).getExpiration().after(expirationDate);
    }
}
