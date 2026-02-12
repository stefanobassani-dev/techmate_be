package org.techmate.techmate_be.oauth.dto.response.google;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GoogleAccessToken {
    private String accessToken;
    private String tokenType;
    private String idToken;
    private String scope;
    private String expiresIn;
}