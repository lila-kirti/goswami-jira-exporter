INSERT INTO donation
(title,description,img_url,amount,necessary_amount,end_date,currency)
VALUES (
  'На развитие Студии',
  'Разного рода текущие расходы, связанные с поддержанием и усовершенствованием материально-технической базы Студии Госвами.ру.',
  '/donation/site-development.png',
  null,
  null,
  '2030-03-01',
  'RUR'
);

INSERT INTO donation
(title,description,img_url,amount,necessary_amount,end_date,currency)
VALUES (
  'Лицензия для JIRA сервера',
  'Лицензия для JIRA сервера https://www.atlassian.com/software/jira/pricing?tab=self-hosted. Система используется для управления обработкой лекций.',
  '/donation/site-development.png',
  102,
  630,
  '2019-12-31',
  'RUR'
);

INSERT INTO vacancy (title, description, img_uri) VALUES ('Звукоинженер','Обработка записей лекций','/vacancy/engeneer.png');
INSERT INTO vacancy (title, description, img_uri) VALUES ('Специалисты по маркетингу','Специалисты по маркетингу SMM, е-мейл рассылки и PR, анонсы и рекламные кампании записей семинаров и дисков с лекциями, изданных в Студии','/vacancy/marketing.png');
INSERT INTO vacancy (title, description, img_uri) VALUES ('Видеомонтаж','Монтаж видеолекций Гурудева. Подготовка и размещение на официальном канале','/vacancy/video.png');