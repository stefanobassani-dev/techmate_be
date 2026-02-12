package org.techmate.techmate_be.model.dto.response.project;

import lombok.Getter;
import lombok.Setter;
import org.techmate.techmate_be.model.dto.response.user.UserResponse;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class ProjectResponse {
    private UUID projectId;
    private String title;
    private String overview;
    private LocalDateTime createdAt;
    private UserResponse owner;

    public ProjectResponse() {

    }
}
