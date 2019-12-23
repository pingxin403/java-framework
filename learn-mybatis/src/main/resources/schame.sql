create TABLE tb_user
(
  id       integer primary key auto_increment,
  username varchar(50),
  birthday date,
  sex      varchar(5),
  address  varchar(100)
);

create table tb_orders
(
  id       integer primary key auto_increment,
  `number` varchar(100),
  user_id  integer,
  CONSTRAINT fk_o_u FOREIGN KEY (user_id) REFERENCES tb_user (id)
);

create table tb_idcard
(
  id   integer primary key auto_increment,
  code varchar(100)
);

create table tb_person
(
  id      integer primary key auto_increment,
  name    varchar(50),
  age     int,
  sex     varchar(5),
  card_id integer,
  CONSTRAINT fk_p_d FOREIGN KEY (card_id) REFERENCES tb_idcard (id)
);


-- 添加data

insert into tb_user
values (1, '张三', '2000-2-5', '男', '地球'),
       (2, '小红', '2001-2-22', '女', '火星');

insert into tb_orders
values (1, '1000001', 1),
       (2, '1000002', 2);

insert into tb_idcard
values (1, '412121196602212699'),
       (2, '212225198805035466');

insert into tb_person
values (1, 'tom', 66, '男', 1),
       (2, 'rose', 29, '女', 2);