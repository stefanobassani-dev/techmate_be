-- =========================
-- DROP TYPES (per reset)
-- =========================
DROP TYPE IF EXISTS "providers" CASCADE;
DROP TYPE IF EXISTS "onboarding_status" CASCADE;
DROP TYPE IF EXISTS "join_request_statuses" CASCADE;

-- =========================
-- DROP TABLES (per reset)
-- ordine inverso delle dipendenze
-- =========================
DROP TABLE IF EXISTS "project_join_request_rejections" CASCADE;
DROP TABLE IF EXISTS "project_join_requests" CASCADE;
DROP TABLE IF EXISTS "project_users" CASCADE;
DROP TABLE IF EXISTS "project_job_roles" CASCADE;
DROP TABLE IF EXISTS "user_job_roles" CASCADE;
DROP TABLE IF EXISTS "job_roles" CASCADE;
DROP TABLE IF EXISTS "job_macro_areas" CASCADE;
DROP TABLE IF EXISTS "projects" CASCADE;
DROP TABLE IF EXISTS "users" CASCADE;

-- =========================
-- ENUM TYPES
-- =========================
CREATE TYPE "providers" AS ENUM (
    'GOOGLE',
    'GITHUB'
    );

CREATE TYPE "onboarding_status" AS ENUM (
    'PENDING',
    'COMPLETED'
    );

CREATE TYPE "join_request_statuses" AS ENUM (
    'REJECTED',
    'PENDING',
    'ACCEPTED'
    );

-- =========================
-- TABLES
-- =========================
CREATE TABLE "users" (
                         "user_id" uuid PRIMARY KEY,
                         "email" varchar UNIQUE NOT NULL,
                         "username" varchar,
                         "avatar_url" varchar,
                         "last_provider" providers NOT NULL,
                         "onboarding_status" onboarding_status NOT NULL,
                         "created_at" timestamp NOT NULL DEFAULT (now()),
                         "updated_at" timestamp NOT NULL DEFAULT (now())
);

CREATE TABLE "projects" (
                            "project_id" uuid PRIMARY KEY,
                            "title" varchar NOT NULL,
                            "overview" text NOT NULL,
                            "created_at" timestamp NOT NULL DEFAULT (now()),
                            "updated_at" timestamp NOT NULL DEFAULT (now()),
                            "owner_id" uuid NOT NULL
);

--TODO cambiare tipo id e mettere intero
CREATE TABLE "job_macro_areas" (
                                   "job_macro_area_id" uuid PRIMARY KEY,
                                   "name" varchar UNIQUE NOT NULL
);

CREATE TABLE "job_roles" (
                             "job_role_id" serial PRIMARY KEY,
                             "name" varchar,
                             "job_macro_area_id" uuid NOT NULL
);

CREATE TABLE "user_job_roles" (
                                  "user_id" uuid,
                                  "job_role_id" integer,
                                  PRIMARY KEY ("user_id", "job_role_id")
);

CREATE TABLE "project_job_roles" (
                                     "job_role_id" integer,
                                     "project_id" uuid,
                                     PRIMARY KEY ("job_role_id", "project_id")
);

CREATE TABLE "project_users" (
                                 "user_id" uuid,
                                 "project_id" uuid,
                                 PRIMARY KEY ("user_id", "project_id")
);

CREATE TABLE "project_join_requests" (
                                         "join_request_id" uuid PRIMARY KEY,
                                         "requester_id" uuid NOT NULL,
                                         "project_id" uuid NOT NULL,
                                         "request_status" join_request_statuses NOT NULL,
                                         "created_at" timestamp NOT NULL DEFAULT (now()),
                                         "updated_at" timestamp NOT NULL DEFAULT (now())
);

CREATE TABLE "project_join_request_rejections" (
                                         "join_request_id" uuid PRIMARY KEY,
                                         "rejected_at" timestamp NOT NULL DEFAULT (now())
);

-- =========================
-- FOREIGN KEYS
-- =========================
ALTER TABLE "projects" ADD FOREIGN KEY ("owner_id") REFERENCES "users" ("user_id");

ALTER TABLE "job_roles" ADD FOREIGN KEY ("job_macro_area_id") REFERENCES "job_macro_areas" ("job_macro_area_id");

ALTER TABLE "user_job_roles" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("user_id");
ALTER TABLE "user_job_roles" ADD FOREIGN KEY ("job_role_id") REFERENCES "job_roles" ("job_role_id");

ALTER TABLE "project_job_roles" ADD FOREIGN KEY ("job_role_id") REFERENCES "job_roles" ("job_role_id");
ALTER TABLE "project_job_roles" ADD FOREIGN KEY ("project_id") REFERENCES "projects" ("project_id");

ALTER TABLE "project_users" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("user_id");
ALTER TABLE "project_users" ADD FOREIGN KEY ("project_id") REFERENCES "projects" ("project_id");

ALTER TABLE "project_join_requests" ADD FOREIGN KEY ("requester_id") REFERENCES "users" ("user_id");
ALTER TABLE "project_join_requests" ADD FOREIGN KEY ("project_id") REFERENCES "projects" ("project_id");
ALTER TABLE "project_join_requests" ADD CONSTRAINT uq_requester_project UNIQUE (requester_id, project_id);

ALTER TABLE "project_join_request_rejections" ADD FOREIGN KEY ("join_request_id")
    REFERENCES "project_join_requests" ("join_request_id");
