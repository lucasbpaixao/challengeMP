CREATE TABLE person(
    id int8 NOT NULL,
    name varchar(255) NOT NULL,
    last_name varchar(255) NOT NULL,
    email varchar(255) NOT NULL,
    gender varchar(20) NOT NULL,
    access_ip varchar(16) NOT NULL,
    age integer NOT NULL,
    birth_date timestamp NOT NULL,
    CONSTRAINT person_pkey PRIMARY KEY(id)

);