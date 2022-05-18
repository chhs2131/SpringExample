
create table pay (
                     id         bigint not null auto_increment,
                     amount     bigint,
                     tx_name     varchar(255),
                     tx_date_time datetime,
                     primary key (id)
) engine = InnoDB;
create table pay2 (
                      id         bigint not null auto_increment,
                      amount     bigint,
                      tx_name     varchar(255),
                      tx_date_time datetime,
                      primary key (id)
) engine = InnoDB;

insert into pay (amount, tx_name, tx_date_time) VALUES (1000, 'trade1', '2018-09-10 00:00:00');
insert into pay (amount, tx_name, tx_date_time) VALUES (2000, 'trade2', '2018-09-10 00:00:00');
insert into pay (amount, tx_name, tx_date_time) VALUES (3000, 'trade3', '2018-09-10 00:00:00');
insert into pay (amount, tx_name, tx_date_time) VALUES (4000, 'trade4', '2018-09-10 00:00:00');




create table teacher (
                      id         bigint not null auto_increment,
                      name       varchar(255),
                      subject    varchar(255),
                      primary key (id)
) engine = InnoDB;
insert into teacher (name) VALUES ('1선생님');
insert into teacher (name) VALUES ('2선생님');
insert into teacher (name) VALUES ('3선생님');
insert into teacher (name) VALUES ('4선생님');
insert into teacher (name) VALUES ('5선생님');
insert into teacher (name) VALUES ('6선생님');
insert into teacher (name) VALUES ('7선생님');
insert into teacher (name) VALUES ('8선생님');
