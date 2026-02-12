package org.techmate.techmate_be.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.techmate.techmate_be.mapper.UserMapper;
import org.techmate.techmate_be.model.entity.User;
import org.techmate.techmate_be.model.enumeration.sql.UserQuery;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final JdbcClient jdbcClient;

    public Optional<User> findByEmail(String email) {
        String query = UserQuery.FIND_BY_EMAIL.getValue();

        return this.jdbcClient
                .sql(query)
                .param("email", email)
                .query((rs, rowNum) -> new UserMapper().mapEntity(rs, rowNum))
                .optional();
    }

    public User save(User user) {
        String query = UserQuery.INSERT.getValue();
        UUID uuid = UUID.randomUUID();

        return this.jdbcClient
                .sql(query)
                .param("userId", uuid)
                .param("email", user.getEmail())
                .param("username", user.getUsername())
                .param("avatarUrl", user.getAvatarUrl())
                .param("lastProvider", user.getLastProvider().name())
                .param("onboardingStatus", user.getOnboardingStatus().name())
                .param("createdAt", user.getCreatedAt())
                .param("updatedAt", user.getUpdatedAt())
                .query((rs, rowNum) -> new UserMapper().mapEntity(rs, rowNum))
                .single();
    }
}
