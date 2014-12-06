# Startup data

ALTER TABLE `user` CONVERT TO CHARACTER SET utf8 COLLATE utf8_general_ci;

## Users
INSERT INTO `user` (id, created_date, name, surname, email, password, city, street, house_number, about) VALUES
  (1, NOW(), 'Admin', 'Adminowy', 'admin@admin.pl', '$2a$10$Bdbq653hfPq2li9p/.6htO/fySa6OI90ZhtreuEUtyI9RpdtHRTqK','Wrocław', 'Sienkiewicza', '30/21', 'Tutaj jakieś informacje o mnie'),
  (2, NOW(), 'Krzysiek', 'Milecki', 'kmilecki@user.pl', '$2a$10$Bdbq653hfPq2li9p/.6htO/fySa6OI90ZhtreuEUtyI9RpdtHRTqK', 'Wrocław', 'Sienkiewicza', '30/44', 'O mnie ...'),
  (3, NOW(), 'Bartek', 'Bogucki', 'bbogucki@user.pl', '$2a$10$Bdbq653hfPq2li9p/.6htO/fySa6OI90ZhtreuEUtyI9RpdtHRTqK', 'Wrocław', 'Chorwacka', '30/144', 'O mnie ...'),
  (4, NOW(), 'Roberto', 'Sanchez', 'rsanchze@user.pl', '$2a$10$Bdbq653hfPq2li9p/.6htO/fySa6OI90ZhtreuEUtyI9RpdtHRTqK', 'Wrocław', 'Rynek', '4/441', 'O mnie ...'),
  (5, NOW(), 'Maciek', 'Gruzer', 'mgruzer@user.pl', '$2a$10$Bdbq653hfPq2li9p/.6htO/fySa6OI90ZhtreuEUtyI9RpdtHRTqK', 'Wrocław', 'Jugosławiańska', '43/2m', 'O mnie ...'),
  (6, NOW(), 'Maciek', 'Gol', 'mac_3@op.pl', '$10$jtyizwbyOGPCVRzfpV2tYewd5xSpneQOhC0G.eTU5lEscsjX3Qo/q', 'Wrocław', 'Wittiga', '43/2m', 'O mnie ...');

# Roles
INSERT INTO `user_roles` (user_id, roles) VALUES
  (1, 'ADMIN'),
  (2, 'NORMAL_USER'),
  (3, 'NORMAL_USER2'),
  (4, 'NORMAL_USER3'),
  (5, 'NORMAL_USER4');

## Teams
  INSERT INTO `suspy`.`team` (`id`, `created_date`, `deleted`, `name`, `leader_id`) VALUES
  ('1', '1990-10-10', 0, '90ties kids', '1'),
  ('2', NOW(), 0, 'Wittigowo', '2'),
  ('3', '1990-10-10', 0, 'Univerki', '3'),
  ('4', NOW(), 0, 'WrocStud', '4');

# User Teams
  INSERT INTO `suspy`.`user_team` (`user_id`, `team_id`) VALUES
  ('1', '1'), ('2', '1'),  ('3', '1'),
  ('1', '2'), ('2', '2'),
  ('1', '3'), ('3', '3'),
  ('1', '4'),  ('3', '4'),  ('4', '4');
# Observation
  INSERT INTO `suspy`.`observation` (`user`, `observing_user_id`) VALUES
  ('1', '2'), ('1', '3'), ('1', '4'), ('1', '5'),
  ('2', '5'), ('2', '3'),
  ('3', '5'),
  ('4', '5'),('4', '1'),('4', '3'),
  ('5', '5');

## Places
INSERT INTO `suspy`.`place` (`id`, `created_date`, `deleted`, `accepted`, `city`, `house_number`, `street`, `capacity`, `name`, `owner_id`) VALUES
  ('1', NOW(), false, true , 'Internet', '99', 'League of Legends', '10', 'Games: League of Legends', '1'),
  ('2', NOW(), false, true , 'Wroclaw', '60', 'Wittiga', '100', 'Boisko Akademiki', '1'),
  ('3', NOW(), false, true , 'Przeworsk', '60', 'Mickiewicza', '50', 'Basen', '1'),
  ('4', NOW(), false, true , 'Warszawa', '60', 'Alicjanowicza', '20', 'Hala sportowa 1', '1'),
  ('5', NOW(), false, true , 'Warszawa', '60', 'Alicjanowicza', '20', 'Hala sportowa 2', '1'),
  ('6', NOW(), false, true , 'Warszawa', '60', 'Alicjanowicza', '20', 'Hala sportowa 3', '1'),
  ('7', NOW(), false, true , 'Milanowice', '60', 'Slowackiego', '100', 'Stadion', '1'),
  ('8', NOW(), false, true , 'Wroclaw', '60', 'Chrobrego', '100', 'Basen', '1'),
  ('9', NOW(), false, true , 'Wroclaw', '60', 'Chrobrego', '20', 'Boisko do koszykówki', '1'),
  ('10', NOW(), false, true , 'Wroclaw', '60', 'Chrobrego', '40', 'Orlik', '1');

#Places activity (TODO)

#Time Slots
  INSERT INTO `suspy`.`time_slot` (`id`, `created_date`, `deleted`, `date_from`, `price`, `date_to`, `place_id`) VALUES
  ('1', NOW(), 0, NOW(), '100', '2015-01-01', '1'),
  ('2', NOW(), 0, NOW(), '111', '2015-02-02', '2'),
  ('3', NOW(), 0, NOW(), '222', '2015-03-03', '3'),
  ('4', NOW(), 0, NOW(), '333', '2015-04-04', '4'),
  ('5', NOW(), 0, NOW(), '444', '2015-05-06', '5'),
  ('6', '2000-05-06', 0, '2014-05-06', '444', '2014-06-06', '6'),
  ('7', '2010-05-06', 0, '2013-05-06', '2000', '2014-06-08', '7'),
  ('8', '2010-05-06', 0, '2014-11-11', '40', '2014-12-12', '7');

## Event
INSERT INTO `suspy`.`event` (`id`, `created_date`, `deleted`, `name`, `priv`, `team_id`, `time_slot_id`) VALUES
('1', '2000-01-01', 0, 'Niedzielna gra', 0, '1', '1'),
('2', '2000-01-01', 0, 'Niedzielna gra 2', 0, '1', '1'),
('3', '2000-01-01', 0, 'Niedzielna gra 3', 0, '1', '1'),
('4', '2000-01-01', 0, 'Ja gra', 0, '1', '2'),
('5', '2000-01-01', 0, 'Kosz', 0, '2', '3'),
('6', '2000-01-01', 0, 'Reczna', 0, '2', '4'),
('7', '2000-01-01', 0, 'tenis', 0, '2', '5'),
('8', '2000-01-01', 0, 'MASAKRYCZNA GRA', 0, '4', '6'),
('9', '2000-01-01', 0, 'Lepiej Masakryczna Gra', 0, '4', '7'),
('10', '2000-01-01', 0, 'Bardziej Lepiej Masakryczna gra', 0, '4', '8');