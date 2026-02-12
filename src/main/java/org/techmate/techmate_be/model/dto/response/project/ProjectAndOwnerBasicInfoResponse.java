package org.techmate.techmate_be.model.dto.response.project;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ProjectAndOwnerBasicInfoResponse {
    private UUID projectId;
    private String title;
    private UUID ownerId;
    private String ownerUsername;
    private String ownerEmail;
}
