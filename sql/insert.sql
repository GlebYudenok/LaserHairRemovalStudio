INSERT INTO USER (id, login, password, email, role)
VALUES ('1', 'larionova.stan', 'kcmUXF', 'missobino-2015@yopmail.com', 'client');
INSERT INTO user_info (user_id, name, surname, avatar_link, birth_date, phone_number, gender) VALUES
('1', 'Ларионова', 'Станислава', 'img/userAvatar/iGYPJ9KRO.jpg', '2001-01-05', '+375297799270', 'male');
INSERT INTO USER (id, login, password, email, role) VALUES
('2', 'artemov.mak', '0Jl3H9', 'uffodadett-4474@yopmail.com', 'admin');
INSERT INTO USER_INFO (user_id, name, surname, avatar_link, birth_date, phone_number, gender) VALUES
('2', 'Артемов', 'Максим', 'img/userAvatar/28349_800.jpg', '1965-06-06', '+375297125115', 'female');
INSERT INTO USER (id, login, password, email, role) VALUES
('3', 'and.kuleshov', 'yGGeT5', 'ilevezimm-7113@yopmail.com', 'master');
INSERT INTO user_info (user_id, name, surname, avatar_link, birth_date, phone_number, gender) VALUES
('3', 'Кулешов', 'Андрей', 'img/userAvatar/i.jpg', '1995-03-12', '+375445540091', 'female');
INSERT INTO USER (id, login, password, email, role) VALUES
('4', 'kotova.oliviya', 'cQp33E', 'ytejama-2493@yopmail.com', 'client');
INSERT INTO user_info (user_id, name, surname, avatar_link, birth_date, phone_number, gender) VALUES
('4', 'Котова', 'Оливия', 'img/userAvatar/scale_1200.jpg', '2004-12-13', '+375123456789', 'male');
INSERT INTO USER (id, login, password, email, role) VALUES
('5', 'zaytsev.vlad', 'YjXaCD', 'ulewezu-0804@yopmail.com', 'master');
INSERT INTO user_info (user_id, name, surname, avatar_link, birth_date, phone_number, gender) VALUES
('5', 'Зайцев', 'Владимир', 'img/userAvatar/s1200.jpg', '1991-05-31', '+375336688241', 'female');




INSERT INTO service (id, zone_name, price)
VALUES ('1', 'upper lip', '18'),
       ('2', 'eyebrow', '18'),
       ('3', 'cheeks', '18'),
       ('4', 'chin', '18'),
       ('5', 'whiskey', '12'),
       ('6', 'neck: front surface', '18'),
       ('7', 'fingers', '12'),
       ('8', 'Hands', '18'),
       ('9', 'forearms (arms to elbow)', '45'),
       ('10', 'shoulders (arms above elbow)', '45'),
       ('11', 'armpits', '27'),
       ('12', 'toes', '10'),
       ('13', 'foot (rise)', '10'),
       ('14', 'hip', '68'),
       ('15', 'shin', '68'),
       ('16', 'neck: back surface, nape', '20'),
       ('17', 'chest halo', '12'),
       ('18', 'chest full', '78'),
       ('19', 'belly: white line', '20'),
       ('20', 'belly full', '75'),
       ('21', 'back fully', '75'),
       ('22', 'buttocks', '30'),
       ('23', 'deep bikini', '50'),
       ('24', 'classic bikini', '40');

INSERT INTO complex_service (id, service_id, name, price, user_gender) VALUES
('1', '1', 'ComplexBeijing', '65', 'female'),
('1', '2', 'ComplexBeijing', '65', 'female'),
('1', '3', 'ComplexBeijing', '65', 'female'),

('2', '4', 'RioDejaneiro', '120', 'female'),
('2', '5', 'RioDejaneiro', '120', 'female'),
('2', '7', 'RioDejaneiro', '120', 'female'),

('3', '10', 'NewYork', '135', 'male'),
('3', '12', 'NewYork', '135', 'male'),
('3', '15', 'NewYork', '135', 'male');

INSERT INTO appointment (id, date_n_time, user_id, complex_id) VALUES
('1', '2019-06-09 17:30:00', '1', '1'),
('2', '2019-06-09 18:15', '2', '3');

INSERT INTO appointment (id, date_n_time, user_id, service_id) VALUES
('3', '2019-07-09 12:00', '1', '2');

INSERT INTO pictures (id, link) VALUES ('1',
'/img/userAvatar/i.jpg');