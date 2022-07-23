CREATE SCHEMA IF NOT EXISTS board;

CREATE SCHEMA IF NOT EXISTS "user";

CREATE TABLE IF NOT EXISTS "user"."user"(
	id 			uuid			PRIMARY KEY,
	first_name	VARCHAR(250)	NOT NULL,
	last_name	VARCHAR(250)	NULL,
	email		VARCHAR(320)	NOT NULL UNIQUE,
	photo_url	VARCHAR(2048)	NULL,
	is_active	bool			NOT NULL,
	created_at	TIMESTAMPTZ(6)	NOT NULL DEFAULT current_timestamp(6)
);

CREATE TABLE IF NOT EXISTS board.board(
	id			uuid			PRIMARY KEY,
	"name"		VARCHAR(150)	NOT NULL,
	created_at	TIMESTAMPTZ(6)	NOT NULL DEFAULT current_timestamp(6)
);

CREATE TABLE IF NOT EXISTS "user".user_board(
	id 			uuid			PRIMARY KEY,
	board_id	uuid			NOT NULL REFERENCES board.board(id),
	user_id		uuid			NOT NULL REFERENCES "user"."user"(id)
);

CREATE TABLE IF NOT EXISTS board.board_column(
	id 			uuid			PRIMARY KEY,
	"name"		VARCHAR(150)	NOT NULL,
	"position"	int				NOT NULL,
	board_id	uuid			NOT NULL REFERENCES board.board(id),
	created_at	TIMESTAMPTZ(6)	NOT NULL DEFAULT current_timestamp(6)
);

CREATE TABLE IF NOT EXISTS board.card(
	id 				uuid			PRIMARY KEY,
	title			VARCHAR(150)	NOT NULL,
	description		TEXT			NULL,
	board_column_id uuid			NOT NULL REFERENCES board.board_column(id),
	start_date		TIMESTAMPTZ(6)	NULL,
	end_date		TIMESTAMPTZ(6)	NULL,
	concluded_at	TIMESTAMPTZ(6)	NULL,
	created_at		TIMESTAMPTZ(6)	NOT NULL DEFAULT current_timestamp(6)
);

CREATE TABLE IF NOT EXISTS "user".user_card(
	id 			uuid			PRIMARY KEY,
	user_id		uuid			NOT NULL REFERENCES "user"."user"(id),
	card_id		uuid			NOT NULL REFERENCES board.card(id)
);

CREATE TABLE IF NOT EXISTS board.card_history(
	id 			uuid			PRIMARY KEY,
	"type"		VARCHAR(50)		NOT NULL,
	"content"	TEXT			NULL,
	card_id		uuid			NOT NULL REFERENCES board.card(id),
	user_id		uuid			NOT NULL REFERENCES "user"."user"(id),
	created_at	TIMESTAMPTZ(6)	NOT NULL DEFAULT current_timestamp(6)
);

CREATE TABLE IF NOT EXISTS board.check_list(
	id 			uuid			PRIMARY KEY,
	title		VARCHAR(150)	NOT NULL,
	card_id		uuid			NOT NULL REFERENCES board.card(id),
	created_at	TIMESTAMPTZ(6)	NOT NULL DEFAULT current_timestamp(6)
);

CREATE TABLE IF NOT EXISTS board.check_list_item(
	id 				uuid			PRIMARY KEY,
	description		VARCHAR(350)	NOT NULL,
	check_list_id	uuid			NOT NULL REFERENCES board.check_list(id),
	created_at		TIMESTAMPTZ(6)	NOT NULL DEFAULT current_timestamp(6)
);

CREATE TABLE IF NOT EXISTS board.tag(
	id 			uuid			PRIMARY KEY,
	title		VARCHAR(150)	NULL,
	color		VARCHAR(7)		NOT NULL,
	board_id	uuid			NOT NULL REFERENCES board.board(id),
	created_at	TIMESTAMPTZ(6)	NOT NULL DEFAULT current_timestamp(6)
);

CREATE TABLE IF NOT EXISTS board.card_tag(
	id 		uuid	PRIMARY KEY,
	card_id	uuid	NOT NULL REFERENCES board.card(id),
	tag_id	uuid	NOT NULL REFERENCES board.tag(id)
);

-- ROLLBACK

-- DROP TABLE IF EXISTS board.card_tag;
-- DROP TABLE IF EXISTS board.tag;
-- DROP TABLE IF EXISTS board.check_list_item;
-- DROP TABLE IF EXISTS board.check_list;
-- DROP TABLE IF EXISTS board.card_history;
-- DROP TABLE IF EXISTS "user".user_card;
-- DROP TABLE IF EXISTS board.card;
-- DROP TABLE IF EXISTS board.board_column;
-- DROP TABLE IF EXISTS "user".user_board;
-- DROP TABLE IF EXISTS board.board;
-- DROP TABLE IF EXISTS "user"."user";
-- DROP SCHEMA IF EXISTS "user";
-- DROP SCHEMA IF EXISTS board;