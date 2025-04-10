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
  type VARCHAR(255) NOT NULL CHECK (type IN ('EMAIL', 'SMS', 'TELEGRAM')),
  date_at BIGINT NOT NULL,
  date_die BIGINT NOT NULL,
  CONSTRAINT fk_user FOREIGN KEY (id_user) REFERENCES users(id) ON DELETE CASCADE
);