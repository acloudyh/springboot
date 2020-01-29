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
-- Table structure for yh_waybill
-- ----------------------------
DROP TABLE IF EXISTS `yh_waybill`;
CREATE TABLE `yh_waybill`
(
    `id`            bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '主键',
    `bill_code`     varchar(32) NOT NULL COMMENT '运单号',
    `carrier_email` varchar(128) DEFAULT NULL COMMENT '承运人邮箱',
    `carrier_name`  varchar(32)  DEFAULT NULL COMMENT '承运人姓名',
    `created_time`  datetime     DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 80001
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Records of yh_waybill
-- ----------------------------
BEGIN;
INSERT INTO `yh_waybill`
VALUES (1, 'wb0', '0@qq.com', '王刚0', '2019-05-13 16:15:35');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
