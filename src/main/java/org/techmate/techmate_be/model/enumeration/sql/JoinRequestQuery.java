package org.techmate.techmate_be.model.enumeration.sql;

public enum JoinRequestQuery {
    SAVE_JOIN_REQUEST("""
            insert into project_join_requests (join_request_id, requester_id, project_id, request_status)
            values (:joinRequestId, :requesterId, :projectId, :requestStatus)
            on conflict (requester_id, project_id) do nothing;
            """);

    final String value;

    JoinRequestQuery(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
