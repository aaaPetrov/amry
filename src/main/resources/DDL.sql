#CREATE/DROP DATABASE

create database if not exists army;
drop database if exists army;


#CREATE/DROP TABLES

use army;

create table if not exists armies (
id bigint unsigned not null auto_increment,
country varchar(20) not null unique,
primary key(id)
);

drop table if exists armies;

create table if not exists military_units (
id bigint unsigned not null auto_increment,
army_id bigint unsigned not null,
name varchar(70) not null,
longitude decimal(8,6) unsigned not null,
latitude decimal(8,6) unsigned not null,
primary key(id),
constraint fk_military_units_army_id foreign key(army_id) references armies(id)
on update no action
on delete cascade
);

drop table if exists military_units;

create table if not exists weapons (
id bigint unsigned not null auto_increment,
type enum('P.APS', 'P.SPS', 'P.MR-444', 'P.MP-448', 'P.P-96', 'A.AKM', 'A.AK-47', 'A.AK-74M',
'A.9A91', 'A.A-91M', 'SR.SVD', 'SR.SVY_AS', 'SR.SV-98', 'SR.OSV-96', 'SR.ASVK', 'MG.RPK', 'MG.PK',
'MG.PKM', 'MG.PKMT', 'MG.PKMB', 'MG.KPV', 'GL.GP-25', 'GL.6G30', 'GL.GM-94', 'GL.RMG', 'GL.RPG26', 'GL.RPG32') not null unique,
primary key(id)
);

drop table if exists weapons;

create table if not exists military_unit_weapon (
weapon_id bigint unsigned not null,
military_unit_id bigint unsigned not null,
amount int unsigned not null,
constraint fk_military_unit_weapon_weapon_id foreign key(weapon_id) references weapons(id)
on update no action
on delete cascade,
constraint fk_military_unit_weapon_military_unit_id foreign key(military_unit_id) references military_units(id)
on update no action
on delete cascade
);

drop table if exists military_unit_weapon;

create table if not exists tanks (
id bigint unsigned not null auto_increment,
type enum('2С25 Sproot-CD', '2С31 Vena', 'BMD-4', 'BMD-4M', 'BTR-90', 'BTR-MD', 'T-14 Armata',
'T-15', 'T-90', 'T-95 Object', 'TOC-1 Pinocchio', 'TOC-1A Sunfire', 'Black Eagle') not null unique,
primary key(id)
);

drop table if exists tanks;

create table if not exists military_unit_tank (
tank_id bigint unsigned not null,
military_unit_id bigint unsigned not null,
amount int unsigned not null,
constraint fk_military_unit_tank_tank_id foreign key(tank_id) references tanks(id)
on update no action
on delete cascade,
constraint fk_military_unit_tank_military_unit_id foreign key(military_unit_id) references military_units(id)
on update no action
on delete cascade
);

drop table if exists military_unit_tank;

create table if not exists planes (
id bigint unsigned not null auto_increment,
type enum('MiG-35', 'Cy-57', 'Ty-160', 'Cy-25', 'Cy-35C', 'Cy-47', 'Ty-22M3', 'An-124', 'B-52') not null unique,
primary key(id)
);

drop table if exists planes;

create table if not exists military_unit_plane (
plane_id bigint unsigned not null,
military_unit_id bigint unsigned not null,
amount int unsigned not null,
constraint fk_military_unit_plane_plane_id foreign key(plane_id) references planes(id)
on update no action
on delete cascade,
constraint fk_military_unit_plane_military_unit_id foreign key(military_unit_id) references military_units(id)
on update no action
on delete cascade
);

drop table if exists military_unit_plane;

create table if not exists ammo (
id bigint unsigned not null auto_increment,
type enum('5.45х39mm', '5.56х45mm', '6х51mm', '7.62х39mm', '7.62х54mm R', '7.92х57mm', '8.6x70mm') not null unique,
primary key(id)
);

drop table if exists ammo;

create table if not exists military_unit_ammo (
ammo_id bigint unsigned not null,
military_unit_id bigint unsigned not null,
amount int unsigned not null,
constraint fk_military_unit_ammo_ammo_id foreign key(ammo_id) references ammo(id)
on update no action
on delete cascade,
constraint fk_military_unit_ammo_military_unit_id foreign key(military_unit_id) references military_units(id)
on update no action
on delete cascade
);

drop table if exists military_unit_ammo;

create table if not exists recruits (
id bigint unsigned not null auto_increment,
first_name varchar(20) not null,
last_name varchar(20) not null,
birthday date not null,
primary key(id)
);

drop table if exists recruits;

create table if not exists ranks (
id bigint unsigned not null auto_increment,
type enum('Squaddie', 'Corporal', 'Lance-sergeant', 'Sergeant', 'Staff-sergeant', 'Senior-sergeant', 'Warrant',
'Senior-warrant', 'Sublieutenant', 'Lieutenant', 'Senior-lieutenant', 'Captain', 'Major', 'Lieutenant-colonel', 
'Colonel', 'Major-general', 'Lieutenant-general', 'Colonel-general') not null unique,
primary key(id)
);

drop table if exists ranks;

create table if not exists soldiers (
id bigint unsigned not null auto_increment,
recruit_id bigint unsigned not null unique,
rank_id bigint unsigned not null,
military_unit_id bigint unsigned not null,
entered_the_service date not null,
end_of_service date not null,
primary key(id),
constraint fk_soldiers_recruit_id foreign key(recruit_id) references recruits(id)
on update no action
on delete cascade,
constraint fk_soldiers_rank_id foreign key(rank_id) references ranks(id)
on update no action
on delete cascade,
constraint fk_soldiers_military_unit_id foreign key(military_unit_id) references military_units(id)
on update no action
on delete cascade
);

drop table if exists soldiers;