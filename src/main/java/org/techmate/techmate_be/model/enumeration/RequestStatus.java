package org.techmate.techmate_be.model.enumeration;

public enum RequestStatus {
    PENDING("pending"),
    ACCEPTED("accepted"),
    REJECTED("rejected"),;

    final String value;

    RequestStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}


