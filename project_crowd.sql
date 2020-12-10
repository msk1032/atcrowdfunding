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

drop table if exists `t_menu`;
create table `t_menu`
(
    `id` int(11) not null auto_increment,
    `pid` int(11),
    `name` varchar(200),
    `url` varchar(200),
    `icon` varchar(200),
    primary key (`id`)
);

insert into `t_menu` (`id`, `pid`, `name`, `icon`, `url`)
values('1',NULL,'系统权限菜单','glyphicon glyphicon-th-list',NULL);
insert into `t_menu` (`id`, `pid`, `name`, `icon`, `url`)
values('2','1',' 控制面板 ','glyphicon glyphicon-dashboard','main.htm');
insert into `t_menu` (`id`, `pid`, `name`, `icon`, `url`)
values('3','1','权限管理','glyphicon glyphicon-tasks',NULL);
insert into `t_menu` (`id`, `pid`, `name`, `icon`, `url`)
values('4','3',' 用户维护 ','glyphicon glyphicon-user','user/index.htm');
insert into `t_menu` (`id`, `pid`, `name`, `icon`, `url`)
values('5','3',' 角色维护 ','glyphicon glyphicon-king','role/index.htm');
insert into `t_menu` (`id`, `pid`, `name`, `icon`, `url`)
values('6','3',' 菜单维护 ','glyphicon glyphicon-lock','permission/index.htm');
insert into `t_menu` (`id`, `pid`, `name`, `icon`, `url`)
values('7','1',' 业务审核 ','glyphicon glyphicon-ok',NULL);
insert into `t_menu` (`id`, `pid`, `name`, `icon`, `url`)
values('8','7',' 实名认证审核 ','glyphicon glyphicon-check','auth_cert/index.htm');
insert into `t_menu` (`id`, `pid`, `name`, `icon`, `url`)
values('9','7',' 广告审核 ','glyphicon glyphicon-check','auth_adv/index.htm');
insert into `t_menu` (`id`, `pid`, `name`, `icon`, `url`)
values('10','7',' 项目审核 ','glyphicon glyphicon-check','auth_project/index.htm');
insert into `t_menu` (`id`, `pid`, `name`, `icon`, `url`)
values('11','1',' 业务管理 ','glyphicon glyphicon-th-large',NULL);
insert into `t_menu` (`id`, `pid`, `name`, `icon`, `url`)
values('12','11',' 资质维护 ','glyphicon glyphicon-picture','cert/index.htm');
insert into `t_menu` (`id`, `pid`, `name`, `icon`, `url`)
values('13','11',' 分类管理 ','glyphicon glyphicon-equalizer','certtype/index.htm');
insert into `t_menu` (`id`, `pid`, `name`, `icon`, `url`)
values('14','11',' 流程管理 ','glyphicon glyphicon-random','process/index.htm');
insert into `t_menu` (`id`, `pid`, `name`, `icon`, `url`)
values('15','11',' 广告管理 ','glyphicon glyphicon-hdd','advert/index.htm');
insert into `t_menu` (`id`, `pid`, `name`, `icon`, `url`)
values('16','11',' 消息模板 ','glyphicon glyphicon-comment','message/index.htm');
insert into `t_menu` (`id`, `pid`, `name`, `icon`, `url`)
values('17','11',' 项目分类 ','glyphicon glyphicon-list','projectType/index.htm');
insert into `t_menu` (`id`, `pid`, `name`, `icon`, `url`)
values('18','11',' 项目标签 ','glyphicon glyphicon-tags','tag/index.htm');
insert into `t_menu` (`id`, `pid`, `name`, `icon`, `url`)
values('19','1',' 参数管理 ','glyphicon glyphicon-list-alt','param/index.htm');


drop table if exists `inner_admin_role`;
CREATE TABLE `project_crowd`.`inner_admin_role` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `admin_id` INT,
    `role_id` INT,
    PRIMARY KEY (`id`)
);
drop table if exists `t_auth`;
CREATE TABLE `t_auth` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(200) DEFAULT NULL,
    `title` varchar(200) DEFAULT NULL,
    `category_id` int(11) DEFAULT NULL,
    PRIMARY KEY (`id`)
);
INSERT INTO t_auth(id,`name`,title,category_id) VALUES(1,'','用户模块',NULL);
INSERT INTO t_auth(id,`name`,title,category_id) VALUES(2,'user:delete','删除',1);
INSERT INTO t_auth(id,`name`,title,category_id) VALUES(3,'user:get','查询',1);
INSERT INTO t_auth(id,`name`,title,category_id) VALUES(4,'','角色模块',NULL);
INSERT INTO t_auth(id,`name`,title,category_id) VALUES(5,'role:delete','删除',4);
INSERT INTO t_auth(id,`name`,title,category_id) VALUES(6,'role:get','查询',4);
INSERT INTO t_auth(id,`name`,title,category_id) VALUES(7,'role:add','新增',4);


drop table if exists `inner_role_auth`;
CREATE TABLE `project_crowd`.`inner_role_auth` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `role_id` INT, `auth_id` INT,
    PRIMARY KEY (`id`)
);
