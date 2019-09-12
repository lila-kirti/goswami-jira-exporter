insert into scripture (id, name,description) values(1, 'Бхагавад-гита',
'Укажите главу и номер стиха для поиска. Примеры:<br/>
<b>1</b> (1 глава)<br/>
<b>1-3</b> (с 1 по 3 главы)<br/>
<b>1.4</b> (1 глава, 4 стих)<br/>
<b>1.4-7</b> (1 глава, с 4 по 7 стихи)<br/>');
insert into scripture (id, name,description) values(2, 'Шримад-бхагаватам',
'Укажите песнь, главу и номер стиха для поиска. Примеры:<br/>
1 (1 песнь)<br/>
1.2 (1 песнь, 2 глава)<br/>
1.2-4 (1 песнь, с 2 по 4 главы)<br/>
1.2.1-10 (1 песнь, 2 глава, с 1 по 10 стихи)<br/>');
insert into scripture (id, name,description) values(3, 'Чайтанья-чаритамрита',
'Укажите том, главу и номер стиха для поиска. Примеры:<br/>
Ади (Ади-лила, варианты: Мадхья, Антья)<br/>
Ади 2 (Ади-лила, 2 глава)<br/>
Ади 2-3 (Ади-лила, с 2 по 3 главы)<br/>
Ади 2.4 (Ади-лила, 2 глава, 4 стих)<br/>
Ади 2.115-118 (Ади-лила, 2 глава, с 115 по 118 стихи)<br/>');
insert into scripture (id, name,description) values(4, 'Нектар преданности','Укажите номер главы для поиска');
insert into scripture (id, name,description) values(5, 'Нектар наставлений','Укажите номер стиха для поиска');
insert into scripture (id, name,description) values(6, 'Шри Ишопанишад','Укажите номер стиха для поиска');
insert into scripture (id, name) values(7, 'Мадхурья-кадмбини');
insert into scripture (id, name) values(8, 'Рамаяна');

insert into category (id, name) values(1, 'Паломничество');
insert into category (id, name) values(2, 'Праздник');
insert into category (id, name) values(3, 'Обращение');
insert into category (id, name) values(4, 'Публичная лекция');
insert into category (id, name) values(5, 'Встреча с учениками');
insert into category (id, name) values(6, 'Киртан');
insert into category (id, name) values(7, 'Инициация');
insert into category (id, name) values(8, 'Обсуждение');

insert into role (id, name) values(1, 'Пользователь');
insert into role (id, name) values(2, 'Администратор');
insert into role (id, name) values(3, 'Редактор');
insert into role (id, name) values(4, 'Представитель');

