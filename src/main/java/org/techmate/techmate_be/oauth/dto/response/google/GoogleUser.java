package org.techmate.techmate_be.oauth.dto.response.google;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoogleUser {
    private String id;
    private String email;
    private String verified_email;
    private String name;
    private String given_name;
    private String family_name;
    private String picture;
}