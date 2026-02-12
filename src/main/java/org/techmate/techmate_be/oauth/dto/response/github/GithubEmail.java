package org.techmate.techmate_be.oauth.dto.response.github;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GithubEmail {
    private String email;
    private boolean primary;
    private boolean verified;
    private String visibility;
}