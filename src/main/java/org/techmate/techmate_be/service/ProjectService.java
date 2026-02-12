package org.techmate.techmate_be.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.techmate.techmate_be.exception.ProjectNotFoundException;
import org.techmate.techmate_be.model.dto.request.JoinRequest;
import org.techmate.techmate_be.model.dto.response.Page;
import org.techmate.techmate_be.model.dto.response.project.ProjectAndOwnerBasicInfoResponse;
import org.techmate.techmate_be.model.dto.response.project.ProjectFullResponse;
import org.techmate.techmate_be.model.dto.response.project.ProjectResponse;
import org.techmate.techmate_be.model.entity.User;
import org.techmate.techmate_be.repository.JoinRequestRepository;
import org.techmate.techmate_be.repository.ProjectRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final JoinRequestRepository joinRequestRepository;
    private final AuthService authService;
    private final EmailService emailService;

    public ProjectResponse getProjectById(UUID id) {
        User user = this.authService.getAuthenticatedUser();

        return this.projectRepository.findProjectById(user, id).orElseThrow(() -> new ProjectNotFoundException(id));
    }

    public Page<ProjectResponse> getRecommendedProject(int page, int size, String searchTerm) {
        User user = this.authService.getAuthenticatedUser();

        return this.projectRepository.findRecommendedProjects(user, searchTerm, page, size);
    }

    public Page<ProjectResponse> getOwnedProject(int page, int size, String searchTerm) {
        User user = this.authService.getAuthenticatedUser();

        return this.projectRepository.findOwnedProjects(user, searchTerm, page, size);
    }

    public Page<ProjectResponse> getJoinedProject(int page, int size, String searchTerm) {
        User user = this.authService.getAuthenticatedUser();

        return this.projectRepository.findJoinedProjects(user, searchTerm, page, size);
    }

    public Page<ProjectResponse> getPendingProject(int page, int size, String searchTerm) {
        User user = this.authService.getAuthenticatedUser();

        return this.projectRepository.findPendingProjects(user, searchTerm, page, size);
    }

    public boolean joinRequest(UUID id, JoinRequest request) {
        User user = this.authService.getAuthenticatedUser();
        ProjectAndOwnerBasicInfoResponse project = projectRepository
                .findProjectAndOwnerById(user, id)
                .orElseThrow(() -> new ProjectNotFoundException(id));

        if (this.joinRequestRepository.save(project, user) < 1) return false;

        this.emailService.sendJoinRequestEmail(project.getOwnerEmail(), project, user, request.getCommitmentLevel());

        return true;
    }

//
//    @Transactional
//    public boolean cancelJoinRequest(UUID id) {
//        User user = this.authService.getAuthenticatedUser();
//        Project project = this.projectRepository
//                .findById(id).orElseThrow(() -> new ProjectNotFoundException(id));
//
//        this.projectJoinRequestRepository.deleteByProjectAndRequester(project, user);
//
//        return true;
//    }
//
//    public PagedModel<ProjectProjection> getJoinedProject(int page, int size) {
//        User user = this.authService.getAuthenticatedUser();
//
//        Pageable pageable = PageRequest.of(page, size);
//        return new PagedModel<>(this.projectRepository.findJoinedProjectsByUser(user, pageable));
//    }
//
//    public PagedModel<ProjectProjection> getPendingProjects(int page, int size) {
//        User user = this.authService.getAuthenticatedUser();
//
//        Pageable pageable = PageRequest.of(page, size);
//        return new PagedModel<>(this.projectRepository.findPendingRequestedProjects(user, pageable));
//    }
}