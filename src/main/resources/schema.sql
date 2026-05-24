create database if not exists todo;

use todo;

create table if not exists tasks (
    id int not null auto_increment,
    is_completed bool default false,
    deadline date not null,
    title varchar(64) not null,
    description	varchar(255) not null,
    primary key (id)
);

create table if not exists users (
    id int not null auto_increment,
    username varchar(255) not null,
    password varchar(255) not null,
    primary key (id)
);