package org.techmate.techmate_be.util;

import org.techmate.techmate_be.exception.FingerprintHashingException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

public class HashingUtils {
    public static String hash(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] userFingerprintDigest = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(userFingerprintDigest);
        } catch (NoSuchAlgorithmException e) {
            throw new FingerprintHashingException();
        }
    }
}