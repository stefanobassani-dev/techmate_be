package org.techmate.techmate_be.security.provider;

import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.techmate.techmate_be.exception.FingerprintMismatchException;
import org.techmate.techmate_be.model.entity.User;
import org.techmate.techmate_be.repository.UserRepository;
import org.techmate.techmate_be.security.authentication.JwtAuthentication;
import org.techmate.techmate_be.security.userDetails.UserDetailsImpl;
import org.techmate.techmate_be.service.JwtService;
import org.techmate.techmate_be.util.HashingUtils;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public JwtAuthenticationProvider(JwtService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JwtAuthentication jwtAuthentication = (JwtAuthentication) authentication;
        String token = (String) jwtAuthentication.getCredentials();
        String hashedFingerprint = jwtAuthentication.getHashedFingerprint();

        Claims claims = this.jwtService.parseToken(token);
        String userFingerprint = claims.get("user_fingerprint", String.class);

        if (!userFingerprint.equals(HashingUtils.hash(hashedFingerprint)))
            throw new FingerprintMismatchException();


        String userEmail = claims.getSubject();

        User user = this.userRepository
                .findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));


        return JwtAuthentication.authenticated(token, hashedFingerprint, new UserDetailsImpl(user));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthentication.class.isAssignableFrom(authentication);
    }
}