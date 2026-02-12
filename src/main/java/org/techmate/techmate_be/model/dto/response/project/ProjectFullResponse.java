package org.techmate.techmate_be.model.dto.response.project;

import lombok.Getter;
import lombok.Setter;
import org.techmate.techmate_be.model.dto.response.JobRoleResponse;
import org.techmate.techmate_be.model.enumeration.RequestStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ProjectFullResponse extends ProjectResponse {
    RequestStatus joinRequestStatus;
    LocalDateTime rejectedAt;
    int activeParticipantsCount;
    int openRolesCount;
    List<JobRoleResponse> requiredJobRoles;

    public ProjectFullResponse() {

    }

    public static ProjectFullResponse buildFromProjectResponse(ProjectResponse project,
                                                               RequestStatus joinRequestStatus,
                                                               LocalDateTime rejectedAt, int activeParticipants,
                                                               int openRoles, List<JobRoleResponse> jobRoles) {
        ProjectFullResponse projectFullResponse = new ProjectFullResponse();
        projectFullResponse.setProjectId(project.getProjectId());
        projectFullResponse.setTitle(project.getTitle());
        projectFullResponse.setOverview(project.getOverview());
        projectFullResponse.setRejectedAt(rejectedAt);
        projectFullResponse.setCreatedAt(project.getCreatedAt());
        projectFullResponse.setJoinRequestStatus(joinRequestStatus);
        projectFullResponse.setOwner(project.getOwner());
        projectFullResponse.setOpenRolesCount(openRoles);
        projectFullResponse.setActiveParticipantsCount(activeParticipants + 1);
        projectFullResponse.setRequiredJobRoles(jobRoles);
        return projectFullResponse;
    }
}
