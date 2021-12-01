#INSERT

use army;

insert into armies(country)
values("Russia"),
("Belorus"),
("Ukrain");

insert into military_units(army_id, name, longitude, latitude)
value(1, 'russian_unit_1', 52.314795, 36.623203);
insert into military_units(army_id, name, longitude, latitude)
value(1, 'russian_unit_2', 52.570933, 36.192109);
insert into military_units(army_id, name, longitude, latitude)
value(1, 'russian_unit_3', 55.085285, 33.452858);
insert into military_units(army_id, name, longitude, latitude)
value(1, 'russian_unit_4', 57.719913, 31.039518);
insert into military_units(army_id, name, longitude, latitude)
value(1, 'russian_unit_5', 60.190917, 34.305286);

insert into military_units(army_id, name, longitude, latitude)
value(2, 'belorusian_unit_1', 55.572389, 29.553848);
insert into military_units(army_id, name, longitude, latitude)
value(2, 'belorusian_unit_2',52.020756, 28.095594);
insert into military_units(army_id, name, longitude, latitude)
value(2, 'belorusian_unit_3', 52.534408, 23.987488);

insert into military_units(army_id, name, longitude, latitude)
value(3, 'ukrainian_unit_1', 51.445091, 29.101556);
insert into military_units(army_id, name, longitude, latitude)
value(3, 'ukrainian_unit_2', 51.742052, 26.018921);
insert into military_units(army_id, name, longitude, latitude)
value(3, 'ukrainian_unit_3', 48.056137, 24.991583);
insert into military_units(army_id, name, longitude, latitude)
value(3, 'ukrainian_unit_4', 48.778728, 22.703077);

insert into tanks(type)
values('T_2C25'), ('T_2C31'), ('BMD_4'), ('BMD_4M'),
('BTR_90'), ('BTR_MD'), ('T_14'), ('T_15'), ('T_90'),
('T_95'), ('TOC_1'), ('TOC_1A'), ('BLACK_EAGLE');

insert into military_unit_tank(tank_id, military_unit_id, amount)
values(1, 1, 10), (2, 1, 15), (7, 1, 4), (2, 2, 3), (3, 2, 12), (10, 2, 3),
(9, 3, 14), (8, 3, 8), (12, 3, 6), (4, 4, 9), (5, 4, 1), (6, 4, 2),
(1, 5, 6), (11, 5, 16), (7, 5, 4), (3, 5, 8), (2, 6, 5), (4, 6, 11), (8, 6, 6),
(6, 7, 4), (1, 7, 12), (5, 7, 18), (11, 8, 4), (10, 8, 8), (12, 8, 13),
(3, 9, 9), (6, 9, 7), (8, 9, 10), (4, 10, 15), (5, 10, 7), (9, 10, 6),
(10, 11, 4), (7, 11, 12), (1, 11, 3), (2, 12, 17), (9, 12, 7), (12, 12, 12);

insert into planes(type)
values('MIG_35'), ('CY_57'), ('TY_160'), ('CY_25'),
('CY_35C'), ('CY_47'), ('TY_22M3'), ('AN_124'), ('B_52');

insert into military_unit_plane(plane_id, military_unit_id, amount)
values(1, 1, 10), (2, 1, 15), (7, 1, 4), (2, 2, 3), (3, 2, 12), (9, 2, 3),
(9, 3, 14), (8, 3, 8), (7, 3, 6), (4, 4, 9), (5, 4, 1), (6, 4, 2),
(1, 5, 6), (6, 5, 16), (7, 5, 4), (3, 5, 8), (2, 6, 5), (4, 6, 11), (8, 6, 6),
(6, 7, 4), (1, 7, 12), (5, 7, 18), (9, 8, 4), (3, 8, 8), (4, 8, 13),
(3, 9, 9), (6, 9, 7), (8, 9, 10), (4, 10, 15), (5, 10, 7), (9, 10, 6),
(5, 11, 4), (7, 11, 12), (1, 11, 3), (2, 12, 17), (9, 12, 7), (6, 12, 12);

insert into weapons(type)
values('P_APS'), ('P_SPS'), ('P_MR_444'), ('P_MP_448'), ('P_P_96'), ('A_AKM'), 
('A_AK_47'), ('A_AK_74M'), ('A_9A91'), ('A_A_91M'), ('SR_SVD'), ('SR_SVY_AS'),
('SR_SV_98'), ('SR_OSV_96'), ('SR_ASVK'), ('MG_RPK'), ('MG_PK'), ('MG_PKM'), ('MG_PKMT'),
('MG_PKMB'), ('MG_KPV'), ('GL_GP_25'), ('GL_6G30'), ('GL_GM_94'), ('GL_RMG'), ('GL_RPG_26'), ('GL_RPG_32');

insert into military_unit_weapon(weapon_id, military_unit_id, amount)
values(3, 1, 30), (6, 1, 45), (21, 1, 12), (6, 2, 9), (9, 2, 36), (27, 2, 9),
(27, 3, 44), (24, 3, 24), (21, 3, 18), (12, 4, 27), (15, 4, 3), (18, 4, 6),
(3, 5, 18), (18, 5, 48), (21, 5, 12), (9, 5, 24), (6, 6, 15), (12, 6, 33), (24, 6, 18),
(18, 7, 12), (3, 7, 36), (15, 7, 54), (27, 8, 12), (9, 8, 24), (12, 8, 36),
(9, 9, 27), (18, 9, 21), (24, 9, 30), (12, 10, 45), (15, 10, 21), (27, 10, 18),
(15, 11, 12), (21, 11, 36), (3, 11, 9), (6, 12, 51), (27, 12, 21), (18, 12, 36);

insert into ammo(type)
values('B_5_45x39'), ('B_5_56x45'), ('B_6x51'), ('B_7_62x39'),
('B_7_62x54_R'), ('B_7_92x57mm'), ('B_8_6x70');

insert into military_unit_ammo(ammo_id, military_unit_id, amount)
values(2, 1, 30000), (4, 1, 45000), (7, 1, 12000), (6, 2, 9000), (5, 2, 36000), (3, 2, 9000),
(6, 3, 44000), (5, 3, 24000), (3, 3, 18), (4, 4, 27000), (5, 4, 3000), (6, 4, 6000),
(1, 5, 18000), (7, 5, 48000), (6, 5, 12000), (3, 5, 24000), (2, 6, 15000), (4, 6, 33000), (6, 6, 18000),
(6, 7, 12000), (2, 7, 36000), (7, 7, 54000), (7, 8, 12000), (3, 8, 24000), (6, 8, 36000),
(4, 9, 27000), (5, 9, 21000), (7, 9, 30000), (3, 10, 45000), (5, 10, 21000), (7, 10, 18000),
(5, 11, 12000), (3, 11, 36000), (4, 11, 9000), (6, 12, 51000), (7, 12, 21000), (4, 12, 36);

insert into ranks(type)
values('SQUADDIE'), ('CORPORAL'), ('LANCE_SERGEANT'), ('SERGEANT'), ('STAFF_SERGEANT'),
('SENIOR_SERGEANT'), ('WARRANT'), ('SENIOR_WARRANT'), ('SUBLIEUTENANT'), ('LIEUTENANT'),
('SENIOR_LIEUTENANT'), ('CAPTAIN'), ('MAJOR'), ('LIEUTENANT_COLONEL'), ('COLONEL'),
('MAJOR_GENERAL'), ('LIEUTENANT_GENERAL'), ('COLONEL_GENERAL');

insert into recruits(first_name, last_name, birthday)
values('Artem', 'Petrov', str_to_date('03-05-1993', '%d-%m-%Y')),
('Fedia', 'Rusakevich', str_to_date('04-07-1992', '%d-%m-%Y')),
('Misha', 'Lapitsky', str_to_date('31-12-1992', '%d-%m-%Y')),
('Maksim', 'Tihonchuk', str_to_date('24-03-1993', '%d-%m-%Y')),
('Artem', 'Beliak', str_to_date('10-06-1992', '%d-%m-%Y')),
('Egor', 'Schaslionok', str_to_date('03-08-1991', '%d-%m-%Y')),
('Gleb', 'Karamzin', str_to_date('12-11-1993', '%d-%m-%Y')),
('Alexey', 'Veretenov', str_to_date('27-10-1993', '%d-%m-%Y')),
('Nikita', 'Kontilev', str_to_date('13-06-1992', '%d-%m-%Y')),
('Stas', 'Zablocki', str_to_date('23-04-1991', '%d-%m-%Y')),
('Sergey', 'Dvornikov', str_to_date('18-09-1992', '%d-%m-%Y')),
('Artem', 'Pozhidaev', str_to_date('11-04-1993', '%d-%m-%Y')),
('Ruslan', 'Said', str_to_date('8-12-1992', '%d-%m-%Y')),
('Alexander', 'Halkovskiy', str_to_date('14-03-1992', '%d-%m-%Y')),
('Vlad', 'Andrievski', str_to_date('10-08-1992', '%d-%m-%Y')),
('Pasha', 'Bugluck', str_to_date('08-03-1991', '%d-%m-%Y')),
('Vadim', 'Gerin', str_to_date('08-05-1991', '%d-%m-%Y')),
('Pasha', 'Poliakovich', str_to_date('01-06-1993', '%d-%m-%Y')),
('Alexey', 'Semenenko', str_to_date('18-08-1993', '%d-%m-%Y')),
('Zahar', 'Makarevich', str_to_date('06-06-1991', '%d-%m-%Y')),
('Alexandr', 'Konopelko', str_to_date('17-01-1993', '%d-%m-%Y')),
('Volodia', 'Naxodko', str_to_date('01-04-1994', '%d-%m-%Y')),
('Dmitriy', 'Baltcevich', str_to_date('27-04-1993', '%d-%m-%Y')),
('Nikolay', 'Puchko', str_to_date('16-12-1991', '%d-%m-%Y')),
('Sasha', 'Krasavin', str_to_date('21-05-1993', '%d-%m-%Y')),
('Vova', 'Voitik', str_to_date('05-02-1992', '%d-%m-%Y'));

insert into soldiers(recruit_id, rank_id, military_unit_id, entered_the_service, end_of_service)
values(1, 17, 1, now(), date_add(now(), interval 30 year)), (2, 18, 1, now(), date_add(now(), interval 10 year)),
(3, 16, 1, now(), date_add(now(), interval 6 year)), (4, 15, 2, now(), date_add(now(), interval 5 year)),
(5, 14, 2, now(), date_add(now(), interval 3 year)), (6, 13, 2, now(), date_add(now(), interval 7 year)),
(7, 12, 3, now(), date_add(now(), interval 5 year)), (8, 11, 3, now(), date_add(now(), interval 8 year)),
(9, 10, 3, now(), date_add(now(), interval 4 year)), (10, 9, 4, now(), date_add(now(), interval 3 year)),
(11, 8, 4, now(), date_add(now(), interval 6 year)), (12, 7, 4, now(), date_add(now(), interval 4 year)),
(13, 6, 5, now(), date_add(now(), interval 5 year)), (14, 5, 5, now(), date_add(now(), interval 10 year)), 
(15, 4, 6, now(), date_add(now(), interval 6 year)), (16, 3, 6, now(), date_add(now(), interval 5 year)),
(17, 3, 7, now(), date_add(now(), interval 3 year)), (18, 3, 7, now(), date_add(now(), interval 2 year)),
(19, 3, 8, now(), date_add(now(), interval 9 year)), (20, 3, 8, now(), date_add(now(), interval 10 year)),
(21, 2, 9, now(), date_add(now(), interval 5 year)), (22, 2, 9, now(), date_add(now(), interval 6 year)),
(23, 2, 10, now(), date_add(now(), interval 7 year)), (24, 1, 10, now(), date_add(now(), interval 3 year)),
(25, 1, 11, now(), date_add(now(), interval 4 year)), (26, 1, 12, now(), date_add(now(), interval 6 year));
