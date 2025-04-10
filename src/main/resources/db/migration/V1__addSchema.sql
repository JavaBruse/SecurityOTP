CREATE TABLE users (
  id VARCHAR(255) PRIMARY KEY,
  email VARCHAR(255) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  role VARCHAR(255) NOT NULL CHECK (role IN ('ROLE_USER', 'ROLE_ADMIN')),
  username VARCHAR(255) NOT NULL UNIQUE,
  phone VARCHAR(255) NOT NULL UNIQUE,
  telegram VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE otp_code (
  id BIGSERIAL PRIMARY KEY,
  code VARCHAR(255) NOT NULL,
  operation_id VARCHAR(255),
  created_at TIMESTAMP NOT NULL,
  user_id VARCHAR(255) NOT NULL,
  state VARCHAR(50) NOT NULL CHECK (state IN ('ACTIVE', 'EXPIRED', 'USED')),
  CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES security_schema.users(id) ON DELETE CASCADE
);

CREATE TABLE options_otp (
  id BIGINT PRIMARY KEY,
  time_life BIGINT NOT NULL,
  count_chars INT NOT NULL
);

INSERT INTO security_schema.options_otp (id, time_life, count_chars)
VALUES (1, 300000, 6);
