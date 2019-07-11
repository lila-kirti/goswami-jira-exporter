INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('Все', 'Все', 'filter', 'collection/all.jpg');
INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('Ретриты и фестивали', 'Ретриты и фестивали', 'collection', 'collection/retreats.jpg');
INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('Ретриты учеников', 'Ретриты учеников', 'collection', 'collection/disciple-retreats.jpg');
INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('Садху-санга', 'Садху-санга', 'collection', 'collection/sadhu-sanga.jpg');
INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('Бхакти-сангама', 'Бхакти-сангама', 'collection', 'collection/bhakti-sangama.jpg');
INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('Ретриты Святого имени', 'Ретриты Святого имени', 'collection', 'collection/nama-retreats.jpg');
INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('Ретриты для наставников', 'Ретриты для наставников', 'collection', 'collection/master-retreats.jpg');
INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('Прити-лакшанам', 'Прити-лакшанам', 'collection', 'collection/master-retreats.jpg');

INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='Ретриты и фестивали'), (select id from collection where full_name='Ретриты учеников'), 1);
INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='Ретриты и фестивали'), (select id from collection where full_name='Садху-санга'), 2);
INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='Ретриты и фестивали'), (select id from collection where full_name='Бхакти-сангама'), 3);
INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='Ретриты и фестивали'), (select id from collection where full_name='Ретриты Святого имени'), 4);
INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='Ретриты и фестивали'), (select id from collection where full_name='Ретриты для наставников'), 5);
INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='Ретриты и фестивали'), (select id from collection where full_name='Прити-лакшанам'), 6);


INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('Семинары', 'Семинары', 'collection', 'collection/seminars.jpg');
INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('Паломничества', 'Паломничества', 'collection', 'collection/parikrams.jpg');
INSERT INTO collection (short_name, full_name, source, img_url, category_id, order_by, direction)  VALUES ('Публичные лекции', 'Публичные лекции', 'filter', 'collection/publics.jpg', 4, 'date', 'DESC');
INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('Тематические', 'Тематические', 'collection', 'collection/theme.jpg');
INSERT INTO collection (short_name, full_name, source, img_url, category_id, order_by, direction)  VALUES ('Киртаны', 'Киртаны', 'filter', 'collection/kirtan.jpg', 6, 'date', 'DESC');
INSERT INTO collection (short_name, full_name, source, img_url, order_by, direction)  VALUES ('Школа джапа-медитации', 'Школа джапа-медитации', 'filter', 'collection/shdm.jpg', 'date', 'DESC');
INSERT INTO collection_filter_tag (collection_id, tag_id)  VALUES ((select id from collection where full_name='Школа джапа-медитации'), (select id from tag where name='школа джапа-медитации'));
INSERT INTO collection (short_name, full_name, source, img_url, "language", order_by, direction)  VALUES ('English', 'English', 'filter', 'collection/english.jpg', 'ENG', 'date', 'DESC');