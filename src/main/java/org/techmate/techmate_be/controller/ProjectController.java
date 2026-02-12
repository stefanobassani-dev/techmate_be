package org.techmate.techmate_be.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.techmate.techmate_be.model.dto.request.JoinRequest;
import org.techmate.techmate_be.model.dto.response.GenericResponse;
import org.techmate.techmate_be.model.dto.response.Page;
import org.techmate.techmate_be.model.dto.response.project.ProjectResponse;
import org.techmate.techmate_be.service.ProjectService;

import java.util.UUID;


@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse<ProjectResponse>> getProjectById(@PathVariable UUID id) {
        return GenericResponse.withData("", HttpStatus.OK.value(), this.projectService.getProjectById(id));
    }

    @GetMapping("/recommended")
    public ResponseEntity<GenericResponse<Page<ProjectResponse>>> getUserRecommendedProjects(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size,
            @RequestParam(defaultValue = "") String searchTerm) {
        return GenericResponse
                .withData("", HttpStatus.OK.value(), this.projectService.getRecommendedProject(page, size, searchTerm));
    }

    @GetMapping("/owned")
    public ResponseEntity<GenericResponse<Page<ProjectResponse>>> getUserOwnedProjects(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size,
            @RequestParam(defaultValue = "") String searchTerm) {
        return GenericResponse
                .withData("", HttpStatus.OK.value(), this.projectService.getOwnedProject(page, size, searchTerm));
    }

    @GetMapping("/joined")
    public ResponseEntity<GenericResponse<Page<ProjectResponse>>> getUserJoinedProjects(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size,
            @RequestParam(defaultValue = "") String searchTerm) {
        return GenericResponse
                .withData("", HttpStatus.OK.value(), this.projectService.getJoinedProject(page, size, searchTerm));
    }

    @GetMapping("/pending")
    public ResponseEntity<GenericResponse<Page<ProjectResponse>>> getUserPendingProjects(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size,
            @RequestParam(defaultValue = "") String searchTerm) {
        return GenericResponse
                .withData("", HttpStatus.OK.value(), this.projectService.getPendingProject(page, size, searchTerm));
    }

    @PostMapping("/{id}/join-request")
    public ResponseEntity<GenericResponse<Void>> projectJoinRequest(@PathVariable UUID id,
                                                                    @Valid @RequestBody JoinRequest request) {
        HttpStatus status = this.projectService.joinRequest(id, request) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return GenericResponse.withMessage("", status.value());
    }

}