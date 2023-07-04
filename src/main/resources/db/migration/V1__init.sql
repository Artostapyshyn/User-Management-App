CREATE TABLE IF NOT EXISTS users
(
    user_id       bigserial PRIMARY KEY,
    user_address  varchar(255) NOT NULL,
    user_email    varchar(255) NOT NULL,
    user_name     varchar(255) NOT NULL,
    user_password varchar(255) NOT NULL,
    user_role     varchar NOT NULL,
    CONSTRAINT uk_users_user_email UNIQUE (user_email)
);

INSERT INTO users (user_role, user_name, user_email, user_password, user_address) VALUES ('ROLE_ADMIN', 'admin', 'admin@gmail.com', '$2a$04$M1QKsvEunOcdRmY5SOkfK.OEgO6mAknOQEf.M3dUmBcQFVQZXJSt6', 'Admin address');
INSERT INTO users (user_role, user_name, user_email, user_password, user_address) VALUES ('ROLE_USER', 'user', 'user@gmail.com','$2a$04$HczQoHy2L9OZYbFLzesVuevnlZT5MP.6OW4sKJXGp8RcOMvXpSfWq', 'User address');

