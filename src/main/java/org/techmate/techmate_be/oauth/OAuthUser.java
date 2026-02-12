package org.techmate.techmate_be.oauth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OAuthUser {
    private String username;
    private String email;
    private String avatarUrl;
}