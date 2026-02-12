package org.techmate.techmate_be.model.enumeration.sql;

public enum UserQuery {
    FIND_BY_EMAIL("""
            select *
            from users u
            where u.email = :email
            """),
    INSERT("""
        INSERT INTO users (user_id, email, username, avatar_url, 
        last_provider, onboarding_status, created_at, updated_at)
        VALUES (:userId, :email, :username, :avatarUrl, :lastProvider, :onboardingStatus, :createdAt, :updatedAt)
        RETURNING *
        """);

    final String value;

    UserQuery(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
