package org.techmate.techmate_be.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.techmate.techmate_be.model.dto.response.JobRoleResponse;
import org.techmate.techmate_be.model.dto.response.project.ProjectAndOwnerBasicInfoResponse;
import org.techmate.techmate_be.model.dto.response.project.ProjectFullResponse;
import org.techmate.techmate_be.model.dto.response.project.ProjectResponse;
import org.techmate.techmate_be.model.dto.response.user.UserResponse;
import org.techmate.techmate_be.model.enumeration.RequestStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class ProjectMapper {

    public ProjectResponse map(ResultSet rs, int rowNum) throws SQLException {
        ProjectResponse project = new ProjectResponse();
        project.setProjectId(UUID.fromString(rs.getString("project_id")));
        project.setTitle(rs.getString("title"));
        project.setOverview(rs.getString("overview"));
        project.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        UserResponse owner = new UserMapper().map(rs, rowNum);
        project.setOwner(owner);
        return project;
    }

    public ProjectAndOwnerBasicInfoResponse mapBasicWithOwner(ResultSet rs, int rowNum) throws SQLException {
        ProjectAndOwnerBasicInfoResponse project = new ProjectAndOwnerBasicInfoResponse();
        project.setProjectId(UUID.fromString(rs.getString("project_id")));
        project.setTitle(rs.getString("title"));
        project.setOwnerEmail("email");
        project.setOwnerUsername("username");
        project.setOwnerId(UUID.fromString("user_id"));
        return project;
    }

    public ProjectFullResponse mapFull(ResultSet rs, int rowNum) throws SQLException {
        ProjectResponse projectResponse = this.map(rs, rowNum);

        String requestStatus = rs.getString("request_status");
        Timestamp ts = rs.getTimestamp("rejected_at");

        String json = rs.getString("job_roles");

        List<JobRoleResponse> roles;
        try {
            roles = json != null
                    ? new ObjectMapper().readValue(json, new TypeReference<List<JobRoleResponse>>() {})
                    : Collections.emptyList();
        } catch (JsonProcessingException e) {
            roles = Collections.emptyList();
        }

        return ProjectFullResponse
                .buildFromProjectResponse(
                        projectResponse,
                        requestStatus != null ? RequestStatus.valueOf(rs.getString("request_status")) : null,
                        ts != null ? ts.toLocalDateTime() : null,
                        rs.getInt("active_participants_count"),
                        rs.getInt("open_roles_count"),
                        roles
                        );
    }


}
