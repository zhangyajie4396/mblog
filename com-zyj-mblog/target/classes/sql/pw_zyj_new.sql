/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50548
Source Host           : localhost:3306
Source Database       : pw_zyj

Target Server Type    : MYSQL
Target Server Version : 50548
File Encoding         : 65001

Date: 2018-06-26 18:24:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for resource
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL COMMENT '资源名称',
  `url` varchar(50) DEFAULT NULL,
  `icon` varchar(100) DEFAULT NULL,
  `seq` int(11) DEFAULT NULL COMMENT '序号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of resource
-- ----------------------------
INSERT INTO `resource` VALUES ('2', null, '用户管理', '/admin/user/list.html', 'fa fa-user icon-xlarge', '1');
INSERT INTO `resource` VALUES ('3', null, '角色管理', '/admin/role/list.html', 'fa fa fa-registered icon-xlarge', '2');
INSERT INTO `resource` VALUES ('4', null, '菜单管理', '/html/role/list.html', 'fa fa-reorder icon-xlarge', '3');
INSERT INTO `resource` VALUES ('5', '2', '新增用户', null, null, '1');
INSERT INTO `resource` VALUES ('6', '2', '修改用户', null, null, '2');
INSERT INTO `resource` VALUES ('7', '3', '新增角色', null, null, '1');
INSERT INTO `resource` VALUES ('8', '3', '修改角色', null, null, '2');
INSERT INTO `resource` VALUES ('9', '3', '删除角色', null, null, '3');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(20) DEFAULT NULL COMMENT '角色编码',
  `name` varchar(20) DEFAULT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'ROLE_ADMIN', '管理员');
INSERT INTO `role` VALUES ('2', 'ROLE_USER', '普通用户');

-- ----------------------------
-- Table structure for role_resource
-- ----------------------------
DROP TABLE IF EXISTS `role_resource`;
CREATE TABLE `role_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `resource_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_resource
-- ----------------------------
INSERT INTO `role_resource` VALUES ('25', '1', '2');
INSERT INTO `role_resource` VALUES ('26', '1', '3');
INSERT INTO `role_resource` VALUES ('31', '2', '4');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT '' COMMENT '用户名',
  `password` varchar(100) DEFAULT NULL,
  `nickname` varchar(20) DEFAULT NULL COMMENT '昵称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `profile` varchar(255) DEFAULT NULL COMMENT '头像',
  `disabled` int(11) DEFAULT NULL COMMENT '是否禁用 1是 0否',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'root', 'root', '小张', '2018-06-26 15:15:03', null, '1');
INSERT INTO `user` VALUES ('2', 'sang', '', '小傻蛋', '2018-06-26 15:15:07', null, '1');
INSERT INTO `user` VALUES ('13', 'test1', '123456', 'test222', '2018-06-26 16:34:42', null, '0');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('12', '1', '1');
INSERT INTO `user_role` VALUES ('13', '1', '2');
INSERT INTO `user_role` VALUES ('29', '2', '2');
INSERT INTO `user_role` VALUES ('30', '2', '5');
INSERT INTO `user_role` VALUES ('32', '13', '2');
