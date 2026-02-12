package org.techmate.techmate_be.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.techmate.techmate_be.mapper.ProjectMapper;
import org.techmate.techmate_be.model.dto.response.Page;
import org.techmate.techmate_be.model.dto.response.PageInfo;
import org.techmate.techmate_be.model.dto.response.project.ProjectAndOwnerBasicInfoResponse;
import org.techmate.techmate_be.model.dto.response.project.ProjectFullResponse;
import org.techmate.techmate_be.model.dto.response.project.ProjectResponse;
import org.techmate.techmate_be.model.entity.User;
import org.techmate.techmate_be.model.enumeration.sql.ProjectQuery;
import org.techmate.techmate_be.util.PaginationUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ProjectRepository {
    private final JdbcClient jdbcClient;

    public Optional<ProjectFullResponse> findProjectById(User user, UUID projectId) {
        String query = ProjectQuery.FIND_PROJECT_BY_ID.getValue();

        return this.jdbcClient
                .sql(query)
                .param("projectId", projectId)
                .param("userId", user.getUserId())
                .query((rs, rowNum) -> new ProjectMapper().mapFull(rs, rowNum))
                .optional();
    }

    public Optional<ProjectAndOwnerBasicInfoResponse> findProjectAndOwnerById(User user, UUID projectId) {
        String query = ProjectQuery.FIND_PROJECT_AND_OWNER_BASIC_INFO.getValue();

        return this.jdbcClient
                .sql(query)
                .param("projectId", projectId)
                .param("userId", user.getUserId())
                .query((rs, rowNum) -> new ProjectMapper().mapBasicWithOwner(rs, rowNum))
                .optional();
    }

    public Page<ProjectResponse> findRecommendedProjects(User user, String searchTerm, int page, int size) {
        String query = ProjectQuery.FIND_RECOMMENDED_PROJECTS.getValue();
        return this.getPagedProjects(query, user, searchTerm, page, size);
    }

    public Page<ProjectResponse> findOwnedProjects(User user, String searchTerm, int page, int size) {
        String query = ProjectQuery.FIND_OWNED_PROJECTS.getValue();
        return this.getPagedProjects(query, user, searchTerm, page, size);
    }

    public Page<ProjectResponse> findJoinedProjects(User user, String searchTerm, int page, int size) {
        String query = ProjectQuery.FIND_JOINED_PROJECTS.getValue();
        return this.getPagedProjects(query, user, searchTerm, page, size);
    }

    public Page<ProjectResponse> findPendingProjects(User user, String searchTerm, int page, int size) {
        String query = ProjectQuery.FIND_PENDING_PROJECTS.getValue();
        return this.getPagedProjects(query, user, searchTerm, page, size);
    }

    private Page<ProjectResponse> getPagedProjects(String query, User user, String searchTerm, int page, int size) {
        final int[] totalCount = {-1};

        List<ProjectResponse> projects = this.jdbcClient
                .sql(query)
                .param("userId", user.getUserId())
                .param("size", size)
                .param("offset", PaginationUtils.getOffset(page, size))
                .param("searchTerm", searchTerm)
                .query((rs, rowNum) -> {
                    ProjectResponse project = new ProjectMapper().map(rs,  rowNum);
                    if (totalCount[0] == -1) totalCount[0] = rs.getInt("total_count");
                    return project;
                })
                .list();

        return new Page<>(projects, new PageInfo(size, page, totalCount[0]));
    }
}
