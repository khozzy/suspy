# Startup data

ALTER TABLE user CONVERT TO CHARACTER SET utf8 COLLATE utf8_general_ci;

## Users
INSERT INTO user (id, created_date, name, surname, email, password, role, city, street, house_number, about) VALUES
  (1, NOW(), 'Admin', 'Adminowy', 'admin@admin.pl', 'samolot123', 'ADMIN', 'Wrocław', 'Sienkiewicza', '30/21', 'Tutaj jakieś informacje o mnie'),
  (2, NOW(), 'Darek', 'Handlowy', 'fitness@darek.pl', 'koks', 'GYM_OWNER', 'Wrocław', 'Koksownicza', '666', 'Mam siłkę. Chodźcie do mnie');

