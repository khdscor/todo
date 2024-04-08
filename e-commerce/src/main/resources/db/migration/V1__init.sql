drop table if exists article_table;

create table article_table (
    id bigint not null auto_increment,
    content longtext,
    create_date datetime(6),
    title varchar(50) not null,
    primary key (id)
);