package org.techmate.techmate_be.oauth.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GoogleAccessTokenRequest {
    private String clientId;
    private String clientSecret;
    private String code;
    private String redirectUri;
    private String grantType;
}