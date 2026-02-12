package org.techmate.techmate_be.oauth.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.techmate.techmate_be.oauth.OAuthUser;
import org.techmate.techmate_be.oauth.dto.request.GoogleAccessTokenRequest;
import org.techmate.techmate_be.oauth.dto.response.google.GoogleAccessToken;
import org.techmate.techmate_be.oauth.dto.response.google.GoogleUser;
import org.techmate.techmate_be.util.ApiCallUtils;

@Component
public class GoogleProviderClient implements IOAuthProviderClient {
    private static final String GOOGLE_USER_INFO_URL = "https://www.googleapis.com/oauth2/v2/userinfo";
    private static final String GOOGLE_ACCESS_TOKEN_URL = "https://oauth2.googleapis.com/token";

    @Value("${oauth.google.client_id}")
    private String clientId;

    @Value("${oauth.google.client_secret}")
    private String clientSecret;

    @Value("${oauth.redirect_uri}")
    private String redirectUri;

    @Override
    public OAuthUser getUser(String code) {
        String accessToken = this.getAccessTokenFromCode(code);
        HttpEntity<String> entity = new HttpEntity<>(ApiCallUtils.getCommonHeaders(accessToken));

        GoogleUser user = ApiCallUtils.call(GOOGLE_USER_INFO_URL,
                HttpMethod.GET, entity, new ParameterizedTypeReference<>() {});

        OAuthUser oAuthUser = new OAuthUser();
        oAuthUser.setUsername(user.getGiven_name());
        oAuthUser.setEmail(user.getEmail());
        oAuthUser.setAvatarUrl(user.getPicture());

        return oAuthUser;
    }

    @Override
    public String getAccessTokenFromCode(String code) {
        GoogleAccessTokenRequest request = new GoogleAccessTokenRequest();
        request.setCode(code);
        request.setClientId(clientId);
        request.setClientSecret(clientSecret);
        request.setGrantType("authorization_code");
        request.setRedirectUri(redirectUri);

        HttpEntity<GoogleAccessTokenRequest> entity = new HttpEntity<>(request);
        GoogleAccessToken response = ApiCallUtils
                .call(GOOGLE_ACCESS_TOKEN_URL, HttpMethod.POST, entity, new ParameterizedTypeReference<>() {});
        return response.getAccessToken();
    }
}