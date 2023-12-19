drop table if exists exhibition_type;
drop table if exists booking;
drop table if exists type;
drop table if exists exhibition;
drop table if exists users;

create table users(
                      id bigserial primary key,
                      username varchar,
                      email varchar,
                      hash_password varchar,
                      user_role varchar default 'user'
);

create table exhibition(
                   id bigserial primary key,
                   start_working_time time,
                   end_working_time time,
                   start_date date,
                   end_date date
);

create table type(
                             id bigserial primary key,
                             type varchar unique not null
);

create table booking(
                        id bigserial primary key,
                        user_id bigint,
                        foreign key (user_id) references users(id),
                        exhibition_id bigint,
                        foreign key (exhibition_id) references exhibition(id),
                        from_time timestamp,
                        to_time timestamp,
                        type bigint not null,
                        foreign key (type) references type (id)
);


create table exhibition_type(
    exhibition_id bigint not null,
    type_id bigint not null,
    foreign key (exhibition_id) references exhibition(id),
    foreign key (type_id) references type(id)
);