INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('Ретриты и фестивали', 'Ретриты и фестивали', 'collection', 'retreats.jpg');
INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('Ретриты учеников', 'Ретриты учеников', 'collection', 'disciple-retreats.jpg');
INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('Садху-санга', 'Садху-санга', 'collection', 'sadhu-sanga.jpg');
INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('Бхакти-сангама', 'Бхакти-сангама', 'collection', 'bhakti-sangama.jpg');
INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('Ретриты Святого имени', 'Ретриты Святого имени', 'collection', 'nama-retreats.jpg');
INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('Ретриты для наставников', 'Ретриты для наставников', 'collection', 'master-retreats.jpg');
INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('Прити-лакшанам', 'Прити-лакшанам', 'collection', 'master-retreats.jpg');

INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='Ретриты и фестивали'), (select id from collection where full_name='Ретриты учеников'), 1);
INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='Ретриты и фестивали'), (select id from collection where full_name='Садху-санга'), 2);
INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='Ретриты и фестивали'), (select id from collection where full_name='Бхакти-сангама'), 3);
INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='Ретриты и фестивали'), (select id from collection where full_name='Ретриты Святого имени'), 4);
INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='Ретриты и фестивали'), (select id from collection where full_name='Ретриты для наставников'), 5);
INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='Ретриты и фестивали'), (select id from collection where full_name='Прити-лакшанам'), 6);


INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('Семинары', 'Семинары', 'collection', 'seminars.jpg');
INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('Паломничества', 'Паломничества', 'collection', 'parikrams.jpg');
INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('Публичные лекции', 'Публичные лекции', 'collection', 'publics.jpg');
INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('Тематические', 'Тематические', 'collection', 'theme.jpg');
INSERT INTO collection (short_name, full_name, source, img_url, category_id, order_by, direction)  VALUES ('Киртаны', 'Киртаны', 'filter', 'kirtan.jpg', 6, 'date', 'DESC');
INSERT INTO collection (short_name, full_name, source, img_url, order_by, direction)  VALUES ('Школа Джапа-медитации', 'Школа Джапа-медитации', 'filter', 'shdm.jpg', 'date', 'DESC');
INSERT INTO collection_filter_tag (collection_id, tag_id)  VALUES ((select id from collection where full_name='Школа Джапа-медитации'), (select id from tag where name='школа джапа-медитации'));
INSERT INTO collection (short_name, full_name, source, img_url, "language", order_by, direction)  VALUES ('Школа Джапа-медитации', 'Школа Джапа-медитации', 'filter', 'shdm.jpg', 'ENG', 'date', 'DESC');