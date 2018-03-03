/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50716
Source Host           : localhost:3306
Source Database       : loeo

Target Server Type    : MYSQL
Target Server Version : 50716
File Encoding         : 65001

Date: 2018-03-03 17:09:33
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
-- Table structure for t_product
-- ----------------------------
DROP TABLE IF EXISTS `t_product`;
CREATE TABLE `t_product` (
  `id` char(12) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_product
-- ----------------------------
INSERT INTO `t_product` VALUES ('6', '产品', null);
INSERT INTO `t_product` VALUES ('8', '123123', null);
INSERT INTO `t_product` VALUES ('9', '123123', null);

-- ----------------------------
-- Table structure for t_product_type
-- ----------------------------
DROP TABLE IF EXISTS `t_product_type`;
CREATE TABLE `t_product_type` (
  `id` char(12) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `productId` char(12) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_odx8t7pmevvc0vb1e74yx6asq` (`productId`),
  CONSTRAINT `FK_odx8t7pmevvc0vb1e74yx6asq` FOREIGN KEY (`productId`) REFERENCES `t_product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_product_type
-- ----------------------------

-- ----------------------------
-- Table structure for t_sys_button
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_button`;
CREATE TABLE `t_sys_button` (
  `id` char(12) NOT NULL COMMENT 'id',
  `name` varchar(30) NOT NULL COMMENT '按钮名称',
  `cls` varchar(30) DEFAULT NULL COMMENT '按钮类型',
  `iconCls` varchar(255) DEFAULT NULL COMMENT '按钮图标',
  `script` varchar(255) DEFAULT NULL COMMENT '按钮脚本',
  `menuId` char(12) NOT NULL COMMENT '菜单id',
  `orde` int(255) NOT NULL COMMENT '排序',
  `enable` tinyint(1) DEFAULT NULL COMMENT '按钮状态',
  `createUser` int(255) DEFAULT NULL,
  `createDt` datetime DEFAULT NULL,
  `updateUser` char(12) DEFAULT NULL,
  `updateDt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `btn_menu_id` (`menuId`),
  CONSTRAINT `btn_menu_id` FOREIGN KEY (`menuId`) REFERENCES `t_sys_menu` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_button
-- ----------------------------
INSERT INTO `t_sys_button` VALUES ('1', '权威', null, '11', '22', '14', '32', '1', '1', '2016-09-25 20:03:48', '1', '2016-09-25 20:03:48');
INSERT INTO `t_sys_button` VALUES ('2', '权威', '111', '11', '22', '14', '32', '1', '1', '2016-09-25 20:05:13', '1', '2016-09-25 21:03:25');

-- ----------------------------
-- Table structure for t_sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_menu`;
CREATE TABLE `t_sys_menu` (
  `id` char(12) NOT NULL,
  `name` varchar(30) NOT NULL COMMENT '菜单名称',
  `iconCls` varchar(30) DEFAULT NULL COMMENT '菜单图标',
  `link` varchar(255) DEFAULT NULL COMMENT '菜单url',
  `pid` char(12) DEFAULT NULL COMMENT '父ID',
  `isLeaf` tinyint(1) DEFAULT NULL COMMENT '是否叶子节点',
  `orde` int(255) DEFAULT NULL COMMENT '排序',
  `createDt` datetime DEFAULT NULL,
  `createUser` char(12) DEFAULT NULL,
  `updateDt` datetime DEFAULT NULL,
  `updateUser` char(12) DEFAULT NULL,
  `enable` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_menu
-- ----------------------------
INSERT INTO `t_sys_menu` VALUES ('1', '主菜单', null, null, '0', '0', '1', '2016-09-21 00:11:40', '1', '2016-09-21 00:11:47', '1', '1');
INSERT INTO `t_sys_menu` VALUES ('14', '我的', 'fa fa-dashboard', '3', '1', '0', '3', '2016-09-24 01:31:00', '1', '2016-09-27 01:57:19', '1', '1');
INSERT INTO `t_sys_menu` VALUES ('15', '111', '12', '1122', '14', '0', '11', '2016-10-08 00:31:38', '1', '2016-10-08 00:31:38', '1', '1');
INSERT INTO `t_sys_menu` VALUES ('17', '123', '123', '321', '15', '1', '123', '2016-10-08 00:34:38', '1', '2016-10-08 00:34:38', '1', '1');
INSERT INTO `t_sys_menu` VALUES ('18', '资源管理', 'fa fa-list-ol', '/resource/page.do', '5', '1', '1', '2016-11-05 13:18:58', '1', '2016-11-05 13:20:05', '1', '1');
INSERT INTO `t_sys_menu` VALUES ('5', '系统管理', 'fa fa-files-o', '1', '1', '0', '1', '2016-09-24 00:55:03', '1', '2016-09-27 01:57:03', '1', '1');
INSERT INTO `t_sys_menu` VALUES ('6', '用户管理', 'fa fa-user', 'user/page.do', '5', '1', '1', '2016-09-24 01:11:33', '1', '2016-09-27 01:54:45', '1', '1');
INSERT INTO `t_sys_menu` VALUES ('7', '组织架构', 'fa fa-sitemap', '/org/page.do', '5', '1', '2', '2016-09-24 01:13:28', '1', '2016-09-27 01:55:17', '1', '1');
INSERT INTO `t_sys_menu` VALUES ('8', '角色管理', 'fa fa-users', 'role/page.do', '5', '1', '3', '2016-09-24 01:14:54', '1', '2016-09-27 01:55:36', '1', '1');
INSERT INTO `t_sys_menu` VALUES ('9', '权限管理', 'fa fa-eye', 'privilege/page.do', '5', '1', '4', '2016-09-24 01:15:51', '1', '2016-10-02 23:19:32', '1', '1');

-- ----------------------------
-- Table structure for t_sys_org
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_org`;
CREATE TABLE `t_sys_org` (
  `id` char(12) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `pid` char(12) DEFAULT NULL,
  `descp` varchar(200) DEFAULT NULL,
  `leaf` varchar(10) DEFAULT NULL,
  `createDt` datetime DEFAULT NULL,
  `createUser` char(12) DEFAULT NULL,
  `updateDt` datetime DEFAULT NULL,
  `updateUser` char(12) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_fj4wmb1pc7b1act5qy87bjws9` (`pid`),
  KEY `FK_ptea98lnhphw5t1is8kkb061p` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_org
-- ----------------------------
INSERT INTO `t_sys_org` VALUES ('16', '开发部', '0', '开发部', 'false', null, null, null, null);
INSERT INTO `t_sys_org` VALUES ('2', '人事部', '0', '人事部', 'true', null, null, null, null);
INSERT INTO `t_sys_org` VALUES ('27', '软件部', '16', '描述', 'true', null, null, null, null);
INSERT INTO `t_sys_org` VALUES ('28', '硬件部', '16', '2', 'true', null, null, null, null);
INSERT INTO `t_sys_org` VALUES ('29', '测试部', '0', '描述', 'false', null, null, null, null);
INSERT INTO `t_sys_org` VALUES ('30', '测试2组', '29', '描述', 'true', null, null, null, null);

-- ----------------------------
-- Table structure for t_sys_privilege
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_privilege`;
CREATE TABLE `t_sys_privilege` (
  `id` char(12) NOT NULL,
  `master` varchar(20) DEFAULT NULL,
  `masterValue` varchar(20) DEFAULT NULL,
  `access` varchar(20) DEFAULT NULL,
  `accessValue` varchar(20) DEFAULT NULL,
  `operation` varchar(20) DEFAULT NULL,
  `createUser` char(12) DEFAULT NULL,
  `createDt` datetime DEFAULT NULL,
  `updateUser` char(12) DEFAULT NULL,
  `updateDt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_privilege
-- ----------------------------
INSERT INTO `t_sys_privilege` VALUES ('202', 'user', '2', '0', '7', 'enable', '1', '2016-11-27 13:56:57', '1', '2016-11-27 13:56:57');
INSERT INTO `t_sys_privilege` VALUES ('203', 'user', '2', '1', '10', 'enable', '1', '2016-11-27 13:56:57', '1', '2016-11-27 13:56:57');
INSERT INTO `t_sys_privilege` VALUES ('204', 'user', '2', '1', '11', 'enable', '1', '2016-11-27 13:56:57', '1', '2016-11-27 13:56:57');
INSERT INTO `t_sys_privilege` VALUES ('205', 'user', '2', '1', '13', 'enable', '1', '2016-11-27 13:56:57', '1', '2016-11-27 13:56:57');
INSERT INTO `t_sys_privilege` VALUES ('213', 'user', '1', '2', '23', 'enable', '1', '2017-01-02 21:32:46', '1', '2017-01-02 21:32:46');
INSERT INTO `t_sys_privilege` VALUES ('214', 'user', '1', '1', '8', 'enable', '1', '2017-01-02 21:32:46', '1', '2017-01-02 21:32:46');
INSERT INTO `t_sys_privilege` VALUES ('215', 'user', '1', '0', '7', 'enable', '1', '2017-01-02 21:32:46', '1', '2017-01-02 21:32:46');
INSERT INTO `t_sys_privilege` VALUES ('216', 'user', '1', '1', '9', 'enable', '1', '2017-01-02 21:32:46', '1', '2017-01-02 21:32:46');
INSERT INTO `t_sys_privilege` VALUES ('217', 'user', '1', '1', '10', 'enable', '1', '2017-01-02 21:32:46', '1', '2017-01-02 21:32:46');
INSERT INTO `t_sys_privilege` VALUES ('218', 'user', '1', '1', '11', 'enable', '1', '2017-01-02 21:32:46', '1', '2017-01-02 21:32:46');
INSERT INTO `t_sys_privilege` VALUES ('219', 'user', '1', '1', '13', 'enable', '1', '2017-01-02 21:32:46', '1', '2017-01-02 21:32:46');
INSERT INTO `t_sys_privilege` VALUES ('220', 'user', '1', '2', '25', 'enable', '1', '2017-01-02 21:32:46', '1', '2017-01-02 21:32:46');
INSERT INTO `t_sys_privilege` VALUES ('221', 'user', '1', '2', '24', 'enable', '1', '2017-01-02 21:32:46', '1', '2017-01-02 21:32:46');
INSERT INTO `t_sys_privilege` VALUES ('222', 'role', '4', '0', '7', 'enable', '1', '2018-02-07 10:33:05', '1', '2018-02-07 10:33:05');
INSERT INTO `t_sys_privilege` VALUES ('223', 'role', '4', '1', '8', 'enable', '1', '2018-02-07 10:33:05', '1', '2018-02-07 10:33:05');
INSERT INTO `t_sys_privilege` VALUES ('224', 'role', '4', '2', '23', 'enable', '1', '2018-02-07 10:33:05', '1', '2018-02-07 10:33:05');
INSERT INTO `t_sys_privilege` VALUES ('225', 'role', '4', '2', '24', 'enable', '1', '2018-02-07 10:33:05', '1', '2018-02-07 10:33:05');
INSERT INTO `t_sys_privilege` VALUES ('226', 'role', '4', '2', '25', 'enable', '1', '2018-02-07 10:33:05', '1', '2018-02-07 10:33:05');
INSERT INTO `t_sys_privilege` VALUES ('227', 'role', '4', '1', '9', 'enable', '1', '2018-02-07 10:33:05', '1', '2018-02-07 10:33:05');
INSERT INTO `t_sys_privilege` VALUES ('228', 'role', '4', '1', '10', 'enable', '1', '2018-02-07 10:33:05', '1', '2018-02-07 10:33:05');
INSERT INTO `t_sys_privilege` VALUES ('229', 'role', '4', '1', '11', 'enable', '1', '2018-02-07 10:33:05', '1', '2018-02-07 10:33:05');
INSERT INTO `t_sys_privilege` VALUES ('230', 'role', '4', '1', '13', 'enable', '1', '2018-02-07 10:33:05', '1', '2018-02-07 10:33:05');
INSERT INTO `t_sys_privilege` VALUES ('231', 'role', '5', '0', '7', 'enable', '2', '2018-02-07 10:40:01', '2', '2018-02-07 10:40:01');
INSERT INTO `t_sys_privilege` VALUES ('232', 'role', '5', '1', '9', 'enable', '2', '2018-02-07 10:40:01', '2', '2018-02-07 10:40:01');
INSERT INTO `t_sys_privilege` VALUES ('233', 'role', '5', '1', '10', 'enable', '2', '2018-02-07 10:40:01', '2', '2018-02-07 10:40:01');
INSERT INTO `t_sys_privilege` VALUES ('234', 'role', '5', '1', '11', 'enable', '2', '2018-02-07 10:40:01', '2', '2018-02-07 10:40:01');
INSERT INTO `t_sys_privilege` VALUES ('235', 'role', '5', '1', '8', 'enable', '2', '2018-02-07 10:40:01', '2', '2018-02-07 10:40:01');
INSERT INTO `t_sys_privilege` VALUES ('236', 'role', '5', '2', '23', 'enable', '2', '2018-02-07 10:40:01', '2', '2018-02-07 10:40:01');
INSERT INTO `t_sys_privilege` VALUES ('237', 'role', '5', '2', '24', 'enable', '2', '2018-02-07 10:40:01', '2', '2018-02-07 10:40:01');
INSERT INTO `t_sys_privilege` VALUES ('238', 'role', '5', '2', '25', 'enable', '2', '2018-02-07 10:40:01', '2', '2018-02-07 10:40:01');
INSERT INTO `t_sys_privilege` VALUES ('239', 'role', '4', '1', '5', 'enable', '1', '2018-02-07 17:16:15', '1', '2018-02-07 17:16:19');
INSERT INTO `t_sys_privilege` VALUES ('294', 'role', '19', '1', '5', 'enable', '1', '2018-02-09 14:58:15', '1', '2018-02-09 14:58:15');
INSERT INTO `t_sys_privilege` VALUES ('324', 'role', '6', '1', '8', 'enable', '1', '2018-03-03 14:40:59', '1', '2018-03-03 14:40:59');
INSERT INTO `t_sys_privilege` VALUES ('325', 'role', '6', '0', '7', 'enable', '1', '2018-03-03 14:40:59', '1', '2018-03-03 14:40:59');
INSERT INTO `t_sys_privilege` VALUES ('326', 'role', '6', '3', '26', 'enable', '1', '2018-03-03 14:40:59', '1', '2018-03-03 14:40:59');
INSERT INTO `t_sys_privilege` VALUES ('327', 'role', '6', '2', '24', 'enable', '1', '2018-03-03 14:40:59', '1', '2018-03-03 14:40:59');

-- ----------------------------
-- Table structure for t_sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_resource`;
CREATE TABLE `t_sys_resource` (
  `id` char(12) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `api` varchar(255) DEFAULT NULL,
  `method` varchar(10) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `pid` char(12) DEFAULT NULL,
  `descp` varchar(255) DEFAULT NULL,
  `iconCls` varchar(255) DEFAULT NULL,
  `script` varchar(255) DEFAULT NULL,
  `createDt` datetime DEFAULT NULL,
  `createUser` int(255) DEFAULT NULL,
  `updateDt` datetime DEFAULT NULL,
  `updateUser` char(12) DEFAULT NULL,
  `enable` tinyint(255) DEFAULT NULL,
  `isLeaf` tinyint(255) DEFAULT NULL,
  `orde` int(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_resource
-- ----------------------------
INSERT INTO `t_sys_resource` VALUES ('10', '组织架构', '/orgs', 'GET', '1', '7', '组织架构', 'fa fa-sitemap', '', '2016-11-06 01:39:12', '1', '2016-11-06 01:39:12', '1', '1', '1', '3');
INSERT INTO `t_sys_resource` VALUES ('11', '角色管理', '/roles', 'GET', '1', '7', '角色管理', 'fa fa-users', '', '2016-11-06 01:41:58', '1', '2016-11-06 01:41:58', '1', '1', '1', '4');
INSERT INTO `t_sys_resource` VALUES ('13', '权限管理', '/privileges', 'GET', '1', '7', '权限管理', 'fa fa-eye', '', '2016-11-06 01:50:09', '1', '2016-11-06 01:53:40', '1', '1', '1', '5');
INSERT INTO `t_sys_resource` VALUES ('22', '权威', '', null, '0', '21', '', '123', '123', '2016-11-20 22:13:51', '1', '2016-11-20 22:13:51', '1', '1', '1', '123');
INSERT INTO `t_sys_resource` VALUES ('23', '新增', '/api/users', 'POST', '2', '8', '', 'fa fa-user', '', '2017-01-02 21:30:14', '1', '2017-01-02 21:30:14', '1', '1', '1', '0');
INSERT INTO `t_sys_resource` VALUES ('24', '修改', '/api/users/\\d+', 'POST', '2', '8', '', 'fa fa-user', '', '2017-01-02 21:31:57', '1', '2017-01-02 21:38:25', '1', '1', '1', '1');
INSERT INTO `t_sys_resource` VALUES ('25', '删除', '/api/users/\\d+', 'DELETE', '2', '8', '', 'fa fa-user', '', '2017-01-02 21:32:07', '1', '2017-01-02 21:38:39', '1', '1', '1', '2');
INSERT INTO `t_sys_resource` VALUES ('26', '获取主菜单', '/api/users/\\d+/menus', 'POST', '3', '0', null, 'fa fa-list-ol', null, '2018-02-26 14:43:23', '1', null, null, '1', '1', '1');
INSERT INTO `t_sys_resource` VALUES ('27', '123123', '1111', 'GET', '3', '13', null, null, null, '2018-02-26 15:23:54', '1', null, null, '1', null, null);
INSERT INTO `t_sys_resource` VALUES ('5', '获取用户', '/api/user/\\d+/resources', 'GET', '3', '0', null, 'fa fa-user', null, '2018-02-07 17:15:19', '1', '2018-02-07 17:15:22', '1', '1', '1', '1');
INSERT INTO `t_sys_resource` VALUES ('7', '系统管理', '', null, '0', '0', '系统管理', 'fa fa-files-o', '', '2016-11-06 00:48:37', '1', '2016-11-06 00:48:37', '1', '1', '0', '1');
INSERT INTO `t_sys_resource` VALUES ('8', '用户管理', '/users', 'GET', '1', '7', '用户管理', 'fa fa-user', '', '2016-11-06 01:02:10', '1', '2016-11-06 01:02:10', '1', '1', '0', '2');
INSERT INTO `t_sys_resource` VALUES ('9', '资源管理', '/resources', 'GET', '1', '7', '资源管理', 'fa fa-list-ol', '', '2016-11-06 01:33:15', '1', '2016-11-06 01:33:15', '1', '1', '1', '2');

-- ----------------------------
-- Table structure for t_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role`;
CREATE TABLE `t_sys_role` (
  `id` char(12) NOT NULL,
  `name` varchar(20) NOT NULL,
  `code` varchar(100) NOT NULL,
  `descp` varchar(200) DEFAULT NULL,
  `createUser` char(12) DEFAULT NULL,
  `createDt` datetime DEFAULT NULL,
  `updateUser` char(12) DEFAULT NULL,
  `updateDt` datetime DEFAULT NULL,
  `enable` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_key` (`name`,`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_role
-- ----------------------------
INSERT INTO `t_sys_role` VALUES ('19', '测试', '333311', null, '1', '2018-02-08 17:22:19', null, null, '1');
INSERT INTO `t_sys_role` VALUES ('3pbh7mur11c0', '3333', '3333', null, null, null, null, null, '1');
INSERT INTO `t_sys_role` VALUES ('4', '系统管理员', '1', '系统管理员', null, null, '1', '2016-09-24 01:43:10', '1');
INSERT INTO `t_sys_role` VALUES ('5', '高级用户', '2', '高级用户', null, null, '1', '2016-09-24 01:43:13', '1');
INSERT INTO `t_sys_role` VALUES ('6', '普通用户', '3', '普通用户', null, null, '1', '2016-09-24 01:43:17', '1');
INSERT INTO `t_sys_role` VALUES ('7', '测试员', '4', '测试员', null, null, '1', '2016-09-24 01:43:20', '1');

-- ----------------------------
-- Table structure for t_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user`;
CREATE TABLE `t_sys_user` (
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
  `orgId` char(12) DEFAULT NULL,
  `createDt` datetime DEFAULT NULL,
  `createUser` char(12) DEFAULT NULL,
  `updateDt` datetime DEFAULT NULL,
  `updateUser` char(12) DEFAULT NULL,
  `enable` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_unique` (`username`),
  KEY `user_org` (`orgId`),
  CONSTRAINT `user_org` FOREIGN KEY (`orgId`) REFERENCES `t_sys_org` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_user
-- ----------------------------
INSERT INTO `t_sys_user` VALUES ('1', '1', '2cfd4560539f887a5e420412b370b361', '管理员', '4', '2016-06-29', '男', null, '123@1.com', '123123', null, '2016-09-16 01:28:52', '11', '2016-09-20 01:28:56', '33', '1');
INSERT INTO `t_sys_user` VALUES ('11', 'liutao', '4af56b53ccce830e6566022fab445b8c', '刘涛', '0', '1988-04-22', '男', null, '11@qq.com', '1111111', null, null, null, '2016-09-18 02:28:51', '1', '1');
INSERT INTO `t_sys_user` VALUES ('2', '2', 'de94903812cefa17dc6b0a5955680ac1', '啊啦雷', '8', '2014-12-01', '女', null, 'wer@qqq.com', '(123) 123-1231', null, null, null, null, null, '1');
INSERT INTO `t_sys_user` VALUES ('31', 'qinzeyu', '76068304ad13dbd7526d837e2a74735d', '秦133', '0', '1989-11-19', '保密', null, '22@wq.com', '110111', null, null, null, null, null, '1');
INSERT INTO `t_sys_user` VALUES ('33', 'admin1', 'b6fad01be4bb2c981e7802414fbaa4fe', '管理员1', null, '2018-02-08', '男', null, '1@1q.c', '1233231', null, '2018-02-08 17:03:42', '1', null, null, '1');
INSERT INTO `t_sys_user` VALUES ('35', 'admin2', '123321', '11233311', null, '2018-03-09', '男', null, '11@qq.com', '123321', null, '2018-03-02 13:39:31', '1', null, null, '1');

-- ----------------------------
-- Table structure for t_sys_user_org
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user_org`;
CREATE TABLE `t_sys_user_org` (
  `id` char(12) NOT NULL,
  `orgId` char(12) DEFAULT NULL,
  `userId` char(12) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_aundm63jovvvcbdti8xp3rr9b` (`orgId`),
  KEY `FK_s60ynuecdfjaitv44ct672k8r` (`userId`),
  CONSTRAINT `FK_aundm63jovvvcbdti8xp3rr9b` FOREIGN KEY (`orgId`) REFERENCES `t_sys_org` (`id`),
  CONSTRAINT `FK_s60ynuecdfjaitv44ct672k8r` FOREIGN KEY (`userId`) REFERENCES `t_sys_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_user_org
-- ----------------------------

-- ----------------------------
-- Table structure for t_sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user_role`;
CREATE TABLE `t_sys_user_role` (
  `userId` char(12) NOT NULL,
  `roleId` char(12) NOT NULL,
  PRIMARY KEY (`userId`,`roleId`),
  KEY `FK_kjp9c6hki8a1p70x44bwqex2v` (`roleId`),
  KEY `FK_akj61lp0wul5h73yq0xrq89cq` (`userId`),
  CONSTRAINT `role_user_id` FOREIGN KEY (`userId`) REFERENCES `t_sys_user` (`id`),
  CONSTRAINT `user_role_Id` FOREIGN KEY (`roleId`) REFERENCES `t_sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sys_user_role
-- ----------------------------
INSERT INTO `t_sys_user_role` VALUES ('1', '4');
INSERT INTO `t_sys_user_role` VALUES ('1', '6');
INSERT INTO `t_sys_user_role` VALUES ('2', '6');
INSERT INTO `t_sys_user_role` VALUES ('33', '6');
