package org.techmate.techmate_be.model.dto.response.user;

import lombok.Getter;
import lombok.Setter;
import org.techmate.techmate_be.model.entity.User;

import java.util.UUID;

@Getter
@Setter
public class UserResponse {
    private UUID userId;
    private String username;
    private String email;
    private String avatarUrl;

    public UserResponse() {}

    public UserResponse(UUID id, String username, String email, String avatarUrl) {
        this.userId = id;
        this.username = username;
        this.email = email;
        this.avatarUrl = avatarUrl;
    }

    public static UserResponse toUserResponse(User user) {
        return new UserResponse(user.getUserId(), user.getUsername(),
                user.getEmail(), user.getAvatarUrl());
    }
}