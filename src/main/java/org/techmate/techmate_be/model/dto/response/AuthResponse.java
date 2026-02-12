package org.techmate.techmate_be.model.dto.response;

import lombok.Getter;
import lombok.Setter;
import org.techmate.techmate_be.model.dto.response.user.UserResponse;
import org.techmate.techmate_be.model.enumeration.OAuthProvider;

@Getter
@Setter
public class AuthResponse {
    private String token;
    private UserResponse user;
    private OAuthProvider lastProvider;

    public AuthResponse() {}
}