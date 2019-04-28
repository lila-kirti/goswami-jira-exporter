INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('Бхагавад-гита', 'Бхагавад-гита', 'collection', 'bg.png');
INSERT INTO collection (short_name, full_name, source, img_url, scripture_id, chapter)  VALUES ('Глава 1','Бхагавад-гита, Глава 1', 'filter', 'bg1.png', 1, 1);
INSERT INTO collection (short_name, full_name, source, img_url, scripture_id, chapter)  VALUES ('Глава 2','Бхагавад-гита, Глава 2', 'filter', 'bg2.png', 1, 2);
INSERT INTO collection (short_name, full_name, source, img_url, scripture_id, chapter)  VALUES ('Глава 3','Бхагавад-гита, Глава 3', 'filter', 'bg3.png', 1, 3);
INSERT INTO collection (short_name, full_name, source, img_url, scripture_id, chapter)  VALUES ('Глава 4','Бхагавад-гита, Глава 4', 'filter', 'bg4.png', 1, 4);
INSERT INTO collection (short_name, full_name, source, img_url, scripture_id, chapter)  VALUES ('Глава 5','Бхагавад-гита, Глава 5', 'filter', 'bg5.png', 1, 5);
INSERT INTO collection (short_name, full_name, source, img_url, scripture_id, chapter)  VALUES ('Глава 6','Бхагавад-гита, Глава 6', 'filter', 'bg6.png', 1, 6);
INSERT INTO collection (short_name, full_name, source, img_url, scripture_id, chapter)  VALUES ('Глава 7','Бхагавад-гита, Глава 7', 'filter', 'bg7.png', 1, 7);
INSERT INTO collection (short_name, full_name, source, img_url, scripture_id, chapter)  VALUES ('Глава 8','Бхагавад-гита, Глава 8', 'filter', 'bg8.png', 1, 8);
INSERT INTO collection (short_name, full_name, source, img_url, scripture_id, chapter)  VALUES ('Глава 9','Бхагавад-гита, Глава 9', 'filter', 'bg9.png', 1, 9);
INSERT INTO collection (short_name, full_name, source, img_url, scripture_id, chapter)  VALUES ('Глава 10','Бхагавад-гита, Глава 10', 'filter', 'bg10.png', 1, 10);
INSERT INTO collection (short_name, full_name, source, img_url, scripture_id, chapter)  VALUES ('Глава 11','Бхагавад-гита, Глава 11', 'filter', 'bg11.png', 1, 11);
INSERT INTO collection (short_name, full_name, source, img_url, scripture_id, chapter)  VALUES ('Глава 12','Бхагавад-гита, Глава 12', 'filter', 'bg12.png', 1, 12);
INSERT INTO collection (short_name, full_name, source, img_url, scripture_id, chapter)  VALUES ('Глава 13','Бхагавад-гита, Глава 13', 'filter', 'bg13.png', 1, 13);
INSERT INTO collection (short_name, full_name, source, img_url, scripture_id, chapter)  VALUES ('Глава 14','Бхагавад-гита, Глава 14', 'filter', 'bg14.png', 1, 14);
INSERT INTO collection (short_name, full_name, source, img_url, scripture_id, chapter)  VALUES ('Глава 15','Бхагавад-гита, Глава 15', 'filter', 'bg15.png', 1, 15);
INSERT INTO collection (short_name, full_name, source, img_url, scripture_id, chapter)  VALUES ('Глава 16','Бхагавад-гита, Глава 16', 'filter', 'bg16.png', 1, 16);
INSERT INTO collection (short_name, full_name, source, img_url, scripture_id, chapter)  VALUES ('Глава 17','Бхагавад-гита, Глава 17', 'filter', 'bg17.png', 1, 17);
INSERT INTO collection (short_name, full_name, source, img_url, scripture_id, chapter)  VALUES ('Глава 18','Бхагавад-гита, Глава 18', 'filter', 'bg18.png', 1, 18);

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







