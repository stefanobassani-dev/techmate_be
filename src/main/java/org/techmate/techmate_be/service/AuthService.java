package org.techmate.techmate_be.service;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.techmate.techmate_be.exception.UnauthorizedException;
import org.techmate.techmate_be.exception.UserNotFoundException;
import org.techmate.techmate_be.oauth.OAuthUser;
import org.techmate.techmate_be.model.dto.request.LoginRequest;
import org.techmate.techmate_be.model.dto.response.AuthResponse;
import org.techmate.techmate_be.model.dto.response.user.UserResponse;
import org.techmate.techmate_be.model.entity.User;
import org.techmate.techmate_be.oauth.client.IOAuthProviderClient;
import org.techmate.techmate_be.oauth.factory.OAuthProviderFactory;
import org.techmate.techmate_be.repository.UserRepository;
import org.techmate.techmate_be.security.authentication.JwtAuthentication;
import org.techmate.techmate_be.security.userDetails.UserDetailsImpl;

import java.security.SecureRandom;
import java.time.Duration;
import java.util.HexFormat;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtService jwtService;
    private final OAuthProviderFactory oauthProviderFactory;
    private final UserRepository userRepository;
    private final CookieService cookieService;

    public AuthResponse login(LoginRequest loginRequest, HttpServletResponse httpResponse) {
        IOAuthProviderClient client = this.oauthProviderFactory.getClient(loginRequest.getProvider().getValue());
        OAuthUser oAuthUser = client.getUser(loginRequest.getCode());

        Optional<User> userOptional = this.userRepository.findByEmail(oAuthUser.getEmail());

        User user;

        if (userOptional.isPresent()) {
            user = userOptional.get();
            user.setLastProvider(loginRequest.getProvider());
        } else {
            User newUser = new User();
            newUser.setEmail(oAuthUser.getEmail());
            newUser.setAvatarUrl(oAuthUser.getAvatarUrl());
            newUser.setLastProvider(loginRequest.getProvider());
            user = this.userRepository.save(newUser);
        }

        String userFingerprint = this.createUserFingerprint();

        String accessToken = this.jwtService.generateToken(user, false, userFingerprint);
        ResponseCookie fingerprintCookie = this.cookieService
                .createCookie("__NotSecure-Fgp", userFingerprint, "/", null);
        httpResponse.addHeader(HttpHeaders.SET_COOKIE, fingerprintCookie.toString());

        String refreshToken = this.jwtService.generateToken(user, true);
        httpResponse.addHeader(HttpHeaders.SET_COOKIE,
                this.cookieService.createJwtCookie(refreshToken, true).toString());

        UserResponse userResponse = UserResponse.toUserResponse(user);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setUser(userResponse);
        authResponse.setToken(accessToken);
        authResponse.setLastProvider(loginRequest.getProvider());

        return authResponse;
    }

    public UserResponse getAuthenticatedUserDto() {
        return UserResponse.toUserResponse(this.getAuthenticatedUser());
    }

    public User getAuthenticatedUser() {
        JwtAuthentication authentication = (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return this.userRepository
                .findByEmail(userDetails.getEmail())
                .orElseThrow(() -> new UserNotFoundException(userDetails.getEmail()));
    }

    public AuthResponse refresh(String refreshToken, HttpServletResponse httpResponse) {
        if (refreshToken == null) throw new UnauthorizedException();
        Claims claims = this.jwtService.parseToken(refreshToken);
        String userEmail = claims.getSubject();

        User user = this.userRepository
                .findByEmail(userEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String userFingerprint = this.createUserFingerprint();

        String accessToken = this.jwtService.generateToken(user, false, userFingerprint);
        ResponseCookie fingerprintCookie = this.cookieService
                .createCookie("__NotSecure-Fgp", userFingerprint, "/", null);
        httpResponse.addHeader(HttpHeaders.SET_COOKIE, fingerprintCookie.toString());

        AuthResponse authResponse = new AuthResponse();
        authResponse.setUser(UserResponse.toUserResponse(user));
        authResponse.setToken(accessToken);

        return authResponse;
    }

    public void logout(HttpServletResponse httpResponse) {
        httpResponse.addHeader(
                HttpHeaders.SET_COOKIE,
                this.cookieService.createCookie("__NotSecure-Fgp", "", "/", Duration.ZERO)
                        .toString());
        httpResponse.addHeader(
                HttpHeaders.SET_COOKIE,
                this.cookieService.createCookie("refresh_token", "", "/api/auth/refresh", Duration.ZERO)
                        .toString());
    }

    private String createUserFingerprint() {
        byte[] randomFgp = new byte[50];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(randomFgp);
        return HexFormat.of().formatHex(randomFgp);
    }
}