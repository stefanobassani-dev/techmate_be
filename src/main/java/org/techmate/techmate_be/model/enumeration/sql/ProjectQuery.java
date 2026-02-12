package org.techmate.techmate_be.model.enumeration.sql;

public enum ProjectQuery {
    FIND_RECOMMENDED_PROJECTS("""
            WITH user_roles AS (SELECT job_role_id
                                FROM user_job_roles
                                WHERE user_id = :userId)
            select count(*) over() as total_count, p.project_id,
            p.title, p.overview, p.created_at, u.user_id, u.username, u.email, u.avatar_url
            from projects p
                     left join project_join_requests pjr
                               on pjr.project_id = p.project_id
                                   and pjr.requester_id = :userId
                     left join users u
                               on u.user_id = p.owner_id
            where (pjr.join_request_id is null
               or pjr.request_status not in ('PENDING', 'ACCEPTED'))
                and p.owner_id <> :userId
                and EXISTS (select 1
                            from project_job_roles pjr_role
                                     join user_roles ur on ur.job_role_id = pjr_role.job_role_id
                            where pjr_role.project_id = p.project_id)
                and (:searchTerm is null or (
                    LOWER(u.username) LIKE LOWER(CONCAT('%', :searchTerm, '%'))
                        OR LOWER(p.overview) LIKE LOWER(CONCAT('%', :searchTerm, '%'))
                        OR LOWER(p.title) LIKE LOWER(CONCAT('%', :searchTerm, '%'))
                    ))
            order by p.created_at DESC
            limit :size offset :offset;
            """),
    FIND_PROJECT_BY_ID("""
            select
                p.project_id,
                p.title,
                p.overview,
                p.created_at,
                pjr.request_status,
                rej.rejected_at,
                u.user_id,
                u.username,
                u.email,
                u.avatar_url,
                (select count(*) from project_users pu where pu.project_id = p.project_id) as active_participants_count,
                (select count(*) from project_job_roles pjr_roles where pjr_roles.project_id = p.project_id) as open_roles_count,
                coalesce(
                                json_agg(
                                json_build_object(
                                        'jobId', jr.job_role_id,
                                        'jobName', jr.name,
                                        'jobMacroArea', jma.name
                                )
                                        ) filter (where jr.job_role_id is not null),
                                '[]'::json
                ) as job_roles
            from projects p
                     left join project_join_requests pjr
                               on pjr.project_id = p.project_id
                                   and pjr.requester_id = :userId
                     left join project_join_request_rejections rej
                               on rej.join_request_id = pjr.join_request_id
                     left join users u on u.user_id = p.owner_id
                     left join project_job_roles pjr_role on pjr_role.project_id = p.project_id
                     left join job_roles jr on pjr_role.job_role_id = jr.job_role_id
                     left join job_macro_areas jma on jma.job_macro_area_id = jr.job_macro_area_id
            where p.project_id = :projectId
            group by
                p.project_id,
                p.title,
                p.overview,
                p.created_at,
                pjr.request_status,
                rej.rejected_at,
                u.user_id,
                u.username,
                u.email,
                u.avatar_url;
            """),
    FIND_PROJECT_AND_OWNER_BASIC_INFO("""
            select p.project_id, p.title, u.user_id, u.email, u.username
            from projects p
            join users u on u.user_id = p.owner_id
            where p.project_id = :projectId
            """),
    FIND_OWNED_PROJECTS("""
            select count(*) over() as total_count, p.project_id,
            p.title, p.overview, p.created_at, u.user_id, u.username, u.email, u.avatar_url
            from projects p
            left join users u
                               on u.user_id = p.owner_id
            where p.owner_id = :userId                   
            """),
    FIND_JOINED_PROJECTS("""
            select count(*) over() as total_count, p.project_id,
                   p.title, p.overview, p.created_at, u.user_id, u.username, u.email, u.avatar_url, pu.user_id
            from projects p
            left join users u on u.user_id = p.owner_id
            left join project_users pu on pu.project_id = p.project_id
            where pu.user_id = :userId
            """),
    FIND_PENDING_PROJECTS("""
            select count(*) over() as total_count, p.project_id,
                   p.title, p.overview, p.created_at, u.user_id, u.username, u.email, u.avatar_url
            from projects p
            left join users u on u.user_id = p.owner_id
            left join project_join_requests pjr on pjr.project_id = p.project_id
            where pjr.requester_id = :userId
            and pjr.request_status = 'PENDING'
            """);

    final String value;

    ProjectQuery(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
