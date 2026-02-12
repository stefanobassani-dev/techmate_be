package org.techmate.techmate_be.exception;

public class JwtException extends RuntimeException {
    public JwtException(String message) {
        super(message);
    }
}