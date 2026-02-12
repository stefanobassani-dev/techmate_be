package org.techmate.techmate_be.model.enumeration;

import lombok.Getter;

@Getter
public enum OnboardingStatus {
//    NOT_STARTED("not_started"),
    PENDING("pending"),
    COMPLETED("completed");

    final String value;

    OnboardingStatus(String value) {
        this.value = value;
    }

}
