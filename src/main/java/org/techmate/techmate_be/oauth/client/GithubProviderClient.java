package org.techmate.techmate_be.oauth.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.techmate.techmate_be.oauth.OAuthUser;

import org.techmate.techmate_be.oauth.dto.request.GithubAccessTokenRequest;
import org.techmate.techmate_be.oauth.dto.response.github.GithubUser;
import org.techmate.techmate_be.oauth.dto.response.github.GithubEmail;
import org.techmate.techmate_be.util.ApiCallUtils;

import java.util.List;

@Component
public class GithubProviderClient implements IOAuthProviderClient {
    private static final String GITHUB_ACCESS_TOKEN_URL = "https://github.com/login/oauth/access_token";
    private static final String GITHUB_USER_INFO_URL = "https://api.github.com/user";
    private static final String GITHUB_USER_EMAILS_URL = "https://api.github.com/user/emails";

    @Value("${oauth.github.client_id}")
    private String clientId;

    @Value("${oauth.github.client_secret}")
    private String clientSecret;

    @Override
    public OAuthUser getUser(String code) {
        String accessToken = this.getAccessTokenFromCode(code);

        HttpEntity<String> entity = new HttpEntity<>(ApiCallUtils.getCommonHeaders(accessToken));

        GithubUser gitHubUser = ApiCallUtils
                .call(GITHUB_USER_INFO_URL, HttpMethod.GET,
                        entity, new ParameterizedTypeReference<>() {
                        });

        OAuthUser user = new OAuthUser();
        user.setUsername(gitHubUser.getLogin());
        user.setAvatarUrl(gitHubUser.getAvatar_url());

        List<GithubEmail> emailsResponse = ApiCallUtils
                .call(GITHUB_USER_EMAILS_URL, HttpMethod.GET,
                        entity, new ParameterizedTypeReference<>() {
                        });

        String userEmail = emailsResponse
                .stream()
                .filter(GithubEmail::isPrimary)
                .findFirst()
                .map(GithubEmail::getEmail)
                .get();

        user.setEmail(userEmail);

        return user;
    }

    @Override
    public String getAccessTokenFromCode(String code) {
        GithubAccessTokenRequest request = new GithubAccessTokenRequest();
        request.setCode(code);
        request.setClientId(clientId);
        request.setClientSecret(clientSecret);
        request.setAccept("json");
        HttpEntity<GithubAccessTokenRequest> accessTokenEntity = new HttpEntity<>(request);

        String response = ApiCallUtils.call(GITHUB_ACCESS_TOKEN_URL,
                HttpMethod.POST,
                accessTokenEntity,
                new ParameterizedTypeReference<>() {
                }
        );

        return response.split("&")[0].split("=")[1];
    }
}