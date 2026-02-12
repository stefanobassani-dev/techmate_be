package org.techmate.techmate_be.oauth.client;

import org.techmate.techmate_be.oauth.OAuthUser;

public interface IOAuthProviderClient {
    OAuthUser getUser(String accessToken);
    String getAccessTokenFromCode(String code);
}