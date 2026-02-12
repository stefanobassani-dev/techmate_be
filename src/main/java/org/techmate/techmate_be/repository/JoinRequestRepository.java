package org.techmate.techmate_be.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.techmate.techmate_be.model.dto.request.JoinRequest;
import org.techmate.techmate_be.model.dto.response.project.ProjectAndOwnerBasicInfoResponse;
import org.techmate.techmate_be.model.dto.response.project.ProjectFullResponse;
import org.techmate.techmate_be.model.entity.User;
import org.techmate.techmate_be.model.enumeration.RequestStatus;
import org.techmate.techmate_be.model.enumeration.sql.JoinRequestQuery;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class JoinRequestRepository {
    private final JdbcClient jdbcClient;

    public int save(ProjectAndOwnerBasicInfoResponse project, User user) {
        String query = JoinRequestQuery.SAVE_JOIN_REQUEST.getValue();

        return this.jdbcClient
                .sql(query)
                .param("joinRequestId", UUID.randomUUID().toString())
                .param("requesterId", user.getUserId())
                .param("projectId",  project.getProjectId())
                .param("requestStatus", RequestStatus.PENDING.getValue())
                .update();
    }
}
