package org.techmate.techmate_be.exception;

public class OAuthProviderNotFoundException extends RuntimeException {
    public OAuthProviderNotFoundException(String provider) {
        super("OAuth provider not found: " + provider);
    }
}