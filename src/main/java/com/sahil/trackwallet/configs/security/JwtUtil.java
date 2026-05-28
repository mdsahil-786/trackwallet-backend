package com.sahil.trackwallet.configs.security;

import com.sahil.trackwallet.enums.Role;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    public String generateToken(String email, Role role) {

        Map<String, Object> claims = new HashMap<>();

        claims.put("email", email);
        claims.put("role", role.name());

        return createToken(claims);
    }

    public String extractUsername(String token) {

        return extractClaims(token)
                .get("email")
                .toString();
    }

    public Date extractExpiration(String token) {

        return extractClaims(token).getExpiration();
    }

    public boolean isTokenExpired(String token) {

        return extractExpiration(token)
                .before(new Date());
    }

    public boolean validateToken(
            String token,
            UserDetails userDetails
    ) {

        try {

            final String email = extractUsername(token);

            boolean expired = isTokenExpired(token);

            return email.equals(userDetails.getUsername())
                    && !expired;

        } catch (Exception e) {

            return false;
        }
    }

    public Claims extractClaims(String token)
            throws JwtException {

        try {

            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

        } catch (SignatureException e) {

            throw new JwtException("Invalid JWT signature");

        } catch (MalformedJwtException e) {

            throw new JwtException("Invalid JWT token");

        } catch (ExpiredJwtException e) {

            throw new JwtException("JWT token has expired");

        } catch (UnsupportedJwtException e) {

            throw new JwtException("Unsupported JWT token");

        } catch (Exception e) {

            throw new JwtException("Invalid JWT token");
        }
    }

    private SecretKey getSigningKey() {

        byte[] bytes =
                secret.getBytes(StandardCharsets.UTF_8);

        return Keys.hmacShaKeyFor(bytes);
    }

    private String createToken(
            Map<String, Object> claims
    ) {

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(claims.get("email").toString())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(
                        new Date(System.currentTimeMillis() + expiration)
                )
                .signWith(getSigningKey())
                .compact();
    }
}
