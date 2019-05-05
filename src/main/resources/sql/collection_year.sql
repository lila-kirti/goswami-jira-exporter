INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('По годам', 'По годам', 'collection', 'year.jpg');

INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('2019', '2019 год', 'collection', '2019.jpg');
INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('2018', '2018 год', 'collection', '2018.jpg');
INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('2017', '2017 год', 'collection', '2017.jpg');
INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('2016', '2016 год', 'collection', '2016.jpg');
INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('2015', '2015 год', 'collection', '2015.jpg');
INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('2014', '2014 год', 'collection', '2014.jpg');
INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('2013', '2013 год', 'collection', '2013.jpg');
INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('2012', '2012 год', 'collection', '2012.jpg');
INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('2011', '2011 год', 'collection', '2011.jpg');
INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('2010', '2010 год', 'collection', '2010.jpg');
INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('2009', '2009 год', 'collection', '2009.jpg');
INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('2008', '2008 год', 'collection', '2008.jpg');
INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('2007', '2007 год', 'collection', '2007.jpg');
INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('2006', '2006 год', 'collection', '2006.jpg');
INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('2005', '2005 год', 'collection', '2005.jpg');
INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('2004', '2004 год', 'collection', '2004.jpg');
INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('2003', '2003 год', 'collection', '2003.jpg');
INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('2002', '2002 год', 'collection', '2002.jpg');
INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('2001', '2001 год', 'collection', '2001.jpg');
INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('2000', '2000 год', 'collection', '2000.jpg');
INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('1999', '1999 год', 'collection', '1999.jpg');
INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('1998', '1998 год', 'collection', '1998.jpg');
INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('1997', '1997 год', 'collection', '1997.jpg');
INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('1996', '1996 год', 'collection', '1996.jpg');
INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('1995', '1995 год', 'collection', '1995.jpg');
INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('1994', '1994 год', 'collection', '1994.jpg');
INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('1993', '1993 год', 'collection', '1993.jpg');
INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('1992', '1992 год', 'collection', '1992.jpg');
INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('1991', '1991 год', 'collection', '1991.jpg');



INSERT INTO collection (short_name, full_name, source, img_url, date_from, date_to)  VALUES ('Все', 'Все лекции 2019 года', 'filter', 'all.jpg', date_from, date_to);
INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('Семинары', 'Семинары 2019 года', 'collection', 'seminar.jpg');
INSERT INTO collection (short_name, full_name, source, img_url)  VALUES ('Парикрамы', 'Паломничества 2019 года', 'collection', 'parikram.jpg');
INSERT INTO collection (short_name, full_name, source, img_url, date_from, date_to)  VALUES ('Обращения, встречи с учениками', 'Обращения, встречи с учениками 2019 года', 'collection', 'treatment.jpg', date_from, date_to);
INSERT INTO collection (short_name, full_name, source, img_url, date_from, date_to)  VALUES ('Публичные лекции', 'Публичные лекции 2019 года', 'collection', 'public.jpg', date_from, date_to);
INSERT INTO collection (short_name, full_name, source, img_url, date_from, date_to)  VALUES ('Праздники', 'Лекции на праздниках 2019 года', 'collection', 'celebration.jpg', date_from, date_to);
INSERT INTO collection (short_name, full_name, source, img_url, date_from, date_to)  VALUES ('Инициация', 'Лекции на церемонии посвящения 2019 года', 'collection', 'initiation.jpg', date_from, date_to);

INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='По годам'), (select id from collection where full_name='2019 год'), 100);
INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='По годам'), (select id from collection where full_name='2018 год'), 100);



INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='2019'), (select id from collection where full_name='Все лекции 2019 года'), 2);
INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='2019'), (select id from collection where full_name='Семинары 2019 года'), 3);
INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='2019'), (select id from collection where full_name='Паломничества 2019 года'), 4);
INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='2019'), (select id from collection where full_name='Обращения, встречи с учениками 2019 года'), 5);
INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='2019'), (select id from collection where full_name='Публичные лекции 2019 года'), 6);
INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='2019'), (select id from collection where full_name='Лекции на праздниках 2019 года'), 7);
INSERT INTO collection_hierarchy (parent_id, children_id, ordern) VALUES ((select id from collection where full_name='2019'), (select id from collection where full_name='Лекции на церемонии посвящения 2019 года'), 8);

