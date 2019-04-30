
CREATE TABLE IF NOT EXISTS location (
    id               serial PRIMARY KEY,
    "name"           character varying(255) not null
);
COMMENT ON TABLE location IS 'Справочник городов где читались лекции';

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
CREATE TYPE public.media_data_type AS ENUM ( 'video', 'image');

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

CREATE TABLE IF NOT EXISTS media_scripture
(
    id               serial PRIMARY KEY,
    media_id         integer not null references media(id) on delete cascade,
    scripture_id     integer not null references scripture(id),
    canto            integer, -- песнь
    chapter          integer, -- глава
    verse            integer -- стих   
);
COMMENT ON TABLE media_scripture IS 'Песнь/глава/стих священных писаний, объясненные в лекции';

COMMENT ON TABLE category IS 'Священные писания';

CREATE TABLE IF NOT EXISTS media_tag (
    media_id         integer not null references media(id) on delete cascade,
    tag_id           integer not null references tag(id) on delete cascade
);
COMMENT ON TABLE media_tag IS 'Связка лекции и ключевых слов';

-- data_type  ('picture', 'video');
CREATE TABLE media_data
(
    id                serial PRIMARY KEY,
    media_id          integer references media(id) on delete cascade,
    data_type         media_data_type,
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


CREATE INDEX collection_hierarchy_parent_idx ON collection_hierarchy(parent_id);
CREATE INDEX collection_hierarchy_children_idx ON collection_hierarchy(children_id);
ALTER TABLE collection_hierarchy ADD CONSTRAINT parent_children_pk PRIMARY KEY (parent_id, children_id);

CREATE INDEX media_scripture_media_idx ON media_scripture(media_id);
CREATE INDEX media_scripture_scripture_idx ON media_scripture(scripture_id);
CREATE INDEX media_scripture_canto_idx ON media_scripture(canto);
CREATE INDEX media_scripture_chapter_idx ON media_scripture(chapter);
CREATE INDEX media_scripture_verse_idx ON media_scripture(verse);
ALTER TABLE media_scripture ADD CONSTRAINT media_scripture_unq UNIQUE (media_id, scripture_id, canto, chapter, verse);

CREATE INDEX collection_media_collection_idx ON collection_media(collection_id);
CREATE INDEX collection_media_media_idx ON collection_media(media_id);
ALTER TABLE collection_media ADD CONSTRAINT collection_media_pk PRIMARY KEY (collection_id, media_id);

CREATE INDEX media_data_media_idx ON media_data(media_id);

CREATE INDEX media_tag_tag_idx ON media_tag(tag_id);
CREATE INDEX media_tag_media_idx ON media_tag(media_id);
ALTER TABLE media_tag ADD CONSTRAINT media_tag_pk PRIMARY KEY (media_id, tag_id);

CREATE INDEX tag_name_idx ON tag(name);

CREATE INDEX media_type_idx ON media(type);
CREATE INDEX media_title_idx ON media(title);
CREATE INDEX media_jiraref_idx ON media(jira_ref);
CREATE INDEX media_occurrence_date_idx ON media(occurrence_date);
CREATE INDEX media_issue_date_idx ON media(issue_date);
CREATE INDEX media_category_idx ON media(category_id);
CREATE INDEX media_location_idx ON media(location_id);
CREATE INDEX media_language_idx ON media(language);