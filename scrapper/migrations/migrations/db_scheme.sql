CREATE TABLE if not exists chats (
 id BIGINT PRIMARY KEY,
 chat_id BIGINT NOT NULL UNIQUE
);

CREATE TABLE if not exists  links (
 id BIGINT PRIMARY KEY,
 chat_id BIGINT NOT NULL,
 link_url VARCHAR ( 255 ) NOT NULL,
 FOREIGN KEY (chat_id) REFERENCES chats (chat_id)
);


