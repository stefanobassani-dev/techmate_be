package org.techmate.techmate_be.util;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.util.StringUtils;

public class JwtUtils {
    private static final String AUTHORIZATION_HEADER = "Authorization";

    public static String extractTokenFromRequest(HttpServletRequest request) {
        String header = request.getHeader(AUTHORIZATION_HEADER);

        if (header != null) {
            if (StringUtils.hasText(header) && header.startsWith("Bearer "))
                return header.substring(7);
        }

        return null;
    }
}