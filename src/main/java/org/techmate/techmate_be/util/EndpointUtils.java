package org.techmate.techmate_be.util;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.Arrays;

public class EndpointUtils {
    private static final String[] ENDPOINT_WHITELIST = {
            "/api/healthcheck",
            "/api/auth/login",
            "/api/auth/refresh"
    };

    public static boolean isEndpointWhitelisted(HttpServletRequest request) {
        return Arrays.stream(ENDPOINT_WHITELIST)
                .map(AntPathRequestMatcher::antMatcher)
                .anyMatch(matcher -> matcher.matches(request));
    }
}