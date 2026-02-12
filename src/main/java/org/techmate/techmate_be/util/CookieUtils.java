package org.techmate.techmate_be.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Cookie;

public class CookieUtils {

    public static String extractCookieValueFromRequest(HttpServletRequest request, String key) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) return "";

        String token = null;
        for (Cookie cookie : cookies) {
            if (key.equals(cookie.getName())) {
                token = cookie.getValue();
            }
        }

        return token;
    }
}