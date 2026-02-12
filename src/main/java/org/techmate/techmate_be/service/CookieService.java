package org.techmate.techmate_be.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class CookieService {
    @Value("${jwt.access_expiration}")
    private Long accessExpiration;

    @Value("${jwt.refresh_expiration}")
    private Long refreshExpiration;

    public ResponseCookie createJwtCookie(String token, boolean isRefreshToken) {
        Long millis = isRefreshToken ? refreshExpiration : accessExpiration;
        String key = isRefreshToken ? "refresh_token" : "access_token";
        String path = isRefreshToken ? "/api/auth/refresh" : "/";

        return this.createCookie(key, token, path, Duration.ofMillis(millis));
    }

    public ResponseCookie createCookie(String key, String value, String path, Duration maxAge) {
        ResponseCookie.ResponseCookieBuilder cookieBuilder = ResponseCookie
                .from(key)
                .value(value)
                .httpOnly(true)
                .path(path);

        if (maxAge != null) {
            cookieBuilder = cookieBuilder.maxAge(maxAge);
        }
        return cookieBuilder
                .sameSite("Strict")
                .secure(false)
                .build();
    }
}