package org.techmate.techmate_be.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.techmate.techmate_be.security.authentication.JwtAuthentication;
import org.techmate.techmate_be.util.CookieUtils;
import org.techmate.techmate_be.util.EndpointUtils;
import org.techmate.techmate_be.util.JwtUtils;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private final AuthenticationManager authenticationManager;

    public JwtFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        if (EndpointUtils.isEndpointWhitelisted(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = JwtUtils.extractTokenFromRequest(request);
        String hashedFingerprint = CookieUtils.extractCookieValueFromRequest(request, "__NotSecure-Fgp");
        if (token == null || hashedFingerprint == null) {
            filterChain.doFilter(request, response);
            return;
        }

        JwtAuthentication authRequest = JwtAuthentication.unauthenticated(token, hashedFingerprint);
        Authentication authToken = this.authenticationManager.authenticate(authRequest);

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authToken);
        SecurityContextHolder.setContext(context);

        filterChain.doFilter(request, response);
    }
}