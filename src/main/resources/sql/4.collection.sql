INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('Все', 'Все', 'filter', 'all.jpg');
INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('По священным писаниям', 'По священным писаниям', 'collection', 'shastra.jpg');

INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('Бхагавад-гита', 'Бхагавад-гита', 'collection', 'bg.jpg');
INSERT INTO collection (short_name, full_name, source, img_url, scripture_id, chapter)  VALUES ('Глава 1','Бхагавад-гита, Глава 1', 'filter', 'bg1.jpg', 1, 1);
INSERT INTO collection (short_name, full_name, source, img_url, scripture_id, chapter)  VALUES ('Глава 2','Бхагавад-гита, Глава 2', 'filter', 'bg2.jpg', 1, 2);
INSERT INTO collection (short_name, full_name, source, img_url, scripture_id, chapter)  VALUES ('Глава 3','Бхагавад-гита, Глава 3', 'filter', 'bg3.jpg', 1, 3);
INSERT INTO collection (short_name, full_name, source, img_url, scripture_id, chapter)  VALUES ('Глава 4','Бхагавад-гита, Глава 4', 'filter', 'bg4.jpg', 1, 4);
INSERT INTO collection (short_name, full_name, source, img_url, scripture_id, chapter)  VALUES ('Глава 5','Бхагавад-гита, Глава 5', 'filter', 'bg5.jpg', 1, 5);
INSERT INTO collection (short_name, full_name, source, img_url, scripture_id, chapter)  VALUES ('Глава 6','Бхагавад-гита, Глава 6', 'filter', 'bg6.jpg', 1, 6);
INSERT INTO collection (short_name, full_name, source, img_url, scripture_id, chapter)  VALUES ('Глава 7','Бхагавад-гита, Глава 7', 'filter', 'bg7.jpg', 1, 7);
INSERT INTO collection (short_name, full_name, source, img_url, scripture_id, chapter)  VALUES ('Глава 8','Бхагавад-гита, Глава 8', 'filter', 'bg8.jpg', 1, 8);
INSERT INTO collection (short_name, full_name, source, img_url, scripture_id, chapter)  VALUES ('Глава 9','Бхагавад-гита, Глава 9', 'filter', 'bg9.jpg', 1, 9);
INSERT INTO collection (short_name, full_name, source, img_url, scripture_id, chapter)  VALUES ('Глава 10','Бхагавад-гита, Глава 10', 'filter', 'bg10.jpg', 1, 10);
INSERT INTO collection (short_name, full_name, source, img_url, scripture_id, chapter)  VALUES ('Глава 11','Бхагавад-гита, Глава 11', 'filter', 'bg11.jpg', 1, 11);
INSERT INTO collection (short_name, full_name, source, img_url, scripture_id, chapter)  VALUES ('Глава 12','Бхагавад-гита, Глава 12', 'filter', 'bg12.jpg', 1, 12);
INSERT INTO collection (short_name, full_name, source, img_url, scripture_id, chapter)  VALUES ('Глава 13','Бхагавад-гита, Глава 13', 'filter', 'bg13.jpg', 1, 13);
INSERT INTO collection (short_name, full_name, source, img_url, scripture_id, chapter)  VALUES ('Глава 14','Бхагавад-гита, Глава 14', 'filter', 'bg14.jpg', 1, 14);
INSERT INTO collection (short_name, full_name, source, img_url, scripture_id, chapter)  VALUES ('Глава 15','Бхагавад-гита, Глава 15', 'filter', 'bg15.jpg', 1, 15);
INSERT INTO collection (short_name, full_name, source, img_url, scripture_id, chapter)  VALUES ('Глава 16','Бхагавад-гита, Глава 16', 'filter', 'bg16.jpg', 1, 16);
INSERT INTO collection (short_name, full_name, source, img_url, scripture_id, chapter)  VALUES ('Глава 17','Бхагавад-гита, Глава 17', 'filter', 'bg17.jpg', 1, 17);
INSERT INTO collection (short_name, full_name, source, img_url, scripture_id, chapter)  VALUES ('Глава 18','Бхагавад-гита, Глава 18', 'filter', 'bg18.jpg', 1, 18);

INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='По священным писаниям'), (select id from collection where full_name='Бхагавад-гита'), 1);
INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='Бхагавад-гита'), (select id from collection where full_name='Бхагавад-гита, Глава 1'), 1);
INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='Бхагавад-гита'), (select id from collection where full_name='Бхагавад-гита, Глава 2'), 2);
INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='Бхагавад-гита'), (select id from collection where full_name='Бхагавад-гита, Глава 3'), 3);
INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='Бхагавад-гита'), (select id from collection where full_name='Бхагавад-гита, Глава 4'), 4);
INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='Бхагавад-гита'), (select id from collection where full_name='Бхагавад-гита, Глава 5'), 5);
INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='Бхагавад-гита'), (select id from collection where full_name='Бхагавад-гита, Глава 6'), 6);
INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='Бхагавад-гита'), (select id from collection where full_name='Бхагавад-гита, Глава 7'), 7);
INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='Бхагавад-гита'), (select id from collection where full_name='Бхагавад-гита, Глава 8'), 8);
INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='Бхагавад-гита'), (select id from collection where full_name='Бхагавад-гита, Глава 9'), 9);
INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='Бхагавад-гита'), (select id from collection where full_name='Бхагавад-гита, Глава 10'), 10);
INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='Бхагавад-гита'), (select id from collection where full_name='Бхагавад-гита, Глава 11'), 11);
INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='Бхагавад-гита'), (select id from collection where full_name='Бхагавад-гита, Глава 12'), 12);
INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='Бхагавад-гита'), (select id from collection where full_name='Бхагавад-гита, Глава 13'), 13);
INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='Бхагавад-гита'), (select id from collection where full_name='Бхагавад-гита, Глава 14'), 14);
INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='Бхагавад-гита'), (select id from collection where full_name='Бхагавад-гита, Глава 15'), 15);
INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='Бхагавад-гита'), (select id from collection where full_name='Бхагавад-гита, Глава 16'), 16);
INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='Бхагавад-гита'), (select id from collection where full_name='Бхагавад-гита, Глава 17'), 17);
INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='Бхагавад-гита'), (select id from collection where full_name='Бхагавад-гита, Глава 18'), 18);


-- INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('Шримад-Бхагаватам', 'Шримад-Бхагаватам', 'collection', 'sb.jpg');
-- INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('Песнь 1', 'Шримад-Бхагаватам, Песнь 1', 'collection', 'sb1.jpg');
-- INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('Песнь 2', 'Шримад-Бхагаватам, Песнь 2', 'collection', 'sb2.jpg');
-- INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('Песнь 3', 'Шримад-Бхагаватам, Песнь 3', 'collection', 'sb3.jpg');
-- INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('Песнь 4', 'Шримад-Бхагаватам, Песнь 4', 'collection', 'sb4.jpg');
-- INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('Песнь 5', 'Шримад-Бхагаватам, Песнь 5', 'collection', 'sb5.jpg');
-- INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('Песнь 6', 'Шримад-Бхагаватам, Песнь 6', 'collection', 'sb6.jpg');
-- INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('Песнь 7', 'Шримад-Бхагаватам, Песнь 7', 'collection', 'sb7.jpg');
-- INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('Песнь 8', 'Шримад-Бхагаватам, Песнь 8', 'collection', 'sb8.jpg');
-- INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('Песнь 9', 'Шримад-Бхагаватам, Песнь 9', 'collection', 'sb9.jpg');
-- INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('Песнь 10', 'Шримад-Бхагаватам, Песнь 10', 'collection', 'sb10.jpg');
-- INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('Песнь 11', 'Шримад-Бхагаватам, Песнь 11', 'collection', 'sb11.jpg');
-- INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('Песнь 12', 'Шримад-Бхагаватам, Песнь 12', 'collection', 'sb12.jpg');
--
-- INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('Глава 1: Вопросы мудрецов', 'Шримад-Бхагаватам, Песнь 1, Глава 1 - Вопросы мудрецов', 'collection', 'sb1_1.jpg');
-- INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('Глава 2: Божественность и божественное служение', 'Шримад-Бхагаватам, Песнь 1, Глава 2: Божественность и божественное служение', 'collection', 'sb1_2.jpg');
-- INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('Глава 3: Кришна - источник всех воплощений', 'Шримад-Бхагаватам, Песнь 1, Глава 3: Кришна - источник всех воплощений', 'collection', 'sb1_3.jpg');
-- INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('Глава 4: Явление Шри Нарады', 'Шримад-Бхагаватам, Песнь 1, Глава 4: Явление Шри Нарады', 'collection', 'sb1_4.jpg');
--
--
INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('По годам', 'По годам', 'collection', 'year.jpg');
INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('2019', '2019', 'collection', '2019.jpg');
INSERT INTO collection (short_name, full_name, source, img_url, date_from, date_to)  VALUES ('Все', '2019 - все лекции', 'filter', 'all.jpg', date_from, date_to);
INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('Семинары', '2019 - семинары', 'collection', 'seminar.jpg');
INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('Парикрамы', '2019 - парикрамы', 'collection', 'parikram.jpg');
INSERT INTO collection (short_name, full_name, source, img_url, date_from, date_to)  VALUES ('Обращения', '2019 - обращения', 'collection', 'treatment.jpg', date_from, date_to);
INSERT INTO collection (short_name, full_name, source, img_url, date_from, date_to)  VALUES ('Публичные лекции', '2019 - публичные лекции', 'collection', 'public.jpg', date_from, date_to);
INSERT INTO collection (short_name, full_name, source, img_url, date_from, date_to)  VALUES ('Встречи с учениками', '2019 - встречи с учениками', 'collection', 'disciple.jpg', date_from, date_to);
INSERT INTO collection (short_name, full_name, source, img_url, date_from, date_to)  VALUES ('Праздники', '2019 - праздники', 'collection', 'celebration.jpg', date_from, date_to);
INSERT INTO collection (short_name, full_name, source, img_url, date_from, date_to)  VALUES ('Инициация', '2019 - инициация', 'collection', 'initiation.jpg', date_from, date_to);

INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='По годам'), (select id from collection where full_name='2019'), 1);
INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='2019'), (select id from collection where full_name='2019 - все лекции'), 1);
INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='2019'), (select id from collection where full_name='2019 - семинары'), 2);
INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='2019'), (select id from collection where full_name='2019 - парикрамы'), 3);
INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='2019'), (select id from collection where full_name='2019 - обращения'), 4);
INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='2019'), (select id from collection where full_name='2019 - публичные лекции'), 5);
INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='2019'), (select id from collection where full_name='2019 - встречи с учениками'), 6);
INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='2019'), (select id from collection where full_name='2019 - инициация'), 7);

