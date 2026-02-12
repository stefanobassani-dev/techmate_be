package org.techmate.techmate_be.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class Project {
    private UUID projectId;
    private String title;
    private String overview;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UUID ownerId;
}
