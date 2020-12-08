create database `project_crowd` char set utf8;


use `project_crowd`;

drop table if exists `t_admin`;
create table `t_admin`(
    `id` int not null auto_increment,
    `login_acct` varchar(255) not null ,
    `user_pswd` char(32) not null ,
    `user_name` varchar(255) not null ,
    `email` varchar(255) not null ,
    `create_time` char(19),
    primary key (`id`)
);


alter table  project_crowd.`t_admin` add unique index (`login_acct`);

CREATE TABLE `project_crowd`.`t_role` ( `id` INT NOT NULL, `name` CHAR(100), PRIMARY KEY
(`id`) );
ALTER TABLE `project_crowd`.`t_role` CHANGE `id` `id` INT(11) NOT NULL AUTO_INCREMENT;

create table `t_menu`
(
    `id` int(11) not null auto_increment,
    `pid` int(11),
    `name` varchar(200),
    `url` varchar(200),
    `icon` varchar(200),
    primary key (`id`)
);

insert into `t_menu` (`id`, `pid`, `name`, `icon`, `url`) values('1',NULL,'系统权限菜单','glyphicon
glyphicon-th-list',NULL);
insert into `t_menu` (`id`, `pid`, `name`, `icon`, `url`) values('2','1',' 控 制 面 板 ','glyphicon
glyphicon-dashboard','main.htm');
insert into `t_menu` (`id`, `pid`, `name`, `icon`, `url`) values('3','1','权限管理','glyphicon glyphicon
glyphicon-tasks',NULL);
insert into `t_menu` (`id`, `pid`, `name`, `icon`, `url`) values('4','3',' 用 户 维 护 ','glyphicon
glyphicon-user','user/index.htm');
insert into `t_menu` (`id`, `pid`, `name`, `icon`, `url`) values('5','3',' 角 色 维 护 ','glyphicon
glyphicon-king','role/index.htm');
insert into `t_menu` (`id`, `pid`, `name`, `icon`, `url`) values('6','3',' 菜 单 维 护 ','glyphicon
glyphicon-lock','permission/index.htm');
insert into `t_menu` (`id`, `pid`, `name`, `icon`, `url`) values('7','1',' 业 务 审 核 ','glyphicon
glyphicon-ok',NULL);
insert into `t_menu` (`id`, `pid`, `name`, `icon`, `url`) values('8','7',' 实 名 认 证 审 核 ','glyphicon
glyphicon-check','auth_cert/index.htm');
insert into `t_menu` (`id`, `pid`, `name`, `icon`, `url`) values('9','7',' 广 告 审 核 ','glyphicon
glyphicon-check','auth_adv/index.htm');
insert into `t_menu` (`id`, `pid`, `name`, `icon`, `url`) values('10','7',' 项 目 审 核 ','glyphicon
glyphicon-check','auth_project/index.htm');
insert into `t_menu` (`id`, `pid`, `name`, `icon`, `url`) values('11','1',' 业 务 管 理 ','glyphicon
glyphicon-th-large',NULL);
insert into `t_menu` (`id`, `pid`, `name`, `icon`, `url`) values('12','11',' 资 质 维 护 ','glyphicon
glyphicon-picture','cert/index.htm');
insert into `t_menu` (`id`, `pid`, `name`, `icon`, `url`) values('13','11',' 分 类 管 理 ','glyphicon
glyphicon-equalizer','certtype/index.htm');
insert into `t_menu` (`id`, `pid`, `name`, `icon`, `url`) values('14','11',' 流 程 管 理 ','glyphicon
glyphicon-random','process/index.htm');
insert into `t_menu` (`id`, `pid`, `name`, `icon`, `url`) values('15','11',' 广 告 管 理 ','glyphicon
glyphicon-hdd','advert/index.htm');
insert into `t_menu` (`id`, `pid`, `name`, `icon`, `url`) values('16','11',' 消 息 模 板 ','glyphicon
glyphicon-comment','message/index.htm');
insert into `t_menu` (`id`, `pid`, `name`, `icon`, `url`) values('17','11',' 项 目 分 类 ','glyphicon
glyphicon-list','projectType/index.htm');
insert into `t_menu` (`id`, `pid`, `name`, `icon`, `url`) values('18','11',' 项 目 标 签 ','glyphicon
glyphicon-tags','tag/index.htm');
insert into `t_menu` (`id`, `pid`, `name`, `icon`, `url`) values('19','1',' 参 数 管 理 ','glyphicon
glyphicon-list-alt','param/index.htm');