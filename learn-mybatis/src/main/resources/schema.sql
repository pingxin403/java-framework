create TABLE if not exists tb_user
(
  id       integer primary key auto_increment,
  username varchar(50),
  birthday date,
  sex      varchar(5),
  address  varchar(100)
);

-- 订单
create table if not exists  tb_orders
(
  id       integer primary key auto_increment,
  `number` varchar(100),
  user_id  integer,
  CONSTRAINT fk_o_u FOREIGN KEY (user_id) REFERENCES tb_user (id)
);

-- 商品
create table if not exists  tb_product
(
  id    integer primary key auto_increment,
  name  varchar(100),
  price numeric(8, 2)
);

-- 订单商品关系，多对多
create table if not exists  tb_ordersitem
(
  id         integer primary key auto_increment,
  orders_id  integer,
  product_id integer,
  CONSTRAINT fk_ot_o FOREIGN KEY (orders_id) REFERENCES tb_orders (id),
  CONSTRAINT fk_ot_p FOREIGN KEY (product_id) REFERENCES tb_product (id)
);

-- 身份证
create table if not exists tb_idcard
(
  id   integer primary key auto_increment,
  code varchar(100)
);

-- 人
create table if not exists tb_person
(
  id      integer primary key auto_increment,
  name    varchar(50),
  age     int,
  sex     varchar(5),
  card_id integer,
  CONSTRAINT fk_p_d FOREIGN KEY (card_id) REFERENCES tb_idcard (id)
);

-- 部门表
create table if not exists tbl_dept
(
  id        integer primary key auto_increment,
  dept_name varchar(50)
);

-- 员工表
create table if not exists tbl_employee
(
  id        integer primary key auto_increment,
  last_name varchar(50),
  email     varchar(50),
  gender    varchar(5),
  d_id      integer,
  CONSTRAINT fk_e_e FOREIGN KEY (d_id) REFERENCES tbl_dept (id)

);


-- 添加data

insert into tb_user
values (1, '张三', '2000-2-5', '男', '地球'),
       (2, '小红', '2001-2-22', '女', '火星');

insert into tb_orders
values (1, '1000001', 1),
       (2, '1000002', 2);

insert into tb_product
values (1, 'Java从入门到精通', 50.2),
       (2, '数据结构详解', 63.0);

insert into tb_ordersitem
values (1, 1, 1),
       (2, 1, 2),
       (3, 2, 2);

insert into tb_idcard
values (1, '412121196602212699'),
       (2, '212225198805035466');

insert into tb_person
values (1, 'tom', 66, '男', 1),
       (2, 'rose', 29, '女', 2);

insert into tbl_dept
values (1, '开发部门'),
       (2, '销售部门'),
       (3, '售后部门');
insert into tbl_employee
values (1, 'lisa', '123@123.com', '0', 1),
       (2, 'tom', '123@456.com', '1', 2),
       (3, 'lisa2', '123@123.com', '0', 3),
       (4, 'lisa3', '123@123.com', '1', 1),
       (5, 'lisa4', '123@123.com', '0', 2),
       (6, 'lisa5', '123@123.com', '0', 3),
       (7, 'lisa6', '123@123.com', '1', 1),
       (8, 'lisa7', '123@123.com', '1', 2),
       (9, 'lisa8', '123@123.com', '0', 3),
       (10, 'lisa9', '123@123.com', '1', 1),
       (11, 'lisa10', '123@123.com', '0', 2),
       (12, 'lisa11', '123@123.com', '1', 3),
       (13, 'lisa12', '123@123.com', '0', 1);

