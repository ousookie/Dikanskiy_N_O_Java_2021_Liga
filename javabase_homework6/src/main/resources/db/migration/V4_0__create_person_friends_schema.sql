CREATE TABLE IF NOT EXISTS person_friends
(
    person_id BIGINT REFERENCES person ON DELETE CASCADE,
    friend_id BIGINT REFERENCES person ON DELETE RESTRICT,
    PRIMARY KEY (person_id, friend_id)
);

