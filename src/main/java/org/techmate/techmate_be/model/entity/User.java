package org.techmate.techmate_be.model.entity;

import lombok.Getter;
import lombok.Setter;
//import org.techmate.techmate_be.model.entity.JobRole;
//import org.techmate.techmate_be.model.entity.project.Project;
//import org.techmate.techmate_be.model.entity.project.ProjectJoinRequest;
import org.techmate.techmate_be.model.enumeration.OAuthProvider;
import org.techmate.techmate_be.model.enumeration.OnboardingStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class User {
    private UUID userId;

    private String username;

    private String email;

    private String avatarUrl;

    private OnboardingStatus onboardingStatus = OnboardingStatus.PENDING;

    private OAuthProvider lastProvider;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}