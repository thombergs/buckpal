CREATE TABLE BOOK
(
    id        BIGINT auto_increment NOT NULL,
    title     VARCHAR(50)           NOT NULL,
    author_id BIGINT                NOT NULL
);

CREATE TABLE AUTHOR
(
    id          BIGINT auto_increment NOT NULL,
    name VARCHAR(50)           NOT NULL
);