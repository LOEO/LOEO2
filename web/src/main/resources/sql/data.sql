/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : loeo

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2018-03-08 15:53:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for persistent_logins
-- ----------------------------
DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of persistent_logins
-- ----------------------------

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` char(12) CHARACTER SET utf8mb4 NOT NULL,
  `url` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `method` varchar(10) CHARACTER SET utf8mb4 DEFAULT NULL,
  `action` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `params` longtext CHARACTER SET utf8mb4,
  `create_user` char(12) CHARACTER SET utf8mb4 DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('3pswq5acsidc', 'http://localhost:9999/api/users/1/menus', 'POST', 'com.loeo.entity.SysResource@5f2b8604', '{}', '1', '2018-03-08 14:21:14');
INSERT INTO `sys_log` VALUES ('3psxfbe6tc00', 'http://localhost:9999/api/users/1/menus', 'POST', '获取主菜单', '{}', '1', '2018-03-08 14:29:04');
INSERT INTO `sys_log` VALUES ('3psxfwxfavb4', 'http://localhost:9999/users', 'GET', '用户管理', '{}', '1', '2018-03-08 14:29:15');
INSERT INTO `sys_log` VALUES ('3psxg7z3mx34', 'http://localhost:9999/roles', 'GET', '角色管理', '{}', '1', '2018-03-08 14:29:21');
INSERT INTO `sys_log` VALUES ('3psxmhim2zuo', 'http://localhost:9999/users', 'GET', '用户管理', '{}', '1', '2018-03-08 14:31:18');
INSERT INTO `sys_log` VALUES ('3psxtmomr08w', 'http://localhost:9999/api/users/35', 'POST', '修改', '{\"id\":[\"35\"],\"username\":[\"admin2\"],\"password\":[\"123321\"],\"password1\":[\"123321\"],\"nickname\":[\"11233311111\"],\"birthday\":[\"2018-03-08 16:00:00\"],\"sex\":[\"男\"],\"email\":[\"11@qq.com\"],\"phone\":[\"123321\"],\"enable\":[\"1\"]}', '1', '2018-03-08 14:33:32');
INSERT INTO `sys_log` VALUES ('3pt23z80fg8w', 'http://localhost:9999/api/users/1/menus', 'POST', '获取主菜单', '{}', '1', '2018-03-08 15:21:35');
INSERT INTO `sys_log` VALUES ('3pt243nmfvnk', 'http://localhost:9999/users', 'GET', '用户管理', '{}', '1', '2018-03-08 15:21:38');
INSERT INTO `sys_log` VALUES ('3pt24n2hj6dc', 'http://localhost:9999/orgs', 'GET', '组织架构', '{}', '1', '2018-03-08 15:21:48');
INSERT INTO `sys_log` VALUES ('3pt24pu3herk', 'http://localhost:9999/roles', 'GET', '角色管理', '{}', '1', '2018-03-08 15:21:49');
INSERT INTO `sys_log` VALUES ('3pt25t9ja8e8', 'http://localhost:9999/api/users/1/menus', 'POST', '获取主菜单', '{}', '1', '2018-03-08 15:22:10');
INSERT INTO `sys_log` VALUES ('3pt27lax2l1c', 'http://localhost:9999/api/users/1/menus', 'POST', '获取主菜单', '{}', '1', '2018-03-08 15:22:43');

-- ----------------------------
-- Table structure for sys_org
-- ----------------------------
DROP TABLE IF EXISTS `sys_org`;
CREATE TABLE `sys_org` (
  `id` char(12) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `pid` char(12) DEFAULT NULL,
  `descp` varchar(200) DEFAULT NULL,
  `leaf` varchar(10) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `create_user` char(12) DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `update_user` char(12) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_fj4wmb1pc7b1act5qy87bjws9` (`pid`),
  KEY `FK_ptea98lnhphw5t1is8kkb061p` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_org
-- ----------------------------
INSERT INTO `sys_org` VALUES ('16', '开发部', '0', '开发部', 'false', null, null, null, null);
INSERT INTO `sys_org` VALUES ('2', '人事部', '0', '人事部', 'true', null, null, null, null);
INSERT INTO `sys_org` VALUES ('27', '软件部', '16', '描述', 'true', null, null, null, null);
INSERT INTO `sys_org` VALUES ('28', '硬件部', '16', '2', 'true', null, null, null, null);
INSERT INTO `sys_org` VALUES ('29', '测试部', '0', '描述', 'false', null, null, null, null);
INSERT INTO `sys_org` VALUES ('30', '测试2组', '29', '描述', 'true', null, null, null, null);

-- ----------------------------
-- Table structure for sys_privilege
-- ----------------------------
DROP TABLE IF EXISTS `sys_privilege`;
CREATE TABLE `sys_privilege` (
  `id` char(12) NOT NULL,
  `master` varchar(20) DEFAULT NULL,
  `master_value` varchar(20) DEFAULT NULL,
  `access` varchar(20) DEFAULT NULL,
  `access_value` varchar(20) DEFAULT NULL,
  `operation` varchar(20) DEFAULT NULL,
  `create_user` char(12) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `update_user` char(12) DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_privilege
-- ----------------------------
INSERT INTO `sys_privilege` VALUES ('202', 'user', '2', '0', '7', 'enable', '1', '2016-11-27 13:56:57', '1', '2016-11-27 13:56:57');
INSERT INTO `sys_privilege` VALUES ('203', 'user', '2', '1', '10', 'enable', '1', '2016-11-27 13:56:57', '1', '2016-11-27 13:56:57');
INSERT INTO `sys_privilege` VALUES ('204', 'user', '2', '1', '11', 'enable', '1', '2016-11-27 13:56:57', '1', '2016-11-27 13:56:57');
INSERT INTO `sys_privilege` VALUES ('205', 'user', '2', '1', '13', 'enable', '1', '2016-11-27 13:56:57', '1', '2016-11-27 13:56:57');
INSERT INTO `sys_privilege` VALUES ('213', 'user', '1', '2', '23', 'enable', '1', '2017-01-02 21:32:46', '1', '2017-01-02 21:32:46');
INSERT INTO `sys_privilege` VALUES ('214', 'user', '1', '1', '8', 'enable', '1', '2017-01-02 21:32:46', '1', '2017-01-02 21:32:46');
INSERT INTO `sys_privilege` VALUES ('215', 'user', '1', '0', '7', 'enable', '1', '2017-01-02 21:32:46', '1', '2017-01-02 21:32:46');
INSERT INTO `sys_privilege` VALUES ('216', 'user', '1', '1', '9', 'enable', '1', '2017-01-02 21:32:46', '1', '2017-01-02 21:32:46');
INSERT INTO `sys_privilege` VALUES ('217', 'user', '1', '1', '10', 'enable', '1', '2017-01-02 21:32:46', '1', '2017-01-02 21:32:46');
INSERT INTO `sys_privilege` VALUES ('218', 'user', '1', '1', '11', 'enable', '1', '2017-01-02 21:32:46', '1', '2017-01-02 21:32:46');
INSERT INTO `sys_privilege` VALUES ('219', 'user', '1', '1', '13', 'enable', '1', '2017-01-02 21:32:46', '1', '2017-01-02 21:32:46');
INSERT INTO `sys_privilege` VALUES ('220', 'user', '1', '2', '25', 'enable', '1', '2017-01-02 21:32:46', '1', '2017-01-02 21:32:46');
INSERT INTO `sys_privilege` VALUES ('221', 'user', '1', '2', '24', 'enable', '1', '2017-01-02 21:32:46', '1', '2017-01-02 21:32:46');
INSERT INTO `sys_privilege` VALUES ('222', 'role', '4', '0', '7', 'enable', '1', '2018-02-07 10:33:05', '1', '2018-02-07 10:33:05');
INSERT INTO `sys_privilege` VALUES ('223', 'role', '4', '1', '8', 'enable', '1', '2018-02-07 10:33:05', '1', '2018-02-07 10:33:05');
INSERT INTO `sys_privilege` VALUES ('224', 'role', '4', '2', '23', 'enable', '1', '2018-02-07 10:33:05', '1', '2018-02-07 10:33:05');
INSERT INTO `sys_privilege` VALUES ('225', 'role', '4', '2', '24', 'enable', '1', '2018-02-07 10:33:05', '1', '2018-02-07 10:33:05');
INSERT INTO `sys_privilege` VALUES ('226', 'role', '4', '2', '25', 'enable', '1', '2018-02-07 10:33:05', '1', '2018-02-07 10:33:05');
INSERT INTO `sys_privilege` VALUES ('227', 'role', '4', '1', '9', 'enable', '1', '2018-02-07 10:33:05', '1', '2018-02-07 10:33:05');
INSERT INTO `sys_privilege` VALUES ('228', 'role', '4', '1', '10', 'enable', '1', '2018-02-07 10:33:05', '1', '2018-02-07 10:33:05');
INSERT INTO `sys_privilege` VALUES ('229', 'role', '4', '1', '11', 'enable', '1', '2018-02-07 10:33:05', '1', '2018-02-07 10:33:05');
INSERT INTO `sys_privilege` VALUES ('230', 'role', '4', '1', '13', 'enable', '1', '2018-02-07 10:33:05', '1', '2018-02-07 10:33:05');
INSERT INTO `sys_privilege` VALUES ('231', 'role', '5', '0', '7', 'enable', '2', '2018-02-07 10:40:01', '2', '2018-02-07 10:40:01');
INSERT INTO `sys_privilege` VALUES ('232', 'role', '5', '1', '9', 'enable', '2', '2018-02-07 10:40:01', '2', '2018-02-07 10:40:01');
INSERT INTO `sys_privilege` VALUES ('233', 'role', '5', '1', '10', 'enable', '2', '2018-02-07 10:40:01', '2', '2018-02-07 10:40:01');
INSERT INTO `sys_privilege` VALUES ('234', 'role', '5', '1', '11', 'enable', '2', '2018-02-07 10:40:01', '2', '2018-02-07 10:40:01');
INSERT INTO `sys_privilege` VALUES ('235', 'role', '5', '1', '8', 'enable', '2', '2018-02-07 10:40:01', '2', '2018-02-07 10:40:01');
INSERT INTO `sys_privilege` VALUES ('236', 'role', '5', '2', '23', 'enable', '2', '2018-02-07 10:40:01', '2', '2018-02-07 10:40:01');
INSERT INTO `sys_privilege` VALUES ('237', 'role', '5', '2', '24', 'enable', '2', '2018-02-07 10:40:01', '2', '2018-02-07 10:40:01');
INSERT INTO `sys_privilege` VALUES ('238', 'role', '5', '2', '25', 'enable', '2', '2018-02-07 10:40:01', '2', '2018-02-07 10:40:01');
INSERT INTO `sys_privilege` VALUES ('239', 'role', '4', '1', '5', 'enable', '1', '2018-02-07 17:16:15', '1', '2018-02-07 17:16:19');
INSERT INTO `sys_privilege` VALUES ('294', 'role', '19', '1', '5', 'enable', '1', '2018-02-09 14:58:15', '1', '2018-02-09 14:58:15');
INSERT INTO `sys_privilege` VALUES ('324', 'role', '6', '1', '8', 'enable', '1', '2018-03-03 14:40:59', '1', '2018-03-03 14:40:59');
INSERT INTO `sys_privilege` VALUES ('325', 'role', '6', '0', '7', 'enable', '1', '2018-03-03 14:40:59', '1', '2018-03-03 14:40:59');
INSERT INTO `sys_privilege` VALUES ('326', 'role', '6', '3', '26', 'enable', '1', '2018-03-03 14:40:59', '1', '2018-03-03 14:40:59');
INSERT INTO `sys_privilege` VALUES ('327', 'role', '6', '2', '24', 'enable', '1', '2018-03-03 14:40:59', '1', '2018-03-03 14:40:59');
INSERT INTO `sys_privilege` VALUES ('3pifap6gbda8', 'role', '3phhov11jgn4', '1', '8', 'enable', '1', '2018-03-05 15:50:13', '1', '2018-03-05 15:50:13');
INSERT INTO `sys_privilege` VALUES ('3pifap7wr8jk', 'role', '3phhov11jgn4', '0', '7', 'enable', '1', '2018-03-05 15:50:13', '1', '2018-03-05 15:50:13');
INSERT INTO `sys_privilege` VALUES ('3pifap7wr8jl', 'role', '3phhov11jgn4', '2', '23', 'enable', '1', '2018-03-05 15:50:13', '1', '2018-03-05 15:50:13');

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `id` char(12) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `api` varchar(255) DEFAULT NULL,
  `method` varchar(10) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `pid` char(12) DEFAULT NULL,
  `descp` varchar(255) DEFAULT NULL,
  `icon_cls` varchar(255) DEFAULT NULL,
  `script` varchar(255) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `create_user` int(255) DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `update_user` char(12) DEFAULT NULL,
  `enable` tinyint(255) DEFAULT NULL,
  `leaf` tinyint(255) DEFAULT NULL,
  `orde` int(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
INSERT INTO `sys_resource` VALUES ('10', '组织架构', '/orgs', 'GET', '1', '7', '组织架构', 'fa fa-sitemap', '', '2016-11-06 01:39:12', '1', '2016-11-06 01:39:12', '1', '1', '1', '3');
INSERT INTO `sys_resource` VALUES ('11', '角色管理', '/roles', 'GET', '1', '7', '角色管理', 'fa fa-users', '', '2016-11-06 01:41:58', '1', '2016-11-06 01:41:58', '1', '1', '1', '4');
INSERT INTO `sys_resource` VALUES ('13', '权限管理', '/privileges', 'GET', '1', '7', '权限管理', 'fa fa-eye', '', '2016-11-06 01:50:09', '1', '2016-11-06 01:53:40', '1', '1', '1', '5');
INSERT INTO `sys_resource` VALUES ('22', '权威', '', null, '0', '21', '', '123', '123', '2016-11-20 22:13:51', '1', '2016-11-20 22:13:51', '1', '1', '1', '123');
INSERT INTO `sys_resource` VALUES ('23', '新增', '/api/users', 'POST', '2', '8', '', 'fa fa-user', '', '2017-01-02 21:30:14', '1', '2017-01-02 21:30:14', '1', '1', '1', '0');
INSERT INTO `sys_resource` VALUES ('24', '修改', '/api/users/\\d+', 'POST', '2', '8', '', 'fa fa-user', '', '2017-01-02 21:31:57', '1', '2017-01-02 21:38:25', '1', '1', '1', '1');
INSERT INTO `sys_resource` VALUES ('25', '删除', '/api/users/\\d+', 'DELETE', '2', '8', '', 'fa fa-user', '', '2017-01-02 21:32:07', '1', '2017-01-02 21:38:39', '1', '1', '1', '2');
INSERT INTO `sys_resource` VALUES ('26', '获取主菜单', '/api/users/\\d+/menus', 'POST', '3', '0', null, 'fa fa-list-ol', null, '2018-02-26 14:43:23', '1', null, null, '1', '1', '1');
INSERT INTO `sys_resource` VALUES ('27', '123123', '1111', 'GET', '3', '13', null, null, null, '2018-02-26 15:23:54', '1', null, null, '1', null, null);
INSERT INTO `sys_resource` VALUES ('5', '获取用户', '/api/user/\\d+/resources', 'GET', '3', '0', null, 'fa fa-user', null, '2018-02-07 17:15:19', '1', '2018-02-07 17:15:22', '1', '1', '1', '1');
INSERT INTO `sys_resource` VALUES ('7', '系统管理', '', null, '0', '0', '系统管理', 'fa fa-files-o', '', '2016-11-06 00:48:37', '1', '2016-11-06 00:48:37', '1', '1', '0', '1');
INSERT INTO `sys_resource` VALUES ('8', '用户管理', '/users', 'GET', '1', '7', '用户管理', 'fa fa-user', '', '2016-11-06 01:02:10', '1', '2016-11-06 01:02:10', '1', '1', '0', '2');
INSERT INTO `sys_resource` VALUES ('9', '资源管理', '/resources', 'GET', '1', '7', '资源管理', 'fa fa-list-ol', '', '2016-11-06 01:33:15', '1', '2016-11-06 01:33:15', '1', '1', '1', '2');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` char(12) NOT NULL,
  `name` varchar(20) NOT NULL,
  `code` varchar(100) NOT NULL,
  `descp` varchar(200) DEFAULT NULL,
  `create_user` char(12) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `update_user` char(12) DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `enable` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_key` (`name`,`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('19', '测试113333', '3333111233', '3', '1', '2018-02-08 17:22:19', '1', '2018-03-08 14:31:16', '1');
INSERT INTO `sys_role` VALUES ('3psxi8d974lc', '311233', '3123123', null, '1', '2018-03-08 14:29:59', '1', '2018-03-08 14:29:59', '1');
INSERT INTO `sys_role` VALUES ('4', '系统管理员', '1', '系统管理员', null, null, '1', '2016-09-24 01:43:10', '1');
INSERT INTO `sys_role` VALUES ('5', '高级用户', '2', '高级用户', null, null, '1', '2016-09-24 01:43:13', '1');
INSERT INTO `sys_role` VALUES ('6', '普通用户', '3', '普通用户', null, null, '1', '2016-09-24 01:43:17', '1');
INSERT INTO `sys_role` VALUES ('7', '测试员', '4', '测试员', null, null, '1', '2016-09-24 01:43:20', '1');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` char(12) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(32) NOT NULL,
  `nickname` varchar(20) DEFAULT NULL,
  `age` char(12) DEFAULT NULL,
  `birthday` date NOT NULL,
  `sex` varchar(4) DEFAULT NULL,
  `avatar` varchar(50) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `org_id` char(12) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `create_user` char(12) DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  `update_user` char(12) DEFAULT NULL,
  `enable` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_unique` (`username`),
  KEY `user_org` (`org_id`),
  CONSTRAINT `user_org` FOREIGN KEY (`org_id`) REFERENCES `sys_org` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '1', '2cfd4560539f887a5e420412b370b361', '管理员', '4', '2016-06-29', '男', null, '123@1.com', '123123', null, '2016-09-16 01:28:52', '11', '2016-09-20 01:28:56', '33', '1');
INSERT INTO `sys_user` VALUES ('11', 'liutao', '4af56b53ccce830e6566022fab445b8c', '刘涛', '0', '1988-04-22', '男', null, '11@qq.com', '1111111', null, null, null, '2016-09-18 02:28:51', '1', '1');
INSERT INTO `sys_user` VALUES ('2', '2', 'de94903812cefa17dc6b0a5955680ac1', '啊啦雷', '8', '2014-12-01', '女', null, 'wer@qqq.com', '(123) 123-1231', null, null, null, null, null, '1');
INSERT INTO `sys_user` VALUES ('31', 'qinzeyu', '76068304ad13dbd7526d837e2a74735d', '秦133', '0', '1989-11-19', '保密', null, '22@wq.com', '110111', null, null, null, null, null, '1');
INSERT INTO `sys_user` VALUES ('33', 'admin1', 'b6fad01be4bb2c981e7802414fbaa4fe', '管理员1', null, '2018-02-08', '男', null, '1@1q.c', '1233231', null, '2018-02-08 17:03:42', '1', null, null, '1');
INSERT INTO `sys_user` VALUES ('35', 'admin2', '123321', '11233311111', null, '2018-03-08', '男', null, '11@qq.com', '123321', null, '2018-03-02 13:39:31', '1', '2018-03-08 14:33:32', '1', '1');

-- ----------------------------
-- Table structure for sys_user_org
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_org`;
CREATE TABLE `sys_user_org` (
  `id` char(12) NOT NULL,
  `org_id` char(12) DEFAULT NULL,
  `user_id` char(12) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_aundm63jovvvcbdti8xp3rr9b` (`org_id`),
  KEY `FK_s60ynuecdfjaitv44ct672k8r` (`user_id`),
  CONSTRAINT `FK_aundm63jovvvcbdti8xp3rr9b` FOREIGN KEY (`org_id`) REFERENCES `sys_org` (`id`),
  CONSTRAINT `FK_s60ynuecdfjaitv44ct672k8r` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_org
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` char(12) NOT NULL,
  `role_id` char(12) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FK_kjp9c6hki8a1p70x44bwqex2v` (`role_id`),
  KEY `FK_akj61lp0wul5h73yq0xrq89cq` (`user_id`),
  CONSTRAINT `role_user_id` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `user_role_Id` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '4');
INSERT INTO `sys_user_role` VALUES ('1', '6');
INSERT INTO `sys_user_role` VALUES ('2', '6');
INSERT INTO `sys_user_role` VALUES ('33', '6');
