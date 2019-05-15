/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost:3306
 Source Schema         : springboot-demo

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 15/05/2019 11:41:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` varchar(255) DEFAULT NULL COMMENT '角色code',
  `name` varchar(32) DEFAULT NULL COMMENT '角色名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES (1, 'ROLE_ADMIN', 'ROLE_ADMIN');
INSERT INTO `sys_role` VALUES (2, 'ROLE_ADMIN', 'ROLE_USER');
COMMIT;

-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_code` varchar(32) DEFAULT NULL COMMENT '角色code',
  `username` varchar(32) DEFAULT NULL COMMENT '用户名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_user` VALUES (1, 'ROLE_ADMIN', 'admin');
INSERT INTO `sys_role_user` VALUES (2, 'ROLE_USER', 'user');
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `password` varchar(32) DEFAULT NULL COMMENT '密码',
  `username` varchar(32) DEFAULT NULL COMMENT '用户名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES (1, 'admin', 'admin');
INSERT INTO `sys_user` VALUES (2, 'user', 'user');
COMMIT;

-- ----------------------------
-- Table structure for yh_waybill
-- ----------------------------
DROP TABLE IF EXISTS `yh_waybill`;
CREATE TABLE `yh_waybill` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `bill_code` varchar(32) NOT NULL COMMENT '运单号',
  `carrier_email` varchar(128) DEFAULT NULL COMMENT '承运人邮箱',
  `carrier_name` varchar(32) DEFAULT NULL COMMENT '承运人姓名',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=80001 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of yh_waybill
-- ----------------------------
BEGIN;
INSERT INTO `yh_waybill` VALUES (1, 'wb0', '0@qq.com', '王刚0', '2019-05-13 16:15:35');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
