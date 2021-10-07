CREATE TABLE IF NOT EXISTS post
(
    id        BIGINT       NOT NULL PRIMARY KEY,
    name      VARCHAR(255) NOT NULL,
    content   TEXT         NOT NULL,
    person_id BIGINT REFERENCES person ON DELETE CASCADE
);

