ALTER TABLE board.card
RENAME COLUMN "order" TO "priority";

ALTER TABLE board.card
ADD COLUMN reach_current_column_at TIMESTAMPTZ(6) NOT NULL DEFAULT NOW();

-- ROLLBACK

-- ALTER TABLE board.card
-- RENAME COLUMN "priority" TO "order";

-- ALTER TABLE board.card
-- DROP COLUMN reach_current_column_at;