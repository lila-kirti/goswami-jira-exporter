INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('Святое имя', 'Святое имя', 'collection', 'collection/shdm-retreats.jpg');
INSERT INTO collection (short_name, full_name, source, img_url, order_by, direction)  VALUES ('Лекции', 'Святое имя. Лекции', 'filter', 'collection/all.jpg', 'date', 'DESC');
INSERT INTO collection_filter_tag (collection_id, tag_id)  VALUES ((select id from collection where full_name='Святое имя. Лекции'), (select id from tag where name='о_святом_имени'));
INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('Семинары', 'Святое имя. Семинары', 'collection', 'collection/shdm-retreats.jpg');

INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='Святое имя'), (select id from collection where full_name='Святое имя. Лекции'), 1);
INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='Святое имя'), (select id from collection where full_name='Святое имя. Семинары'), 2);

