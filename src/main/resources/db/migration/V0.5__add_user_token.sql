CREATE TABLE IF NOT EXISTS "user"."token"(
    id			UUID			NOT NULL,
    "type"		INT4			NOT NULL,
    "token"		TEXT			NOT NULL,
    created_at	TIMESTAMPTZ(6)	NOT NULL,
    valid_until	TIMESTAMPTZ(6)	NOT NULL,
    user_id		UUID			NOT NULL,
    CONSTRAINT pk_user_token PRIMARY KEY (id),
    CONSTRAINT fk_user_user_token FOREIGN KEY (user_id) REFERENCES "user"."user" (id)
);

-- ROLLBACK

-- DROP TABLE IF EXISTS "user"."token";