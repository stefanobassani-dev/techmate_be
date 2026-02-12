package org.techmate.techmate_be.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.techmate.techmate_be.model.entity.User;
import org.techmate.techmate_be.exception.JwtException;
import org.techmate.techmate_be.util.HashingUtils;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    @Value("${jwt.key}")
    private String key;

    @Value("${jwt.access_expiration}")
    private Long accessExpiration;

    @Value("${jwt.refresh_expiration}")
    private Long refreshExpiration;

    @Value("${jwt.issuer}")
    private String issuer;

    public String generateToken(User user, boolean isRefreshToken, String userFingerprint) {
        Long expiration = isRefreshToken ? refreshExpiration : accessExpiration;
        Date expirationDate = new Date(System.currentTimeMillis() + expiration);

        Map<String, Object> claims = new HashMap<>();
        if (!isRefreshToken) {
            claims.put("user_fingerprint", HashingUtils.hash(userFingerprint));
        }

        return Jwts
                .builder()
                .signWith(this.getSigningKey())
                .subject(user.getEmail())
                .issuer(issuer)
                .claims(claims)
                .issuedAt(new Date())
                .expiration(expirationDate)
                .compact();
    }

    public String generateToken(User user, boolean isRefreshToken) {
        return this.generateToken(user, isRefreshToken, null);
    }

    public Claims parseToken(String token) {
        try {
            return Jwts
                    .parser()
                    .verifyWith(this.getSigningKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (Exception e) {
            throw new JwtException(e.getMessage());
        }
    }

    private SecretKey getSigningKey() {
        byte[] decode = Decoders.BASE64.decode(key);
        return Keys.hmacShaKeyFor(decode);
    }
}