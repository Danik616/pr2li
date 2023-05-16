package com.pruebas.liti.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import reactor.core.publisher.Mono;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

@Service
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    private final UserServices userDetailsService;

    public Mono<Authentication> getAuthentication(String token) {
        Claims claims = extractAllClaims(token);
        return userDetailsService.findByUsername(claims.getSubject())
                .map(userDetails -> new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()));
                
    }

    private SecretKey generateKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public JwtUtil(UserServices userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public Mono<String> generateToken(String username) {
        return userDetailsService.findByUsername(username)
                .flatMap(userDetails -> Mono.just(createToken(userDetails)));
    }

    private String createToken(UserDetails userDetails) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + expiration * 1000);

        return Jwts.builder()
                .claim("roles", userDetails.getAuthorities())
                .setSubject(userDetails.getUsername())
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(generateKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Mono<Boolean> validateToken(String token) {
        String username = extractUsername(token);
        return userDetailsService.findByUsername(username)
            .flatMap(userDetails -> {
                boolean isTokenValid = username.equals(userDetails.getUsername()) && !isTokenExpired(token);
                return Mono.just(isTokenValid);
            });
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(generateKey()).build().parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }


    public String getUsernameFromToken(String token) {
        return extractAllClaims(token).getSubject();
    }

    public Claims getClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(generateKey()).build().parseClaimsJws(token).getBody();
    }
}
