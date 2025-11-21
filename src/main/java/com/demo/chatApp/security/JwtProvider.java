package com.demo.chatApp.security;

import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtProvider {

    private final Dotenv dotenv;

    private String secretKey;
    private long tokenExpiration;

    @PostConstruct
    void init() {
        secretKey = dotenv.get("SECRET_KEY");
        tokenExpiration = Long.parseLong(dotenv.get("JWT_EXPIRATION"));
    }

    public String generateToken(UserDetails userDetails) {
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        return Jwts.builder()
                .subject(userDetails.getUsername())
                .claim("roles", roles)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + tokenExpiration))
                .signWith(getSignInKey())
                .compact();
    }


    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean validateToken(String token) {
        try{
            JwtParser parser = getParser(token);
            parser.parseSignedClaims(token);
            return true;
        }catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    private JwtParser getParser(String token) {
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build();
    }

    public String getUsernameToken(String token) {
        JwtParser parser = getParser(token);
        return parser.parseSignedClaims(token).getPayload().getSubject();
    }

    public List<String>getRoleFromToken(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        Object roleObj = claims.get("roles");
        if (roleObj instanceof List<?>){
            return ((List<?>) roleObj).stream()
                    .map(Object::toString)
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public boolean isTokenExpired(String token) {
        try{
            Date exp = Jwts.parser()
                    .verifyWith(getSignInKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getExpiration();
            return exp.before(new Date());
        }catch (ExpiredJwtException e) {
            return true;
        }
    }

}
