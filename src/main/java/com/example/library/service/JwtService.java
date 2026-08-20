//package com.example.library.service;
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import javax.crypto.SecretKey;
//
//@Service
//public class JwtService {
//
//    @Value("${jwt.secret}")
//    private String secret;
//
//    public String generateToken(String username,Role role) {
//
//        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes());
//
//        return Jwts.builder()
//                .subject(username)
//                .claim("role",role)    // for role based access
//                .signWith(key)
//                .compact();
//    }
//
//    public String extractUsername(String token) {
//
//        return Jwts.parser()
//                .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
//                .build()
//                .parseSignedClaims(token)
//                .getPayload()
//                 .getSubject();
//    }
//
//    public String extractRole(String token) {
//
//        return Jwts.parser()
//                .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
//                .build()
//                .parseSignedClaims(token)
//                .getPayload()
//                  .get("role", String.class);
//    }
//}

package com.example.library.service;

import com.example.library.model.Role;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    private static final long JWT_EXPIRATION_MS = 1000000;

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(String username, Role role) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        Date expiryDate = new Date(nowMillis + JWT_EXPIRATION_MS);

        return Jwts.builder()
                .subject(username)
                .claim("role", role)    // For role-based access
                .issuedAt(now)          // Sets creation time
                .expiration(expiryDate)  // Sets expiration time
                .signWith(getSigningKey())
                .compact();
    }

    public String extractUsername(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .getSubject();
        } catch (ExpiredJwtException e) {
            // Token is expired; returns the subject from the expired token claims
            return e.getClaims().getSubject();
        } catch (JwtException e) {
            return null; // Invalid token structure or signature
        }
    }

    public String extractRole(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .get("role", String.class);
        } catch (ExpiredJwtException e) {
            // Token is expired; extracts the claim safely anyway
            return e.getClaims().get("role", String.class);
        } catch (JwtException e) {
            return null;
        }
    }

    // New helper method to verify if a token is completely valid and active
    public boolean isTokenExpired(String token) {
        try {
            Jwts.parser()
                    .verifyWith(getSigningKey())
                    .build()
                    .parseSignedClaims(token);
            return false; // Parsing succeeded, token is active
        } catch (ExpiredJwtException e) {
            return true;  // Thrown specifically when token has expired
        } catch (JwtException e) {
            return true;  // Any other parsing issue behaves as an invalid token
        }
    }
}
