DELETE FROM meals;
DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

SELECT id from users WHERE name = 'User' and email = 'user@yandex.ru';

INSERT INTO meals (dateTime, description, calories, userId) VALUES
  (TIMESTAMP 'yesterday', 'Катлетос', 800 , 100000),
  (TIMESTAMP 'yesterday', 'Сасисон', 800 ,  100000),
  (now()+ interval '1 hour', 'Катлетос', 800 , 100000),
  (now()+ interval '1 minute', 'Сасисон', 800 ,  100000),
  (now(), 'Кекс', 800 ,     100000),
  (now(), 'Сасисон', 800 ,  100001),
  (now()+ interval '1 hour', 'Катлетос', 800 , 100001),
  (now()+ interval '1 minute', 'Кекс', 800 ,     100001),
  (TIMESTAMP 'yesterday', 'Катлетос', 800 , 100001),
  (TIMESTAMP 'yesterday', 'Сасисон', 800 ,  100001);
