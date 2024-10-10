create sequence if not exists packages_id_seq;

CREATE TABLE IF NOT EXISTS public.packages
(
    id bigint NOT NULL DEFAULT nextval('packages_id_seq'::regclass),
    name character varying(255) COLLATE pg_catalog."default",
    pack bytea,
    symbol character(1) COLLATE pg_catalog."default",
    CONSTRAINT packages_pkey PRIMARY KEY (id),
    CONSTRAINT uk_9dfk84fuqvrtes1rfcuqun4ri UNIQUE (symbol),
    CONSTRAINT uk_eqxgfbgi0k6ir1yjyadhnghdr UNIQUE (name)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.packages
    OWNER to postgres;