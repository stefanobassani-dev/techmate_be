package org.techmate.techmate_be.mapper;

import org.techmate.techmate_be.model.dto.response.user.UserResponse;
import org.techmate.techmate_be.model.entity.User;
import org.techmate.techmate_be.model.enumeration.OAuthProvider;
import org.techmate.techmate_be.model.enumeration.OnboardingStatus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UserMapper {

    public User mapEntity(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setUserId(UUID.fromString(rs.getString("user_id")));
        user.setEmail(rs.getString("email"));
        user.setUsername(rs.getString("username"));
        user.setAvatarUrl(rs.getString("avatar_url"));
        user.setLastProvider(OAuthProvider.valueOf(rs.getString("last_provider").toUpperCase()));
        user.setOnboardingStatus(OnboardingStatus.valueOf(rs.getString("onboarding_status").toUpperCase()));
        user.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        user.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
        return user;
    }

    public UserResponse map(ResultSet rs, int rowNum) throws SQLException {
        UserResponse userResponse = new UserResponse();
        userResponse.setUserId(UUID.fromString(rs.getString("user_id")));
        userResponse.setEmail(rs.getString("email"));
        userResponse.setUsername(rs.getString("username"));
        userResponse.setAvatarUrl(rs.getString("avatar_url"));
        return userResponse;
    }

//    public UserFullResponse mapFull(ResultSet rs, int rowNum) throws SQLException {
//        UserFullResponse  userResponse = new UserFullResponse();
//        userResponse.setUserId(UUID.fromString(rs.getString("user_id")));
//        userResponse.setEmail(rs.getString("email"));
//        userResponse.setUsername(rs.getString("username"));
//        userResponse.setAvatarUrl(rs.getString("avatar_url"));
//        userResponse.setLastProvider(OAuthProvider.valueOf(rs.getString("last_provider").toUpperCase()));
//        userResponse.setOnboardingStatus(OnboardingStatus.valueOf(rs.getString("onboarding_status").toUpperCase()));
//        userResponse.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
//        userResponse.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
//        return userResponse;
//    }
}
