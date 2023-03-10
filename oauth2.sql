/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : oauth2

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 10/03/2023 10:47:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for client
-- ----------------------------
DROP TABLE IF EXISTS `client`;
CREATE TABLE `client`  (
  `id` bigint NOT NULL,
  `client_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `client_secret` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NULL DEFAULT NULL,
  `scope` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NULL DEFAULT NULL,
  `redirect_uri` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NULL DEFAULT NULL,
  `grant_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `deleted` bit(1) NULL DEFAULT b'0',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of client
-- ----------------------------
INSERT INTO `client` VALUES (1, 'webapp-client', '$2a$10$IbHJBz5ZhWegkP8VZrVdDu6.iyYQvqcK/85SKKzLPePsIlB1qxwPu', 'webapp', 'http://webapp.mxzero.top:8100/login/oauth2/code/webapp-client-oidc', 'authorization_code', '2023-03-05 14:06:00', NULL, b'0');
INSERT INTO `client` VALUES (2, 'message-client', '$2a$10$IbHJBz5ZhWegkP8VZrVdDu6.iyYQvqcK/85SKKzLPePsIlB1qxwPu', 'message', 'http://message.mxzero.top:8200/login', 'authorization_code', '2023-03-05 17:21:49', NULL, b'0');
INSERT INTO `client` VALUES (3, 'resource-client', '$2a$10$IbHJBz5ZhWegkP8VZrVdDu6.iyYQvqcK/85SKKzLPePsIlB1qxwPu', 'resource', 'http://resource.mxzero.top:8500/login/oauth2/code/resource-client-oidc', 'authorization_code', '2023-03-05 17:24:28', NULL, b'0');

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member`  (
  `id` bigint NOT NULL COMMENT '??????ID',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NULL DEFAULT NULL COMMENT '?????????',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NULL DEFAULT NULL COMMENT '??????',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NULL DEFAULT NULL COMMENT '??????',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NULL DEFAULT NULL COMMENT '????????????',
  `real_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NULL DEFAULT NULL COMMENT '????????????',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_german2_ci NULL DEFAULT NULL COMMENT '??????',
  `create_time` datetime NULL DEFAULT NULL COMMENT '????????????',
  `update_time` datetime NULL DEFAULT NULL COMMENT '????????????',
  `deleted` bit(1) NULL DEFAULT b'0' COMMENT '????????????',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_username`(`username`, `deleted`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of member
-- ----------------------------
INSERT INTO `member` VALUES (1, 'user', '$2a$10$ATqd5OQzJ2QSYFLL3TccVOJ5cBPh4UH1VRfzFRLbH5ChjkKblLbp2', 'user@qq.com', '1008611', '????????????', '/default.jpg', '2023-03-05 15:23:50', NULL, b'0');
INSERT INTO `member` VALUES (1632711134926155777, 'zero', '$2a$10$dSaWbS1wxPi2MelgI6nfoeexbNl1MI8GTdzvJnmYxbnC9R83TDrDK', 'qianmeng6879@163.com', NULL, NULL, NULL, '2023-03-06 19:54:03', NULL, b'0');

SET FOREIGN_KEY_CHECKS = 1;
