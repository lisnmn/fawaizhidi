/*
 Navicat Premium Data Transfer

 Source Server         : 阿里云实例
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : 114.55.107.5:3306
 Source Schema         : fawaizhidi

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 05/01/2020 20:23:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for image
-- ----------------------------
DROP TABLE IF EXISTS `image`;
CREATE TABLE `image`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '图片id',
  `uploader` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '上传者',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '图片url',
  `thumb_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `upload_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上传时间',
  `rank` enum('safe','r15','r18') CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT 'safe' COMMENT '分级',
  `del` int(1) NOT NULL DEFAULT 0 COMMENT '删除标记（0：正常，1：删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '密码',
  `del` int(1) NOT NULL DEFAULT 0 COMMENT '删除标记（0：正常，1：删除）',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
