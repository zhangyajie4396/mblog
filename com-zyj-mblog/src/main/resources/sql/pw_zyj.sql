/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50548
Source Host           : localhost:3306
Source Database       : pw_zyj

Target Server Type    : MYSQL
Target Server Version : 50548
File Encoding         : 65001

Date: 2018-08-08 14:02:10
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
  `permission` varchar(50) DEFAULT NULL COMMENT '权限',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of resource
-- ----------------------------
INSERT INTO `resource` VALUES ('2', null, '用户管理', '/admin/user/list.html', 'fa fa-user icon-xlarge', '1', 'user:list');
INSERT INTO `resource` VALUES ('3', null, '角色管理', '/admin/role/list.html', 'fa fa fa-registered icon-xlarge', '2', 'role:list');
INSERT INTO `resource` VALUES ('4', null, '菜单管理', '/admin/menu/list.html', 'fa fa-reorder icon-xlarge', '3', 'menu:list');
INSERT INTO `resource` VALUES ('5', '2', '新增用户', null, null, '1', 'user:add');
INSERT INTO `resource` VALUES ('6', '2', '修改用户', '', '1', '2', 'user:edit');
INSERT INTO `resource` VALUES ('7', '3', '新增角色', null, null, '1', 'role:add');
INSERT INTO `resource` VALUES ('8', '3', '修改角色', null, null, '2', 'role:edit');
INSERT INTO `resource` VALUES ('9', '3', '删除角色', null, null, '3', 'role:delete');
INSERT INTO `resource` VALUES ('13', '2', '删除用户', '', '', '3', 'user:delete');
INSERT INTO `resource` VALUES ('52', '4', '新增菜单', '', '', '1', 'menu:add');
INSERT INTO `resource` VALUES ('53', '4', '删除菜单', '', '', '2', 'menu:delete');
INSERT INTO `resource` VALUES ('54', null, '栏目管理', '/admin/group/list.html', 'fa fa-tags icon-xlarge', '4', 'group:list');
INSERT INTO `resource` VALUES ('55', '54', '新增栏目', '', '', '1', 'group:add');
INSERT INTO `resource` VALUES ('56', '54', '编辑栏目', '', '', '2', 'group:edit');
INSERT INTO `resource` VALUES ('57', '54', '删除栏目', '', '', '3', 'group:delete');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(20) DEFAULT NULL COMMENT '角色编码',
  `name` varchar(20) DEFAULT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_resource
-- ----------------------------
INSERT INTO `role_resource` VALUES ('64', '1', '2');
INSERT INTO `role_resource` VALUES ('65', '1', '5');
INSERT INTO `role_resource` VALUES ('66', '1', '6');
INSERT INTO `role_resource` VALUES ('67', '1', '13');
INSERT INTO `role_resource` VALUES ('68', '1', '3');
INSERT INTO `role_resource` VALUES ('69', '1', '7');
INSERT INTO `role_resource` VALUES ('70', '1', '8');
INSERT INTO `role_resource` VALUES ('71', '1', '9');
INSERT INTO `role_resource` VALUES ('72', '1', '4');
INSERT INTO `role_resource` VALUES ('73', '2', '3');
INSERT INTO `role_resource` VALUES ('74', '2', '7');
INSERT INTO `role_resource` VALUES ('75', '2', '8');
INSERT INTO `role_resource` VALUES ('76', '2', '9');
INSERT INTO `role_resource` VALUES ('77', '2', '4');

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'root', 'root', '小张', '2018-06-26 15:15:03', null, '1');
INSERT INTO `user` VALUES ('2', 'sang', '', '小傻蛋', '2018-06-26 15:15:07', null, '1');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('12', '1', '1');
INSERT INTO `user_role` VALUES ('13', '1', '2');
INSERT INTO `user_role` VALUES ('34', '2', '2');

-- ----------------------------
-- Table structure for z_group
-- ----------------------------
DROP TABLE IF EXISTS `z_group`;
CREATE TABLE `z_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(50) DEFAULT NULL COMMENT '编码',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `status` int(11) DEFAULT NULL COMMENT '状态1显示 0隐藏',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of z_group
-- ----------------------------
INSERT INTO `z_group` VALUES ('1', 'blog', '博文', '1');
INSERT INTO `z_group` VALUES ('2', 'web', 'WEB', '0');
INSERT INTO `z_group` VALUES ('4', 'tutorial', '博文', '1');
