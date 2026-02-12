package org.techmate.techmate_be.exception;

public class FingerprintHashingException extends RuntimeException {
    public FingerprintHashingException() {
        super("Fingerprint hashing failed");
    }
}