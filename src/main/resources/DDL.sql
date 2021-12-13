#CREATE/DROP DATABASE

create database if not exists army;
drop database if exists army;


#CREATE/DROP TABLES

use army;


drop table if exists soldiers;
drop table if exists recruits;
drop table if exists ranks;
drop table if exists military_unit_tank;
drop table if exists military_unit_plane;
drop table if exists military_unit_weapon;
drop table if exists military_unit_ammo;
drop table if exists weapons;
drop table if exists tanks;
drop table if exists planes;
drop table if exists ammo;
drop table if exists military_units;
drop table if exists armies;

create table if not exists armies (
id bigint unsigned not null auto_increment,
country varchar(20) not null unique,
primary key(id)
);

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

create table if not exists weapons (
id bigint unsigned not null auto_increment,
type varchar(20) not null unique,
primary key(id)
);

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

create table if not exists tanks (
id bigint unsigned not null auto_increment,
type varchar(20) not null unique,
primary key(id)
);

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

create table if not exists planes (
id bigint unsigned not null auto_increment,
type varchar(20) not null unique,
primary key(id)
);

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

create table if not exists ammo (
id bigint unsigned not null auto_increment,
type varchar(20) not null unique,
primary key(id)
);

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

create table if not exists recruits (
id bigint unsigned not null auto_increment,
first_name varchar(20) not null,
last_name varchar(20) not null,
birthday date not null,
primary key(id)
);

create table if not exists ranks (
id bigint unsigned not null auto_increment,
type varchar(20) not null unique,
primary key(id)
);

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