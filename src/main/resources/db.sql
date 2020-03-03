/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : localhost:3306
 Source Schema         : user_control

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 03/03/2020 21:24:19
*/
CREATE SCHEMA `user_control` DEFAULT CHARACTER SET utf8mb4 ;
use `user_control`;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;


-- ----------------------------
-- Table structure for relation
-- ----------------------------
DROP TABLE IF EXISTS `relation`;
CREATE TABLE `relation` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `uesrname` varchar(64) NOT NULL COMMENT '用户名',
  `is_state` int(1) NOT NULL COMMENT '主子标识  o-父，1-子',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='主子关系表';

-- ----------------------------
-- Table structure for subuser
-- ----------------------------
DROP TABLE IF EXISTS `subuser`;
CREATE TABLE `subuser` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(64) NOT NULL COMMENT '用户名',
  `password` varchar(64) NOT NULL COMMENT '用户密码',
  `parent_id` int(16) NOT NULL COMMENT '父id',
  `login_state` int(1) DEFAULT NULL COMMENT '0-未登录，1-已登陆',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `status` int(11) NOT NULL COMMENT '用户状态 0:无效  1:有效',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='子用户表';

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `username` varchar(64) NOT NULL COMMENT '用户名',
  `password` varchar(64) NOT NULL COMMENT '用户密码',
  `sub_num` int(16) DEFAULT '0' COMMENT '子账号人数',
  `phone_number` varchar(32) NOT NULL COMMENT '手机号',
  `login_state` int(1) DEFAULT NULL COMMENT '登陆状态  0-未登录，1-已登陆',
  `status` int(11) NOT NULL COMMENT '用户状态 0:无效  1:有效',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户主表';

SET FOREIGN_KEY_CHECKS = 1;