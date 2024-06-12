create table student
(
    id        serial
        primary key,
    name      varchar not null,
    last_name varchar not null,
    email     varchar not null,
    password  varchar not null,
    ciclo     integer not null,
    phone     integer not null
);

alter table student
    owner to postgres;

create table group_utp
(
    id          serial
        primary key,
    user_id     integer not null
        references student,
    name        varchar not null,
    class       varchar not null,
    description varchar,
    has_class   boolean not null,
    students    integer,
    img         varchar not null,
    code        varchar not null
);

alter table group_utp
    owner to postgres;

create table notice
(
    id        serial
        primary key,
    user_id   integer   not null
        references student,
    group_id  integer   not null
        references group_utp,
    message   varchar   not null,
    date_time timestamp not null,
    affair    varchar
);

alter table notice
    owner to postgres;

create table link
(
    id       serial
        primary key,
    group_id integer not null
        references group_utp,
    name     varchar not null,
    url      varchar not null,
    user_id  integer not null
        references student
);

alter table link
    owner to postgres;

create table img_group
(
    id        serial
        primary key,
    group_id  integer   not null
        references group_utp,
    user_id   integer   not null
        references student,
    date_time timestamp not null,
    src       varchar   not null
);

alter table img_group
    owner to postgres;

create table type_column
(
    id          serial
        primary key,
    name        varchar not null,
    description varchar,
    is_editable boolean not null
);

alter table type_column
    owner to postgres;

create table kanban
(
    id       serial
        primary key,
    group_id integer not null
        references group_utp,
    name     varchar not null
);

alter table kanban
    owner to postgres;

create table colum_kanban
(
    id             serial
        primary key,
    kanban_id      integer not null
        references kanban,
    type_column_id integer not null
        references type_column,
    color          varchar not null,
    title          varchar not null,
    order_colum    integer not null
);

alter table colum_kanban
    owner to postgres;

create table task
(
    id               serial
        primary key,
    colum_kanban_id  integer   not null
        constraint task___fk___type___colun
            references colum_kanban,
    kanban_id        integer   not null
        references kanban,
    name             varchar   not null,
    description      varchar,
    assigned_user_id integer
        references student,
    date             timestamp not null,
    priority         integer   not null,
    last_updated     timestamp,
    finish_user_id   integer
        constraint task___fk__finish__studnt
            references student,
    limit_time       timestamp
);

alter table task
    owner to postgres;

create table role
(
    id   integer default nextval('roles_id_seq'::regclass) not null
        constraint id
            primary key,
    name varchar(100)                                      not null
);

alter table role
    owner to postgres;

create table user_group
(
    id       serial
        primary key,
    user_id  integer not null
        references student,
    group_id integer not null
        references group_utp,
    role_id  integer not null
        constraint user_group___fk___role
            references role
);

alter table user_group
    owner to postgres;

