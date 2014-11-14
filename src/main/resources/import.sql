# Startup data

ALTER TABLE `user` CONVERT TO CHARACTER SET utf8 COLLATE utf8_general_ci;

## Users
INSERT INTO `user` (id, created_date, name, surname, email, password, city, street, house_number, about) VALUES
  (1, NOW(), 'Admin', 'Adminowy', 'admin@admin.pl', '$2a$10$Bdbq653hfPq2li9p/.6htO/fySa6OI90ZhtreuEUtyI9RpdtHRTqK','Wrocław', 'Sienkiewicza', '30/21', 'Tutaj jakieś informacje o mnie'),
  (2, NOW(), 'Krzysiek', 'Milecki', 'kmilecki@user.pl', '$2a$10$Bdbq653hfPq2li9p/.6htO/fySa6OI90ZhtreuEUtyI9RpdtHRTqK', 'Wrocław', 'Sienkiewicza', '30/44', 'O mnie ...'),
  (3, NOW(), 'Bartek', 'Bogucki', 'bbogucki@user.pl', '$2a$10$Bdbq653hfPq2li9p/.6htO/fySa6OI90ZhtreuEUtyI9RpdtHRTqK', 'Wrocław', 'Chorwacka', '30/144', 'O mnie ...'),
  (4, NOW(), 'Roberto', 'Sanchez', 'rsanchze@user.pl', '$2a$10$Bdbq653hfPq2li9p/.6htO/fySa6OI90ZhtreuEUtyI9RpdtHRTqK', 'Wrocław', 'Rynek', '4/441', 'O mnie ...'),
  (5, NOW(), 'Maciek', 'Gruzer', 'mgruzer@user.pl', '$2a$10$Bdbq653hfPq2li9p/.6htO/fySa6OI90ZhtreuEUtyI9RpdtHRTqK', 'Wrocław', 'Jugosławiańska', '43/2m', 'O mnie ...');

# Roles
INSERT INTO `user_roles` (user_id, roles) VALUES
  (1, 'ADMIN'),
  (2, 'NORMAL_USER'),
  (3, 'NORMAL_USER'),
  (4, 'NORMAL_USER'),
  (5, 'NORMAL_USER');