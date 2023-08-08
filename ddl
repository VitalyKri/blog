

CREATE TABLE IF NOT EXISTS public.users
(
    id uuid,
    first_name character varying(255),
    last_name character varying(255),
    gender character varying(63),
    date_of_birth timestamp with time zone,
    city character varying(511),
    picture_id uuid,
    about_me character varying(1023),
    nickname character varying(127),
    email character varying(511),
    phone character varying(63),
    is_deleted boolean,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.hard_skills
(
    id uuid,
    name character varying(255),
    description character varying(1023),
    is_deleted boolean,
	PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.hard_skills_users
(
    user_id uuid NOT NULL,
    hard_skill_id uuid NOT NULL,
    description character varying(1023),
    is_deleted boolean,
    PRIMARY KEY (user_id, hard_skill_id)
);

CREATE TABLE IF NOT EXISTS public.subscribers
(
    id uuid,
    user_id uuid,
    subscriber_id uuid,
    is_deleted boolean,
    PRIMARY KEY (user_id, subscriber_id)
);

CREATE TABLE IF NOT EXISTS public.pictures
(
    id uuid,
    path_picture character varying(1023),
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public.users
    ADD CONSTRAINT "FK_user_picture" FOREIGN KEY (picture_id)
    REFERENCES public.pictures (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.hard_skills_users
    ADD CONSTRAINT "FK_hard_skills_users_users" FOREIGN KEY (user_id)
    REFERENCES public.users (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.hard_skills_users
    ADD CONSTRAINT "FK_hard_skills_users_hard_skills" FOREIGN KEY (hard_skill_id)
    REFERENCES public.hard_skills (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.subscribers
    ADD CONSTRAINT "FK_subscribers_user" FOREIGN KEY (user_id)
    REFERENCES public.users (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.subscribers
    ADD CONSTRAINT "FK_subscribers_user_subscriber" FOREIGN KEY (subscriber_id)
    REFERENCES public.users (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;

