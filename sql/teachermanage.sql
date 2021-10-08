/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : localhost:3306
 Source Schema         : teachermanage

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 08/10/2021 10:32:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '',
  `status` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '0正常 1删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '院系表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES (1, '信息工程学院', 0);
INSERT INTO `department` VALUES (2, '机电学院', 0);
INSERT INTO `department` VALUES (3, '生命科技学院', 0);
INSERT INTO `department` VALUES (4, '动物科技学院', 0);
INSERT INTO `department` VALUES (5, '资源与环境学院', 0);
INSERT INTO `department` VALUES (6, '化学化工学院', 0);
INSERT INTO `department` VALUES (7, '教育科技学院', 0);
INSERT INTO `department` VALUES (8, '服装学院', 0);
INSERT INTO `department` VALUES (9, '外国语学院', 0);
INSERT INTO `department` VALUES (10, '经济与管理学院', 0);
INSERT INTO `department` VALUES (11, '食品学院', 0);
INSERT INTO `department` VALUES (12, ' 园艺园林学院', 0);
INSERT INTO `department` VALUES (14, '文法学院', 0);
INSERT INTO `department` VALUES (15, '艺术学院', 0);
INSERT INTO `department` VALUES (16, '数学科学学院', 0);
INSERT INTO `department` VALUES (17, '体育学院', 0);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '姓名',
  `account` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '教职工号',
  `sex` tinyint NOT NULL DEFAULT 0 COMMENT '性别，0男;1女',
  `age` int NOT NULL DEFAULT 0 COMMENT '年龄',
  `marry` tinyint NOT NULL DEFAULT 0 COMMENT '是否婚配，1是;0不是',
  `wechat` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '微信号',
  `phone` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '手机号码',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '密码',
  `dep_id` int NOT NULL DEFAULT 0 COMMENT '院系id',
  `type` tinyint UNSIGNED NOT NULL DEFAULT 0 COMMENT '0未审批；1普通教职工；2管理员；3超级管理员',
  `status` int NOT NULL DEFAULT 0 COMMENT '0正常；1删除',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '教职工用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '小黑', '20171544103', 0, 30, 1, 'wechat1234511', '4566987433', 'E1:0A:DC:39:49:BA:59:AB:BE:56:E0:57:F2:0F:88:3E', 12, 3, 0, '2020-07-07 16:35:01', '2020-07-24 11:31:42');
INSERT INTO `user` VALUES (2, '小兰', '2017141414', 0, 30, 0, 'wechat489655', '6987412365', 'E1:0A:DC:39:49:BA:59:AB:BE:56:E0:57:F2:0F:88:3E', 2, 2, 0, '2020-07-23 20:32:39', '2020-07-24 16:23:04');
INSERT INTO `user` VALUES (14, '小白', '20171524221', 0, 0, 0, 'wechat4789', '4125252', 'E1:0A:DC:39:49:BA:59:AB:BE:56:E0:57:F2:0F:88:3E', 1, 1, 0, '2020-07-23 20:47:09', '2020-07-24 11:32:59');
INSERT INTO `user` VALUES (15, '小绿', '20171523210', 0, 0, 0, 'wechat123', '36363', 'E1:0A:DC:39:49:BA:59:AB:BE:56:E0:57:F2:0F:88:3E', 4, 1, 0, '2020-07-23 20:47:44', '2020-07-24 14:58:34');
INSERT INTO `user` VALUES (16, '小航', '20181332450', 0, 0, 0, 'wechat456', '74747', 'E1:0A:DC:39:49:BA:59:AB:BE:56:E0:57:F2:0F:88:3E', 3, 1, 0, '2020-07-23 20:56:12', '2020-07-24 11:23:36');
INSERT INTO `user` VALUES (17, '小紫', '1111111111', 0, 0, 0, 'wechat963', '12121212', 'E1:0A:DC:39:49:BA:59:AB:BE:56:E0:57:F2:0F:88:3E', 1, 1, 1, '2020-07-23 23:00:47', '2020-07-24 14:58:37');
INSERT INTO `user` VALUES (24, '勇士', '20171544333', 1, 20, 1, '123456', '321654', 'E1:0A:DC:39:49:BA:59:AB:BE:56:E0:57:F2:0F:88:3E', 1, 1, 0, '2020-07-24 16:20:21', '2020-07-24 16:21:37');
INSERT INTO `user` VALUES (25, 'string', '201715555', 0, 0, 0, 'string', 'string', 'B4:5C:FF:E0:84:DD:3D:20:D9:28:BE:E8:5E:7B:0F:21', 1, 0, 0, '2020-07-24 22:16:21', NULL);

SET FOREIGN_KEY_CHECKS = 1;
