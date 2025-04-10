CREATE TABLE  users (
  id VARCHAR(255) NOT NULL ,
  email VARCHAR(255) NOT NULL unique,
  password VARCHAR(255) NOT NULL,
  role VARCHAR(255) NOT NULL check (role in ('ROLE_USER','ROLE_ADMIN')),
  username varchar(255) not null unique,
  PRIMARY KEY (id));


CREATE TABLE otp_code (
  id_user VARCHAR(255) NOT NULL PRIMARY KEY,
  otp_code VARCHAR(255) NOT NULL,
  date_die BIGINT NOT NULL,
  CONSTRAINT fk_user FOREIGN KEY (id_user) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE security_schema.options_otp (
  id BIGINT PRIMARY KEY,
  time_life BIGINT NOT NULL,
  count_chars BIGINT NOT NULL
);

INSERT INTO security_schema.options_otp (id, time_life, count_chars)
VALUES (1, 300000, 6);

