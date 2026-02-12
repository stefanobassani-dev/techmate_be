package org.techmate.techmate_be.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.techmate.techmate_be.model.dto.response.GenericResponse;
import org.techmate.techmate_be.security.authentication.JwtAuthentication;

@RestController
@RequestMapping("/api/healthcheck")
public class HealthcheckController {
    @GetMapping
    public ResponseEntity<String> healthcheck() {
        return ResponseEntity.ok("OK");
    }

    @GetMapping("/auth")
    public ResponseEntity<GenericResponse<Object>> authenticatedHealthcheck() {
        JwtAuthentication auth = (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
        return GenericResponse.withData("User", HttpStatus.OK.value(), auth.getPrincipal());
    }
}