drop table if exists article_table;
drop table if exists file;

create table article_table (
    id bigint not null auto_increment,
    content longtext,
    create_date datetime(6),
    title varchar(50) not null,
    primary key (id)
);

create table file (
    pid bigint not null auto_increment,
    filename varchar(100) not null,
    create_date datetime(6),
    primary key (pid)
);