CREATE TABLE user_model(
    id int8 NOT NULL,
    login varchar(255) NOT NULL,
    password varchar(255) NOT NULL,
    CONSTRAINT user_pkey PRIMARY KEY(id)
);

INSERT INTO user_model(id, login, password) VALUES(1,'user_default', '$2a$10$3eL6KSVlJHkI3TjcWyDTO.idPJFJqwbNZUEX1hrz7lsJxkTkKDDWa');