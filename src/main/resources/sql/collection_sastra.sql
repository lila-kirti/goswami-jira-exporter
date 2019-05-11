INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('По священным писаниям', 'По священным писаниям', 'collection', 'collection/shastra.jpg');

INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('Бхагавад-гита', 'Бхагавад-гита', 'collection', 'collection/bg.jpg');
INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('Семинары','Семинары по Бхагавад-гите', 'collection', 'collection/bg-seminar.jpg');
INSERT INTO collection (short_name, full_name, source, img_url, scripture_id, chapter, order_by, direction)  VALUES ('Глава 1','Бхагавад-гита, Глава 1', 'filter', 'collection/bg1.jpg', 1, 1, 'verse', 'ASC');
INSERT INTO collection (short_name, full_name, source, img_url, scripture_id, chapter, order_by, direction)  VALUES ('Глава 2','Бхагавад-гита, Глава 2', 'filter', 'collection/bg2.jpg', 1, 2, 'verse', 'ASC');
INSERT INTO collection (short_name, full_name, source, img_url, scripture_id, chapter, order_by, direction)  VALUES ('Глава 3','Бхагавад-гита, Глава 3', 'filter', 'collection/bg3.jpg', 1, 3, 'verse', 'ASC');
INSERT INTO collection (short_name, full_name, source, img_url, scripture_id, chapter, order_by, direction)  VALUES ('Глава 4','Бхагавад-гита, Глава 4', 'filter', 'collection/bg4.jpg', 1, 4, 'verse', 'ASC');
INSERT INTO collection (short_name, full_name, source, img_url, scripture_id, chapter, order_by, direction)  VALUES ('Глава 5','Бхагавад-гита, Глава 5', 'filter', 'collection/bg5.jpg', 1, 5, 'verse', 'ASC');
INSERT INTO collection (short_name, full_name, source, img_url, scripture_id, chapter, order_by, direction)  VALUES ('Глава 6','Бхагавад-гита, Глава 6', 'filter', 'collection/bg6.jpg', 1, 6, 'verse', 'ASC');
INSERT INTO collection (short_name, full_name, source, img_url, scripture_id, chapter, order_by, direction)  VALUES ('Глава 7','Бхагавад-гита, Глава 7', 'filter', 'collection/bg7.jpg', 1, 7, 'verse', 'ASC');
INSERT INTO collection (short_name, full_name, source, img_url, scripture_id, chapter, order_by, direction)  VALUES ('Глава 8','Бхагавад-гита, Глава 8', 'filter', 'collection/bg8.jpg', 1, 8, 'verse', 'ASC');
INSERT INTO collection (short_name, full_name, source, img_url, scripture_id, chapter, order_by, direction)  VALUES ('Глава 9','Бхагавад-гита, Глава 9', 'filter', 'collection/bg9.jpg', 1, 9, 'verse', 'ASC');
INSERT INTO collection (short_name, full_name, source, img_url, scripture_id, chapter, order_by, direction)  VALUES ('Глава 10','Бхагавад-гита, Глава 10', 'filter', 'collection/bg10.jpg', 1, 10, 'verse', 'ASC');
INSERT INTO collection (short_name, full_name, source, img_url, scripture_id, chapter, order_by, direction)  VALUES ('Глава 11','Бхагавад-гита, Глава 11', 'filter', 'collection/bg11.jpg', 1, 11, 'verse', 'ASC');
INSERT INTO collection (short_name, full_name, source, img_url, scripture_id, chapter, order_by, direction)  VALUES ('Глава 12','Бхагавад-гита, Глава 12', 'filter', 'collection/bg12.jpg', 1, 12, 'verse', 'ASC');
INSERT INTO collection (short_name, full_name, source, img_url, scripture_id, chapter, order_by, direction)  VALUES ('Глава 13','Бхагавад-гита, Глава 13', 'filter', 'collection/bg13.jpg', 1, 13, 'verse', 'ASC');
INSERT INTO collection (short_name, full_name, source, img_url, scripture_id, chapter, order_by, direction)  VALUES ('Глава 14','Бхагавад-гита, Глава 14', 'filter', 'collection/bg14.jpg', 1, 14, 'verse', 'ASC');
INSERT INTO collection (short_name, full_name, source, img_url, scripture_id, chapter, order_by, direction)  VALUES ('Глава 15','Бхагавад-гита, Глава 15', 'filter', 'collection/bg15.jpg', 1, 15, 'verse', 'ASC');
INSERT INTO collection (short_name, full_name, source, img_url, scripture_id, chapter, order_by, direction)  VALUES ('Глава 16','Бхагавад-гита, Глава 16', 'filter', 'collection/bg16.jpg', 1, 16, 'verse', 'ASC');
INSERT INTO collection (short_name, full_name, source, img_url, scripture_id, chapter, order_by, direction)  VALUES ('Глава 17','Бхагавад-гита, Глава 17', 'filter', 'collection/bg17.jpg', 1, 17, 'verse', 'ASC');
INSERT INTO collection (short_name, full_name, source, img_url, scripture_id, chapter, order_by, direction)  VALUES ('Глава 18','Бхагавад-гита, Глава 18', 'filter', 'collection/bg18.jpg', 1, 18, 'verse', 'ASC');

INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='Бхагавад-гита'), (select id from collection where full_name='Семинары по Бхагавад-гите'), 1);
INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='Бхагавад-гита'), (select id from collection where full_name='Бхагавад-гита, Глава 1'), 2);
INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='Бхагавад-гита'), (select id from collection where full_name='Бхагавад-гита, Глава 2'), 3);
INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='Бхагавад-гита'), (select id from collection where full_name='Бхагавад-гита, Глава 3'), 4);
INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='Бхагавад-гита'), (select id from collection where full_name='Бхагавад-гита, Глава 4'), 5);
INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='Бхагавад-гита'), (select id from collection where full_name='Бхагавад-гита, Глава 5'), 6);
INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='Бхагавад-гита'), (select id from collection where full_name='Бхагавад-гита, Глава 6'), 7);
INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='Бхагавад-гита'), (select id from collection where full_name='Бхагавад-гита, Глава 7'), 8);
INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='Бхагавад-гита'), (select id from collection where full_name='Бхагавад-гита, Глава 8'), 9);
INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='Бхагавад-гита'), (select id from collection where full_name='Бхагавад-гита, Глава 9'), 10);
INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='Бхагавад-гита'), (select id from collection where full_name='Бхагавад-гита, Глава 10'), 11);
INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='Бхагавад-гита'), (select id from collection where full_name='Бхагавад-гита, Глава 11'), 12);
INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='Бхагавад-гита'), (select id from collection where full_name='Бхагавад-гита, Глава 12'), 13);
INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='Бхагавад-гита'), (select id from collection where full_name='Бхагавад-гита, Глава 13'), 14);
INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='Бхагавад-гита'), (select id from collection where full_name='Бхагавад-гита, Глава 14'), 15);
INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='Бхагавад-гита'), (select id from collection where full_name='Бхагавад-гита, Глава 15'), 16);
INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='Бхагавад-гита'), (select id from collection where full_name='Бхагавад-гита, Глава 16'), 17);
INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='Бхагавад-гита'), (select id from collection where full_name='Бхагавад-гита, Глава 17'), 18);
INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='Бхагавад-гита'), (select id from collection where full_name='Бхагавад-гита, Глава 18'), 19);

INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('Шримад-Бхагаватам', 'Шримад-Бхагаватам', 'collection', 'collection/sb.jpg');

INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('Шри Чайтанья-чаритамрита', 'Шри Чайтанья-чаритамрита', 'collection', 'collection/cc.jpg');
INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('Семинары','Семинары по Шри Чайтанье-чаритамрите', 'collection', 'collection/cc-seminar.jpg');
INSERT INTO collection (short_name, full_name, source, img_url, scripture_id, canto, order_by, direction)  VALUES ('Ади лила','Шри Чайтанья-чаритамрита, Ади лила', 'filter', 'collection/сс1.jpg', 3, 1, 'verse', 'ASC');
INSERT INTO collection (short_name, full_name, source, img_url, scripture_id, canto, order_by, direction)  VALUES ('Мадхья лила','Шри Чайтанья-чаритамрита, Мадхья лила', 'filter', 'collection/сс2.jpg', 3, 2, 'verse', 'ASC');
INSERT INTO collection (short_name, full_name, source, img_url, scripture_id, canto, order_by, direction)  VALUES ('Антья лила','Шри Чайтанья-чаритамрита, Антья лила', 'filter', 'collection/сс3.jpg', 3, 3, 'verse', 'ASC');

INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='Шри Чайтанья-чаритамрита'), (select id from collection where full_name='Семинары по Шри Чайтанье-чаритамрите'), 1);
INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='Шри Чайтанья-чаритамрита'), (select id from collection where full_name='Шри Чайтанья-чаритамрита, Ади лила'), 2);
INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='Шри Чайтанья-чаритамрита'), (select id from collection where full_name='Шри Чайтанья-чаритамрита, Мадхья лила'), 3);
INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='Шри Чайтанья-чаритамрита'), (select id from collection where full_name='Шри Чайтанья-чаритамрита, Антья лила'), 4);

INSERT INTO collection (short_name, full_name, source, img_url, scripture_id, order_by, direction)  VALUES ('Нектар преданности', 'Нектар преданности', 'filter', 'collection/nd.jpg', 4, 'verse', 'ASC');
INSERT INTO collection (short_name, full_name, source, img_url, scripture_id, order_by, direction)  VALUES ('Нектар наставлений', 'Нектар наставлений', 'filter', 'collection/ni.jpg', 5, 'verse', 'ASC');
INSERT INTO collection (short_name, full_name, source, img_url, scripture_id, order_by, direction)  VALUES ('Шри ишопанишад', 'Шри ишопанишад', 'filter', 'collection/si.jpg', 6, 'verse', 'ASC');
INSERT INTO collection (short_name, full_name, source, img_url, scripture_id, order_by, direction)  VALUES ('Мадхурья-кадамбини', 'Мадхурья-кадамбини', 'filter', 'collection/si.jpg', 7, 'date', 'DESC');
INSERT INTO collection (short_name, full_name, source, img_url, scripture_id, order_by, direction)  VALUES ('Рамаяна', 'Рамаяна', 'filter', 'collection/ram.jpg', 8, 'date', 'DESC');

INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='По священным писаниям'), (select id from collection where full_name='Бхагавад-гита'), 1);
INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='По священным писаниям'), (select id from collection where full_name='Шримад-Бхагаватам'), 2);
INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='По священным писаниям'), (select id from collection where full_name='Шри Чайтанья-чаритамрита'), 3);
INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='По священным писаниям'), (select id from collection where full_name='Нектар преданности'), 4);
INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='По священным писаниям'), (select id from collection where full_name='Нектар наставлений'), 5);
INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='По священным писаниям'), (select id from collection where full_name='Шри ишопанишад'), 6);
INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='По священным писаниям'), (select id from collection where full_name='Мадхурья-кадамбини'), 7);
INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='По священным писаниям'), (select id from collection where full_name='Рамаяна'), 8);



