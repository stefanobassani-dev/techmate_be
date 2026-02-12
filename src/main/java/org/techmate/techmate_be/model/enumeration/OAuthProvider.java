package org.techmate.techmate_be.model.enumeration;

import lombok.Getter;

@Getter
public enum OAuthProvider {
    GOOGLE("google"),
    GITHUB("github");

    final String value;
    OAuthProvider(String value) {
        this.value = value;
    }
}
