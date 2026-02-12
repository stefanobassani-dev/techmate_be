package org.techmate.techmate_be.security.authentication;

import lombok.Getter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

@Getter
public class JwtAuthentication implements Authentication {

    private final Object principal;
    private final String token;
    private final String hashedFingerprint;
    private boolean authenticated;

    private JwtAuthentication(String token, String hashedFingerprint, Object principal,  boolean authenticated) {
        this.token = token;
        this.hashedFingerprint = hashedFingerprint;
        this.principal = principal;
        setAuthenticated(authenticated);
    }

    public static JwtAuthentication unauthenticated(String token, String hashedFingerprint) {
        return new JwtAuthentication(token, hashedFingerprint, null, false);
    }

    public static JwtAuthentication authenticated(String token, String hashedFingerprint, Object principal) {
        return new JwtAuthentication(token, hashedFingerprint, principal, true);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public Object getCredentials() {
        return this.token;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.authenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return "";
    }
}