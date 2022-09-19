SELECT 'CREATE DATABASE agrow'
WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'agrow')\gexec

-- Table: ROLE
CREATE TABLE public.role
(
    role_id uuid NOT NULL,
    role_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT role_pkey PRIMARY KEY (role_id),
    CONSTRAINT uk_role_name UNIQUE (role_name)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;
ALTER TABLE public.role OWNER to postgres;

-- Table: USERS
CREATE TABLE public.users
(
    user_id uuid NOT NULL,
    enabled boolean NOT NULL,
    first_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    last_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    password character varying(255) COLLATE pg_catalog."default" NOT NULL,
    username character varying(255) COLLATE pg_catalog."default" NOT NULL,
    email_validation boolean,
    expiration_email_validation timestamp without time zone,
    email_validation_key uuid,
    email_validation_time timestamp without time zone,
    CONSTRAINT users_pkey PRIMARY KEY (user_id),
    CONSTRAINT uk_username UNIQUE (username)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;
ALTER TABLE public.users OWNER to postgres;

-- Table> USERS_ROLES
CREATE TABLE public.users_roles
(
    user_id uuid NOT NULL,
    role_id uuid NOT NULL,
    CONSTRAINT users_roles_pkey PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_user_id FOREIGN KEY (user_id)
        REFERENCES public.users (user_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_role_id FOREIGN KEY (role_id)
        REFERENCES public.role (role_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;
ALTER TABLE public.users_roles OWNER to postgres;