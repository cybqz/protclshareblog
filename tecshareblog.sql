/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : localhost:3306
 Source Schema         : tecshareblog

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 30/12/2019 10:41:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for chapter
-- ----------------------------
DROP TABLE IF EXISTS `chapter`;
CREATE TABLE `chapter`  (
  `chapter_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `teclearning_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '关联技术学习',
  `chapter_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标题',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `content` varchar(10240) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '内容',
  `chapter_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片',
  `chapter_create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`chapter_id`) USING BTREE,
  INDEX `fk_teclearning_id`(`teclearning_id`) USING BTREE,
  CONSTRAINT `fk_teclearning_id` FOREIGN KEY (`teclearning_id`) REFERENCES `teclearning` (`teclearning_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of chapter
-- ----------------------------
INSERT INTO `chapter` VALUES ('19717f76-964b-4eb8-8467-bed00f894a3c', '9ea908d3-40ea-4571-b3d8-815bbdd70c9c', 'B', 'bbbbbbbbbbbb', '<p>bbbbbbbbbbbbbbbbbbbb</p>', NULL, '2019-12-16 13:02:08');
INSERT INTO `chapter` VALUES ('63ebfbc6-9245-4848-8625-aa1e876d9b18', '3ecb4726-4493-4039-b968-a9d0c305251e', 'cccccccccc', 'cccccccccccccc', '<p>cccccccccccccccccc</p>', NULL, '2019-12-16 13:56:08');
INSERT INTO `chapter` VALUES ('855f1590-d842-43e8-a24f-3e39982cd990', '9ea908d3-40ea-4571-b3d8-815bbdd70c9c', 'A', 'A', '<p>aaaaaaaaaaaaaaa</p>', NULL, '2019-12-16 13:02:08');
INSERT INTO `chapter` VALUES ('993bbaf6-9d3b-404b-a09c-fa69552e3c81', '9ea908d3-40ea-4571-b3d8-815bbdd70c9c', 'C', 'ccccccccccccccc', '<p>ccccccccccccccc</p><table border=\"0\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\"><tbody><tr><th>&nbsp;</th><th>&nbsp;</th><th>&nbsp;</th><th>&nbsp;</th><th>&nbsp;</th></tr><tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr><tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr></tbody></table><pre><code>this.blog.chapterList.push(chaptermap)<br>                this.blogChapter = {};<br>                this.editor.txt.html(\'\');<br>                this.AddChapterResult = true;</code></pre><p><br></p>', NULL, '2019-12-16 13:02:08');
INSERT INTO `chapter` VALUES ('f9c519dd-7fe1-487f-9d75-3d82b4e01de0', '3ecb4726-4493-4039-b968-a9d0c305251e', 'A', 'aaaaaaaaaaa', '<p>aaaaaaa</p>', NULL, '2019-12-16 13:56:08');
INSERT INTO `chapter` VALUES ('ff6f180f-c2e0-4a4a-914f-a95ba181fb69', '3ecb4726-4493-4039-b968-a9d0c305251e', 'B', 'bbbbbbbbbbbbb', '<p>bbbbbbbbbbbbbbb</p>', NULL, '2019-12-16 13:56:08');

-- ----------------------------
-- Table structure for parames
-- ----------------------------
DROP TABLE IF EXISTS `parames`;
CREATE TABLE `parames`  (
  `id` varchar(38) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '值',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `id` varchar(38) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'name',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` varchar(38) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'name',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('a00b4b4b-842c-4e9f-815d-f9d7fd053091', 'admin', '管理员');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`  (
  `id` varchar(38) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `role_id` varchar(38) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关联角色',
  `permission_id` varchar(38) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关联权限',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `role_id`(`role_id`) USING BTREE,
  INDEX `permission_id`(`permission_id`) USING BTREE,
  CONSTRAINT `permission_id` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for teclearning
-- ----------------------------
DROP TABLE IF EXISTS `teclearning`;
CREATE TABLE `teclearning`  (
  `teclearning_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `author` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '作者',
  `teclearning_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标题',
  `category` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '类别',
  `tag` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标签',
  `preface` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '前言',
  `teclearning_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片',
  `teclearning_create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`teclearning_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teclearning
-- ----------------------------
INSERT INTO `teclearning` VALUES ('2d439b77-0066-45f0-88b9-46f36c44a77b', 'author', 'ssss', 'Recat', 'ssss', 'sssss', NULL, '2019-12-30 10:38:14');
INSERT INTO `teclearning` VALUES ('382d2a41-8188-41d9-a0f3-6dd7a90443e4', 'author', 'ssaadda', 'iview', 'asad', 'asasad', NULL, '2019-12-30 02:16:32');
INSERT INTO `teclearning` VALUES ('3ecb4726-4493-4039-b968-a9d0c305251e', 'author', 'B', 'test', 'ssss', 'ssssss', NULL, '2019-12-16 13:56:08');
INSERT INTO `teclearning` VALUES ('4b2739c4-7e49-41ab-bbde-c4aaccf8938b', 'author', 'cccggg', 'Recat', 'ccc', 'cccc', NULL, '2019-12-30 02:22:15');
INSERT INTO `teclearning` VALUES ('97cd2663-a664-4adc-af91-31779849c9ca', 'author', 'cccgggrr', 'Recat', 'ccc', 'cccc', NULL, '2019-12-30 10:25:43');
INSERT INTO `teclearning` VALUES ('9ca26f50-7c2d-401f-bf23-92894fff8d42', 'author', 'ccc', 'Recat', 'ccc', 'cccc', NULL, '2019-12-30 02:20:58');
INSERT INTO `teclearning` VALUES ('9ea908d3-40ea-4571-b3d8-815bbdd70c9c', 'author', 'A', 'test', 'aaa,bbbbb', 'qqqqqqqqq', NULL, '2019-12-16 13:02:08');
INSERT INTO `teclearning` VALUES ('e0322e4c-eb55-4038-9420-7de030a97a0f', 'author', 'ccccvvvv', 'Recat', 'vvvv', 'vvvv', NULL, '2019-12-30 10:28:10');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` varchar(38) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系方式',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `sex` int(1) NULL DEFAULT NULL COMMENT '性别',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '头像',
  `introduce` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '介绍',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('194a78dd-b57c-4438-b1db-09aa556d8aa3', '智智', 'cyb', '00c18a96dcd54576524d9d284a70b1ff', '123', '123', 1, 'address', 'headportrait/boy.png', 'xx');
INSERT INTO `user` VALUES ('1bd50bb2-2c95-4e60-94ea-a9f378986487', 'paopao', 'gfj', '64c8b1e43d8ba3115ab40bcea57f010b', '123', '123', 0, 'address', '/headportrait/gril.png', '我是障障');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` varchar(38) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `user_id` varchar(38) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关联用户',
  `role_id` varchar(38) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关联角色',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('235213f7-f69e-44c4-8d24-b5ef0e736a3a', '1bd50bb2-2c95-4e60-94ea-a9f378986487', 'a00b4b4b-842c-4e9f-815d-f9d7fd053091', 'gfj_admin');
INSERT INTO `user_role` VALUES ('5ec26942-e148-40fa-8ac7-8f18006d2b90', '194a78dd-b57c-4438-b1db-09aa556d8aa3', 'a00b4b4b-842c-4e9f-815d-f9d7fd053091', 'cyb_admin');

SET FOREIGN_KEY_CHECKS = 1;
