package org.techmate.techmate_be.exception;

import org.springframework.security.core.AuthenticationException;

public class FingerprintMismatchException extends AuthenticationException {
    public FingerprintMismatchException() {
        super("Fingerprint mismatch");
    }
}