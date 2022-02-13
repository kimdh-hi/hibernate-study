drop table if exists book cascade;
drop table if exists author;

create table book
(
    id        bigint not null auto_increment primary key,
    isbn      varchar(255),
    publisher varchar(255),
    title     varchar(255),
    author_id BIGINT
) engine = InnoDB;

create table author
(
    id         bigint not null auto_increment primary key,
    first_name varchar(255),
    last_name  varchar(255)
) engine = InnoDB;

alter table book
    add constraint book_author_fk foreign key (author_id) references author (id);

insert into author (first_name, last_name) values ('daehyun', 'kim');

insert into book (isbn, publisher, title, author_id) values ('1234-5555', 'pub123',
                                                             'book1', (select id from author where first_name = 'daehyun' and last_name = 'kim') );

insert into book (isbn, publisher, title, author_id) values ('2222-333333', 'pub123',
                                                             'book2', (select id from author where first_name = 'daehyun' and last_name = 'kim') );

insert into book (isbn, publisher, title, author_id) values ('978-2132134', 'pub444',
                                                             'book3', (select id from author where first_name = 'daehyun' and last_name = 'kim') );

insert into author (first_name, last_name) values ('david', 'lee');

insert into book (isbn, publisher, title, author_id) values ('978-12323123', 'pub555',
                                                             'book4', (select id from author where first_name = 'david' and last_name = 'lee') );

insert into author (first_name, last_name) values ('gildong', 'hong');

insert into book (isbn, publisher, title, author_id) values ('978-4444422222', 'pub666',
                                                             'book5', (select id from author where first_name = 'gildong' and last_name = 'hong') );