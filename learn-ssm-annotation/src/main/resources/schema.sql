CREATE TABLE `user`
(
  `id`          bigint(20)  NOT NULL AUTO_INCREMENT,
  `name`        varchar(10) NOT NULL DEFAULT '' COMMENT '名字',
  `create_time` timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
);

INSERT INTO `user` (`name`, `create_time`, `update_time`)
VALUES ('Nacos', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);