package org.techmate.techmate_be.exception;

public class OAuthApiCallException extends RuntimeException {
    public OAuthApiCallException(String message) {
        super(message);
    }
}