CREATE TABLE IF NOT EXISTS school
(
    id      BIGINT       NOT NULL PRIMARY KEY,
    name    VARCHAR(255) NOT NULL,
    address varchar(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS person
(
    id        BIGINT       NOT NULL PRIMARY KEY,
    name      VARCHAR(255) NOT NULL,
    email     VARCHAR(255) NOT NULL,
    school_id BIGINT       REFERENCES school ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS post
(
    id        BIGINT       NOT NULL PRIMARY KEY,
    name      VARCHAR(255) NOT NULL,
    content   TEXT         NOT NULL,
    person_id BIGINT REFERENCES person ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS person_friend
(
    person_id BIGINT REFERENCES person ON DELETE CASCADE,
    friend_id BIGINT REFERENCES person ON DELETE SET NULL,
    PRIMARY KEY (person_id, friend_id)
);





