package org.techmate.techmate_be.util;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import org.techmate.techmate_be.exception.OAuthApiCallException;

public class ApiCallUtils {
    public static <T> T call(String url, HttpMethod httpMethod,
                             HttpEntity<?> httpEntity, ParameterizedTypeReference<T> responseType) {
        RestTemplate restTemplate = new RestTemplate();

        try {
            return restTemplate.exchange(
                    url,
                    httpMethod,
                    httpEntity,
                    responseType
            ).getBody();
        } catch (Exception e) {
            throw new OAuthApiCallException(e.getMessage());
        }
    }

    public static HttpHeaders getCommonHeaders(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        headers.add("Accept", "application/json");

        return headers;
    }
}