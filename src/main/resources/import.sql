# Startup data

ALTER TABLE user CONVERT TO CHARACTER SET utf8 COLLATE utf8_general_ci;

## Users
INSERT INTO user (id, created_date, name, surname, email, password, role, city, street, house_number, about) VALUES
  (1, NOW(), 'Admin', 'Adminowy', 'admin@admin.pl', '$2a$10$Bdbq653hfPq2li9p/.6htO/fySa6OI90ZhtreuEUtyI9RpdtHRTqK', 'ADMIN', 'Wrocław', 'Sienkiewicza', '30/21', 'Tutaj jakieś informacje o mnie');

