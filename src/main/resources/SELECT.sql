#SELECT

# Используем базу данных army.
use army;

# Выводим все столбцы и строки из таблицы recruits.
select * from recruits;

# Выводим первые 5 строк таблицы, выводятся все столбцы. 
select * from recruits limit 0,5;

# Выводим столбец birthday из таблицы recruits.
select birthday from recruits;

# Выводим столбцы first_name и last_name из таблицы recruits.
select first_name, last_name from recruits;

# Выводит значения столбца type под псевдонимов tanks_type из таблицы tanks.
select type as tank_types from tanks;

# Выводим неповторяющиеся значения столбца first_name из таблицы recruits.
select distinct(first_name) from recruits;

# Объединяем столбцы first_name и last_name из таблицы recruits,
# с помощью функции concat(), в один общий столбец с псевдонимом name.
select concat(first_name, ' ', last_name) as name from recruits;

# Выводим значение даты и времени на данный момент с помощью функции now(). Выборка из не существующей таблицы dual.
select now() from dual;

# Выводим количество типов оружия под псевдонимом types_count в таблице weapons.
# Подсчёт количества типов оружия производится с помощью функции count.
select count(type) as types_count from weapons;

# Выводит одно случайное значение столбцов first_name и last_name из таблицы recruits.
select first_name, last_name from recruits order by rand() limit 1;

# Выводит значения столбцов first_name и last_name из таблицы recruits, которые родились в 1994 году и позже.
# Условие указывается после ключевого слова where с помощью функции year(DATE) которая возврщает
# год из даты в виде числе и сравнения этого числа ( > 1993 ).
select first_name, last_name from recruits where year(birthday) > 1993;

# Выводит значения столбцов first_name и last_name из таблицы recruits, где значения стобца
# first_name равны одному из значений заданном перечне значений  ('Artem', 'Pasha', 'Ruslan').
select first_name, last_name from recruits where first_name in('Artem', 'Pasha', 'Ruslan');

# Выводит значения столбцов first_name и last_name из таблицы recruits, чьи года рождения
# находятся в промежутке между 1992-ым(включительно) и 1993-им годом(включительно).
select first_name, last_name from recruits where year(birthday) between 1992 and 1993;

# Select из Select-а. Вообще не представляю зачем это может понадобиться.
select A.country from (select * from armies) as A;

# Выводим объеденённую таблицу, в которой будут столбцы A.country, M.name, M.longitude, M.latitude из двух таблиц -
# armies c псевдонимом A и military_units с псевдонимом M.
select A.country, M.name, M.longitude, M.latitude from armies as A, military_units as M;

# Выводим first_name и last_name из таблицы recruits, для которых значение столбца id
# является один из значений вложенного селект запроса, который вернёт значения recruit_id из таблицы 
# soldiers, где значения столбца rank_id будут равны результату ещё одного вложенного select запроса, который вернёт
# значение столбца id из таблицы ranks где значение столбца type будет равно 'Corporal'.
# Результат - выводятся все солдаты со сзванием 'Corporal'.
select first_name, last_name from recruits where id in
(select recruit_id from soldiers where rank_id = (select id from ranks where type like 'Corporal'));

# Выводим столбец entered_the_service под псевдонимом entered_at, столбец end_of_service под псевдонимом end_in
# из таблицы soldiers где значение строки в столбце recruit_id будет равно результату вложенного select запроса.
# Вложенный select запрос выведет значение столбца id из таблицы recruits где значение строки в столбце
# first_name будет равно значению 'Artem', и значение строки в столбце last_name будет равно значению 'Petrov'.
select entered_the_service as entered_at, end_of_service as end_in from soldiers where recruit_id = 
(select id from recruits where first_name like 'Artem' and last_name like 'Petrov');

# Выводим столбец country из таблицы armies у которой псевдонимом A , столбец name из таблицы military_units
# у которой псевдоним M, столбец type из таблицы tanks у которой псевдоним T и столбец amount из таблицы 
# military_unit_tank у которой псевдоним MT. При этом выбираются строки где значения столбца id из таблицы A будут
# равны значениям army_id из таблицы M, и значения столбца id в таблице M будут равны значениям столбца military_unit_id
# из таблицы MT, а значения столбца tank_id из таблицы MT будут равны значениям столбца id из таблицы T. При этом будут выбиратся строки
# который соответствуют условию, что значения столбца  A.country будут равны значению 'Russia', а значения столбца M.name 
# будут равны значению 'russian_unit_3';
# Тут происходит объеденение 4 таблиц посредством inner join. (Выводятся только пересечения 4 таблиц, где значения в таблицах соответствуют условиям).
select A.country, M.name, T.type, MT.amount from armies as A
inner join military_units as M on A.id = M.army_id
inner join military_unit_tank as MT on M.id = MT.military_unit_id
inner join tanks as T on MT.tank_id = T.id
where A.country = 'Russia' and M.name = 'russian_unit_3';

# Тоже самое только из объединённие четырёх таблиц armies, military_units, military_unit_plane и planes.
# Без условия where, но с сортировкой по алфовиту по столбцу country в таблице armies и столбцу name
# в таблице military_units.
select A.country, M.name, P.type, MP.amount from armies as A
inner join military_units as M on A.id = M.army_id
inner join military_unit_plane as MP on M.id = MP.military_unit_id
inner join planes as P on MP.plane_id = P.id
order by A.country, M.name;

# Выбираем значения столбца type из таблицы weapons с псевдонимом W, значения столбца name из таблицы military_units
# с псевданимом M. При этом выбираются строки где значения столбца M.id будут равны значениям столбца MW.military_unit_id,
# и все строки из таблицы weapons(т.к. это right join), строки где значения столбца MW.weapon_id будут равны значениям столбца W.id,
# заполняются в соответсвии с их значениями, а если для какого-то значения W.type нету соответсвующего значения в M.name, то для
# этой строки M.name принимает значение null. Результирующая таблица сортируется по столбцу M.name в обратном алфовитном порядке(desc).
# Посредством этого запроса я смотрю, где используется каждый вид оружия. И какие типы оружия не используются вовсе.
select W.type, M.name from military_units as M
inner join military_unit_weapon as MW on M.id = MW.military_unit_id
right join weapons as W on MW.weapon_id = W.id
order by M.name desc;

# Это для left join.
insert into armies(country) values('Australia'), ('USA'), ('Not a country at all');

# Удаляем всё лишнее.
delete from armies where country like 'Australia' or country like 'USA' or country like 'Not a country at all';

# Выбираем значения столбца country из таблицы armies с псевдонимом A, значения столбца name из таблицы
# military_units с псевдонимом M. Выбираются все значения столбца A.country(т.к. это left join), и значения
# столбца M.name где значения столбца M.army_id будут равны значениям столбца A.id. Значения столбца M.name
# заполняются в соответсвии с их значениями, там где значения столбца M.army_id будут равны значениям столбца A.id,
# а если для какого-то значения A.country нету соответсвующего значения M.name, то для
# этой строки M.name принимает значение null.
# Посредством этого запроса я смотрю, какие есть военные части в каждой стране
select A.country, M.name from armies as A
left join military_units as M on A.id = M.army_id
order by name;

# Очень надоело это всё расписывать. Выводим country из таблицы armies и количество военных частей в каждой стране.
# Функция count() считает количество военных частей для каждой страны за счёт групировки по столбцу A.country с помощью
# оператора group by. Так же мы указываем условие для групировки с пощью оператора having. Будут выводится только те страны,
# в которых количество военных частей будет меньше 4. Сортировка в порядке возрастания по столбцу count(M.name)
# c псевдонимом units_count.
select A.country, count(M.name) as units_count from armies as A
left join military_units as M on A.id = M.army_id
group by A.country having units_count < 4
order by units_count;

# Слишком надоело расписыпать. Выводим сколько всего патронов\пуль каждого типа в каждой из стран.
# Cортируем в алфавитном порядке по стране.
select A.country, AM.id, AM.type, sum(MAM.amount) as weapon_type_amount from ammo as AM
inner join military_unit_ammo as MAM on AM.id = MAM.ammo_id
inner join military_units as M on MAM.military_unit_id = M.id
right join armies as A on M.army_id = A.id
group by A.country, AM.type
order by A.country;

# Выводим сколько всего патронов\пуль каждого типа во всех странах.
# Сортируем по количеству пуль в порядке убывания.
select AM.type, sum(MAM.amount) as weapon_type_amount from ammo as AM
inner join military_unit_ammo as MAM on AM.id = MAM.ammo_id
inner join military_units as M on MAM.military_unit_id = M.id
right join armies as A on M.army_id = A.id
group by AM.type
order by weapon_type_amount desc;

# Тоже самое, что и запрос выше, только в блоке group by мы используем
# условие, что выведутся только типы типы патронов и их количество, количество которых
# во всех странах < 100000. Условие указывается после ключевого слова having.
select AM.type, sum(MAM.amount) as weapon_type_amount from ammo as AM
inner join military_unit_ammo as MAM on AM.id = MAM.ammo_id
inner join military_units as M on MAM.military_unit_id = M.id
right join armies as A on M.army_id = A.id
group by AM.type having weapon_type_amount < 100000
order by weapon_type_amount desc;

# Выводит все перестоновки по первичным ключам из двух таблиц.
# По сути каждому armies.id ставится все military_units.id
# Вообще не понимаю зачем это делать.
select * from armies cross join military_units;

# Выодим объеденение 4-ёх select запросов в одну результирующую таблицу.
# делается это с помощью ключевого слова uninon. Количество столбцов в каждом из select
# запросов должно совпадать, так же должны совпадать их типы. Иначе будет ошибка.
# Этот запрос выведет все типы оружия и амуниции, которые могут поступить на вооружение. 
select 'weapon' as war_power, type from weapons
union 
select 'ammo', type from ammo
union
select 'tank', type from tanks
union
select 'plane', type from planes;

# В следующих двух запросах разница между union и union all.
# Эти два запроса созданы только из интереса и для наглядности.
# union не выводит повторяющиеся строки.
select country from armies
union
select country from armies;

# union all выводит повторяющиеся строки.
select country from armies
union all
select country from armies;