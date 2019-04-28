
CREATE TABLE IF NOT EXISTS location (
    id               serial PRIMARY KEY,
    "name"           character varying(255) not null
);
COMMENT ON TABLE location IS 'Справочник городов где читались лекции';

CREATE TABLE IF NOT EXISTS author (
    id               serial PRIMARY KEY,
    "name"           character varying(255) not null
);
COMMENT ON TABLE author IS 'Справочник авторов лекций';

CREATE TABLE IF NOT EXISTS tag (
    id               serial PRIMARY KEY,
    "name"           character varying(128) not null
);
COMMENT ON TABLE tag IS 'Справочник ключевых слов';


CREATE TABLE IF NOT EXISTS category
(
    id               serial PRIMARY KEY,
    "name"           character varying(512) not null
);
COMMENT ON TABLE category IS 'Справочник категорий лекции';


CREATE TABLE IF NOT EXISTS scripture
(
    id               serial PRIMARY KEY,
    "name"           character varying(512) not null
);
COMMENT ON TABLE category IS 'Священные писания';

CREATE TYPE public.media_type AS ENUM ( 'audio', 'book', 'article');
CREATE TYPE public.collection_source AS ENUM ( 'lecture', 'collection', 'filter');
CREATE TYPE public.lang AS ENUM ( 'RUS', 'ENG');

CREATE TABLE IF NOT EXISTS media (
    id               serial PRIMARY KEY,
    type             media_type,
    title            character varying(256) not null,
    teaser           text, -- аннотация
    jira_ref         character varying(128), -- ключ JIRA, если лекция/подборка была импортирована
    "text"           text, -- текст лекции
    occurrence_date  date not null, -- дата прочтения
    issue_date       timestamp, -- дата публикации
    category_id      integer references category(id), 
    img_url          text not null,   -- ссылка на картинку
    file_url         text not null,   -- ссылка на файл лекции
    alias_url        text,   -- красивая ссылка
    location_id      integer references location(id),
    visible          boolean DEFAULT true,
    duration         interval, -- длительность записи
    size             integer, -- размер записи в МБ
    language         lang DEFAULT 'RUS'
);
COMMENT ON TABLE media IS 'Лекция, книга, статья';

CREATE TABLE IF NOT EXISTS scripture_media
(
    id               serial PRIMARY KEY,
    media_id         integer not null references media(id) on delete cascade,
    scripture_id     integer not null references scripture(id),
    canto            integer, -- песнь
    chapter          integer, -- глава
    verse            integer -- стих   
);
COMMENT ON TABLE scripture_media IS 'Песнь/глава/стих священных писаний, объясненные в лекции';

COMMENT ON TABLE category IS 'Священные писания';

CREATE TABLE IF NOT EXISTS media_tag (
    media_id         integer not null references media(id) on delete cascade,
    tag_id           integer not null references tag(id) on delete cascade
);
COMMENT ON TABLE media_tag IS 'Связка лекции и ключевых слов';

CREATE TABLE IF NOT EXISTS media_author
(
    author_id         integer not null references author(id) on delete cascade,
    media_id          integer not null references media(id) on delete cascade
);
COMMENT ON TABLE media_author IS 'В одном файле (media) может быть несколько выступающих';

-- data_type  ('picture', 'video');
CREATE TABLE media_data
(
    id                serial PRIMARY KEY,
    media_id          integer references media(id) on delete cascade,
    data_type         character varying(128),
    value             text
);
COMMENT ON TABLE media_data IS 'Дополнительные атрибуты объекта';


CREATE TABLE IF NOT EXISTS collection
(
    id               serial PRIMARY KEY,
    short_name             character varying(255) not null,
    full_name             character varying(255),
    annotation       text, 
    source           collection_source,
    img_url          text not null,
    category_id      integer,
    scripture_id     integer,
    canto            integer,
    chapter          integer,
    verse            integer,
    date_from        date,
    date_to        date,
    "language"       lang
	
);
COMMENT ON TABLE collection IS 'Подборки';

CREATE TABLE IF NOT EXISTS collection_hierarchy
(
    parent_id        integer not null references collection(id) on delete cascade,
    children_id      integer not null references collection(id) on delete cascade,
    ordern           integer    
);
COMMENT ON TABLE collection IS 'Иерархия подборок';

CREATE TABLE IF NOT EXISTS collection_media
(
    collection_id    integer not null references collection(id) on delete cascade,
    media_id         integer not null references media(id) on delete cascade,
    ordern           integer    
);
COMMENT ON TABLE collection IS 'Связь лекции и подборки';


CREATE TABLE IF NOT EXISTS "user"
(
    id               serial PRIMARY KEY,
    email            varchar(255) NOT NULL,
    username         varchar(255) NOT NULL,
    password         varchar(256) NOT NULL,
    is_active        BOOLEAN DEFAULT TRUE, 
    CONSTRAINT       user_email_key UNIQUE (email)
);
COMMENT ON TABLE "user" IS 'Основные данные пользователей';

CREATE TABLE "role" (
  id serial PRIMARY KEY, 
  name varchar(100) NOT NULL, 
  code varchar(100), 
  description varchar(250)
);
COMMENT ON TABLE "role" IS 'Роли';

CREATE TABLE  IF NOT EXISTS permission (
  id serial PRIMARY KEY, 
  name varchar(100) NOT NULL, 
  code varchar(100) NOT NULL, 
  parent_id integer, 
  CONSTRAINT permission_parent_fk FOREIGN KEY (parent_id) REFERENCES permission(id)
);
COMMENT ON TABLE permission IS 'Права доступа';

CREATE TABLE IF NOT EXISTS user_role
(
    user_id          integer not null references "user"(id) on delete cascade,
    role_id          integer not null references role(id),
    CONSTRAINT user_role_key UNIQUE (user_id, role_id)
);
COMMENT ON TABLE role IS 'Роли пользователей';

CREATE TABLE role_permission (
  role_id integer not null references role(id), 
  permission_id integer not null references role(id),
  CONSTRAINT role_permission_key UNIQUE (role_id, permission_id)
);

COMMENT ON TABLE role_permission IS 'Таблица связи прав доступа с ролями';

CREATE TABLE IF NOT EXISTS user_data
(
    id               serial PRIMARY KEY,
    user_id          integer NOT NULL,
    name             varchar(254),
    avatar           varchar(254),
    city             varchar(254),
    activity         varchar(254),
    phone            varchar(64),
    birth_date       DATE,
    sex              boolean DEFAULT true,
    email_subscriber boolean DEFAULT false,
    geo_location     varchar(128),
    rank             integer DEFAULT 0 NOT NULL,
    CONSTRAINT user_id_fkey FOREIGN KEY (user_id) REFERENCES "user"(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS user_token
(
    ID               serial PRIMARY KEY,
    user_id          integer NOT NULL,
    user_agent       varchar(254) NOT NULL,
    token            character varying(254) NOT NULL,
    created          integer NOT NULL,
    expires          integer NOT NULL,
    CONSTRAINT       user_token_token_key UNIQUE (token),
    CONSTRAINT user_id_fkey FOREIGN KEY (user_id) REFERENCES "user"(id) ON DELETE CASCADE 
);


CREATE TABLE donation (
    id               serial PRIMARY KEY,
    title            varchar(254) NOT NULL,
    description      text,
    img_url          varchar(254),
    amount           money,
    necessary_amount money,
    end_date         date,
    latest_recharge  date,
    currency         varchar(3) NOT NULL DEFAULT 'RUR'
);
COMMENT ON TABLE donation IS 'Пожертвования';

CREATE TABLE vacancy (
    id            serial,
    title         varchar(254) NOT NULL,
    description   text,
    img_uri       varchar(254),
    CONSTRAINT vacancy_pkey PRIMARY KEY(id)
);
COMMENT ON TABLE vacancy IS 'Вакансии';