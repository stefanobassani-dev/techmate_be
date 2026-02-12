package org.techmate.techmate_be.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.techmate.techmate_be.model.dto.response.GenericResponse;
import org.techmate.techmate_be.model.dto.request.LoginRequest;
import org.techmate.techmate_be.model.dto.response.AuthResponse;
import org.techmate.techmate_be.model.dto.response.user.UserResponse;
import org.techmate.techmate_be.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<GenericResponse<AuthResponse>> login(@Valid @RequestBody LoginRequest loginRequest,
                                                               HttpServletResponse httpResponse) {
        AuthResponse response = this.authService.login(loginRequest, httpResponse);

        return GenericResponse
                .withData("User logged successfully in", HttpStatus.OK.value(), response);
    }

    @GetMapping("/me")
    public ResponseEntity<GenericResponse<UserResponse>> getUser() {
        return GenericResponse.withData("User retrieved successfully",
                HttpStatus.OK.value(),
                this.authService.getAuthenticatedUserDto());
    }

    @GetMapping("/refresh")
    public ResponseEntity<GenericResponse<AuthResponse>> refresh(
            @CookieValue(name = "refresh_token", required = false) String refreshToken,
            HttpServletResponse httpResponse) {
        AuthResponse response = this.authService.refresh(refreshToken, httpResponse);
        return GenericResponse.withData("Tokens refreshed", HttpStatus.OK.value(), response);
    }

    @GetMapping("/logout")
    public ResponseEntity<GenericResponse<Void>> refresh(HttpServletResponse httpResponse) {
        this.authService.logout(httpResponse);
        return GenericResponse.withMessage("User logged out", HttpStatus.OK.value());
    }
}