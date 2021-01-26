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

#xiu
ALTER TABLE `project_crowd`.`t_admin` CHANGE `user_pswd` `user_pswd` CHAR(100) CHARSET
    utf8 COLLATE utf8_general_ci NOT NULL;


drop table if exists `t_member`;
create table `t_member`
(
    `id` int(11) not null auto_increment,
    `login_acct` varchar(255) not null,
    `user_pswd` char(200) not null,
    `username` varchar(255),
    `email` varchar(255),
    `auth_status` int(4) comment '实名认证状态 0 - 未实名认证， 1 - 实名认证申请中， 2 - 已实名认证',
    `user_type` int(4) comment ' 0 - 个人， 1 - 企业',
    `real_name` varchar(255),
    `card_num` varchar(255),
    `acct_type` int(4) comment '0 - 企业， 1 - 个体， 2 - 个人， 3 - 政府',
    primary key (id)
);

use `project_crowd`;

create table t_type
(
    id     int(11) not null auto_increment,
    name   varchar(255) comment '分类名称',
    remark varchar(255) comment '分类介绍',
    primary key (id)
);
create table t_project_type
(
    id        int not null auto_increment,
    projectid int(11),
    typeid    int(11),
    primary key (id)
);
create table t_tag
(
    id   int(11) not null auto_increment,
    pid  int(11),
    name varchar(255),
    primary key (id)
);
create table t_project_tag
(
    id        int(11) not null auto_increment,
    projectid int(11),
    tagid     int(11),
    primary key (id)
);

create table t_project
(
    id                  int(11) not null auto_increment,
    project_name        varchar(255) comment '项目名称',
    project_description varchar(255) comment '项目描述',
    money               bigint(11) comment '筹集金额',
    day                 int(11) comment '筹集天数',
    status              int(4) comment '0-即将开始，1-众筹中，2-众筹成功，3-众筹失败
',
    deploydate          varchar(10) comment '项目发起时间',
    supportmoney        bigint(11) comment '已筹集到的金额',
    supporter           int(11) comment '支持人数',
    completion          int(3) comment '百分比完成度',
    memberid            int(11) comment '发起人的会员 id',
    createdate          varchar(19) comment '项目创建时间',
    follower            int(11) comment '关注人数',
    header_picture_path varchar(255) comment '头图路径',
    primary key (id)
);
create table t_project_item_pic
(
    id            int(11) not null auto_increment,
    projectid     int(11),
    item_pic_path varchar(255),
    primary key (id)
);

create table t_member_launch_info
(
    id                 int(11) not null auto_increment,
    memberid           int(11) comment '会员 id',
    description_simple varchar(255) comment '简单介绍',
    description_detail varchar(255) comment '详细介绍',
    phone_num          varchar(255) comment '联系电话',
    service_num        varchar(255) comment '客服电话',
    primary key (id)
);

create table t_return
(
    id               int(11) not null auto_increment,
    projectid        int(11),
    type             int(4) comment '0 - 实物回报， 1 虚拟物品回报',
    supportmoney     int(11) comment '支持金额',
    content          varchar(255) comment '回报内容',
    count            int(11) comment '回报产品限额，“0”为不限回报数量',
    signalpurchase   int(11) comment '是否设置单笔限购',
    purchase         int(11) comment '具体限购数量',
    freight          int(11) comment '运费，“0”为包邮',
    invoice          int(4) comment '0 - 不开发票， 1 - 开发票',
    returndate       int(11) comment '项目结束后多少天向支持者发送回报',
    describ_pic_path varchar(255) comment '说明图片路径',
    primary key (id)
);

create table t_member_confirm_info
(
    id       int(11) not null auto_increment,
    memberid int(11) comment '会员 id',
    paynum   varchar(200) comment '易付宝企业账号',
    cardnum  varchar(200) comment '法人身份证号',
    primary key (id)
);