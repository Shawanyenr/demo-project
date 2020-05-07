/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : springboot_shixun

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 07/05/2020 00:35:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `avatar` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username_UNIQUE`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (22, 'Gerald', 'Gerald', '123456', NULL, NULL);

-- ----------------------------
-- Table structure for blocklist
-- ----------------------------
DROP TABLE IF EXISTS `blocklist`;
CREATE TABLE `blocklist`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `uid` int(20) NOT NULL,
  `bid` int(20) NOT NULL,
  `time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blocklist
-- ----------------------------
INSERT INTO `blocklist` VALUES (4, 5, 7, '2020-05-04 22:16:11');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parentId` int(11) NULL DEFAULT 0,
  `uid` int(11) NOT NULL,
  `time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `pid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (1, 0, 1, '2020-04-19 12:10:43', '123', 16);
INSERT INTO `comment` VALUES (2, 1, 5, '2020-04-19 12:11:35', '555', 16);
INSERT INTO `comment` VALUES (3, 0, 5, '2020-04-19 12:12:03', '456', 19);
INSERT INTO `comment` VALUES (4, 1, 1, '2020-04-19 12:14:15', '789', 16);
INSERT INTO `comment` VALUES (5, 0, 5, '2020-04-19 14:28:07', 'abc', 16);
INSERT INTO `comment` VALUES (6, 0, 5, '2020-04-22 21:48:57', '嘻嘻', 16);
INSERT INTO `comment` VALUES (7, 0, 5, '2020-04-22 21:49:09', '嘻嘻', 16);
INSERT INTO `comment` VALUES (8, 0, 5, '2020-04-22 21:54:12', 'haha', 16);
INSERT INTO `comment` VALUES (9, 1, 5, '2020-04-22 21:54:26', '@m78 qwerty', 16);
INSERT INTO `comment` VALUES (10, 0, 5, '2020-04-22 22:14:37', 'hhhhhhh', 37);
INSERT INTO `comment` VALUES (11, 0, 5, '2020-04-22 22:20:02', 'asdfgyhu', 37);
INSERT INTO `comment` VALUES (12, 11, 5, '2020-04-22 22:21:42', '@pipi bnytdmsrzm aenhstr', 37);
INSERT INTO `comment` VALUES (13, 0, 6, '2020-04-22 23:24:27', '哇啊啊啊啊啊啊啊啊啊啊啊啊啊啊😁', 37);
INSERT INTO `comment` VALUES (14, 11, 6, '2020-04-22 23:26:34', '@pipi 噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢噢哦哦', 37);
INSERT INTO `comment` VALUES (15, 0, 5, '2020-04-30 15:26:24', '的想法v救护车v发', 36);
INSERT INTO `comment` VALUES (16, 15, 5, '2020-04-30 15:26:31', '@pipi 甲方提供i尽快', 36);
INSERT INTO `comment` VALUES (17, 0, 1, '2020-05-06 23:57:50', 'haerhae', 34);
INSERT INTO `comment` VALUES (18, 0, 1, '2020-05-07 00:00:32', 'vwev', 36);
INSERT INTO `comment` VALUES (19, 0, 1, '2020-05-07 00:03:29', 'cs', 36);
INSERT INTO `comment` VALUES (20, 0, 1, '2020-05-07 00:10:55', 'hhh', 36);
INSERT INTO `comment` VALUES (21, 0, 1, '2020-05-07 00:31:33', 'bwbearbneh', 35);
INSERT INTO `comment` VALUES (22, 0, 1, '2020-05-07 00:34:04', '嘿嘿', 36);

-- ----------------------------
-- Table structure for commentnote
-- ----------------------------
DROP TABLE IF EXISTS `commentnote`;
CREATE TABLE `commentnote`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `uid` int(20) NOT NULL,
  `isread` int(1) UNSIGNED NULL DEFAULT 0,
  `time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `pid` int(20) NULL DEFAULT NULL,
  `fromid` int(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of commentnote
-- ----------------------------
INSERT INTO `commentnote` VALUES (1, 5, 1, '2020-05-06 20:43:21', '测试', 19, 1);
INSERT INTO `commentnote` VALUES (2, 5, 1, '2020-05-06 23:57:50', 'haerhae', 34, 1);
INSERT INTO `commentnote` VALUES (3, 5, 1, '2020-05-07 00:03:29', 'cs', 36, 1);
INSERT INTO `commentnote` VALUES (4, 5, 1, '2020-05-07 00:10:55', 'hhh', 36, 1);
INSERT INTO `commentnote` VALUES (5, 5, 1, '2020-05-07 00:31:33', 'bwbearbneh', 35, 1);
INSERT INTO `commentnote` VALUES (6, 5, 1, '2020-05-07 00:34:04', '嘿嘿', 36, 1);

-- ----------------------------
-- Table structure for likenotify
-- ----------------------------
DROP TABLE IF EXISTS `likenotify`;
CREATE TABLE `likenotify`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `fromid` int(20) NOT NULL,
  `pid` int(20) NULL DEFAULT NULL,
  `isread` int(2) NULL DEFAULT 0,
  `time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `uid` int(20) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of likenotify
-- ----------------------------
INSERT INTO `likenotify` VALUES (1, 5, 19, 1, '2020-05-06 22:08:02', 1);
INSERT INTO `likenotify` VALUES (2, 1, 36, 1, '2020-05-06 23:49:53', 5);
INSERT INTO `likenotify` VALUES (3, 1, 35, 1, '2020-05-06 23:50:25', 5);
INSERT INTO `likenotify` VALUES (4, 1, 32, 1, '2020-05-06 23:52:54', 5);
INSERT INTO `likenotify` VALUES (5, 1, 34, 1, '2020-05-06 23:56:20', 5);
INSERT INTO `likenotify` VALUES (6, 1, 31, 1, '2020-05-07 00:11:09', 5);
INSERT INTO `likenotify` VALUES (7, 5, 23, 0, '2020-05-07 00:14:00', 1);

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sender` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `receiver` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `checked` int(2) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 103 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES (1, 'pipi', 'm78', '123', '2020-04-17 21:23:53', 1);
INSERT INTO `message` VALUES (2, 'pipi', 'm78', '12', '2020-04-17 21:33:37', 1);
INSERT INTO `message` VALUES (3, 'pipi', 'm78', '123erdswe', '2020-04-17 21:33:56', 1);
INSERT INTO `message` VALUES (4, 'm78', 'pipi', '123', '2020-04-17 21:40:01', 1);
INSERT INTO `message` VALUES (5, 'pipi', 'm78', 'qasdv', '2020-04-17 21:40:19', 1);
INSERT INTO `message` VALUES (6, 'pipi', 'm78', 'asvsd,nsbz', '2020-04-17 21:40:24', 1);
INSERT INTO `message` VALUES (7, 'pipi', 'm78', 'd', '2020-04-17 21:43:52', 1);
INSERT INTO `message` VALUES (8, 'pipi', 'm78', 'd', '2020-04-17 21:43:53', 1);
INSERT INTO `message` VALUES (9, 'pipi', 'm78', 'd', '2020-04-17 21:43:54', 1);
INSERT INTO `message` VALUES (10, 'pipi', 'm78', 'd', '2020-04-17 21:43:55', 1);
INSERT INTO `message` VALUES (11, 'pipi', 'm78', 'd', '2020-04-17 21:43:56', 1);
INSERT INTO `message` VALUES (12, 'm78', 'pipi', 'd', '2020-04-17 21:44:00', 1);
INSERT INTO `message` VALUES (13, 'm78', 'pipi', 'd', '2020-04-17 21:44:01', 1);
INSERT INTO `message` VALUES (14, 'm78', 'pipi', 'd', '2020-04-17 21:44:02', 1);
INSERT INTO `message` VALUES (15, 'm78', 'pipi', 'd', '2020-04-17 21:44:03', 1);
INSERT INTO `message` VALUES (16, 'm78', 'pipi', 'd', '2020-04-17 21:44:04', 1);
INSERT INTO `message` VALUES (17, 'm78', 'pipi', 'd', '2020-04-17 21:44:05', 1);
INSERT INTO `message` VALUES (18, 'm78', 'pipi', 'd', '2020-04-17 21:44:06', 1);
INSERT INTO `message` VALUES (19, 'm78', 'pipi', 'd', '2020-04-17 21:44:07', 1);
INSERT INTO `message` VALUES (20, 'm78', 'pipi', 'd', '2020-04-17 21:44:08', 1);
INSERT INTO `message` VALUES (21, 'm78', 'pipi', 'd', '2020-04-17 21:44:11', 1);
INSERT INTO `message` VALUES (22, 'pipi', 'm78', 'd', '2020-04-17 21:44:20', 1);
INSERT INTO `message` VALUES (23, 'pipi', 'm78', 'd', '2020-04-17 21:44:22', 1);
INSERT INTO `message` VALUES (24, 'pipi', 'm78', 'd', '2020-04-17 21:47:10', 1);
INSERT INTO `message` VALUES (25, 'pipi', 'm78', 'd', '2020-04-17 21:47:12', 1);
INSERT INTO `message` VALUES (26, 'pipi', 'm78', 'savW', '2020-04-17 21:47:51', 1);
INSERT INTO `message` VALUES (27, 'pipi', 'm78', '国标舞HRH是', '2020-04-17 21:47:56', 1);
INSERT INTO `message` VALUES (28, 'pipi', 'm78', '阿萨伟大的特色让对方光和热', '2020-04-17 21:48:04', 1);
INSERT INTO `message` VALUES (29, 'pipi', 'm78', 'bSRBerazdb', '2020-04-17 21:49:35', 1);
INSERT INTO `message` VALUES (30, 'pipi', 'm78', 'bheabfzdsbera', '2020-04-17 21:49:46', 1);
INSERT INTO `message` VALUES (31, 'm78', 'pipi', 'afVAWVDVW', '2020-04-17 21:57:24', 1);
INSERT INTO `message` VALUES (32, 'm78', 'pipi', 'szdsfghj', '2020-04-17 23:06:56', 1);
INSERT INTO `message` VALUES (33, 'pipi', 'm78', '123', '2020-04-17 23:09:17', 1);
INSERT INTO `message` VALUES (34, 'pipi', 'm78', 'gdfg', '2020-04-17 23:17:01', 1);
INSERT INTO `message` VALUES (35, 'm78', 'pipi', 'dntjrd', '2020-04-17 23:17:05', 1);
INSERT INTO `message` VALUES (36, 'pipi', 'm78', 'drtfhtdg', '2020-04-17 23:17:13', 1);
INSERT INTO `message` VALUES (37, 'm78', 'pipi', 'rhfdtgrtsgd', '2020-04-17 23:17:16', 1);
INSERT INTO `message` VALUES (38, 'm78', 'pipi', 'zsdfghj', '2020-04-17 23:21:58', 1);
INSERT INTO `message` VALUES (39, 'm78', 'pipi', 'qewaresrdtf', '2020-04-17 23:24:10', 1);
INSERT INTO `message` VALUES (40, 'pipi', 'm78', 'sdfgth', '2020-04-18 12:36:30', 1);
INSERT INTO `message` VALUES (41, 'pipi', 'm78', 'adsfdgfhcx wetrswrab 4H  GR3AW', '2020-04-18 12:56:36', 1);
INSERT INTO `message` VALUES (42, 'pipi', 'm78', '123', '2020-04-18 16:02:38', 1);
INSERT INTO `message` VALUES (43, 'pipi', 'm78', '123', '2020-04-18 16:07:02', 1);
INSERT INTO `message` VALUES (44, 'pipi', 'm78', 'asd', '2020-04-18 16:12:47', 1);
INSERT INTO `message` VALUES (45, 'm78', 'pipi', 'csVbnrydrtsersd', '2020-04-18 16:12:57', 1);
INSERT INTO `message` VALUES (46, 'pipi', 'm78', 'adsf', '2020-04-18 17:02:12', 1);
INSERT INTO `message` VALUES (47, 'm78', 'pipi', 'adsfdgr,nebrstbhfd', '2020-04-18 17:02:16', 1);
INSERT INTO `message` VALUES (48, 'm78', 'pipi', '123', '2020-04-18 17:46:14', 1);
INSERT INTO `message` VALUES (49, 'm78', 'pipi', '23而', '2020-04-18 17:46:40', 1);
INSERT INTO `message` VALUES (50, 'pipi', 'm78', 'sc', '2020-04-18 20:18:28', 1);
INSERT INTO `message` VALUES (51, 'm78', 'pipi', 'as', '2020-04-18 20:24:39', 1);
INSERT INTO `message` VALUES (52, 'm78', 'pipi', '干啥呢', '2020-04-18 20:25:00', 1);
INSERT INTO `message` VALUES (53, 'm78', 'pipi', '阿斯顿', '2020-04-18 20:39:31', 1);
INSERT INTO `message` VALUES (54, 'm78', 'pipi', '123', '2020-04-18 20:44:10', 1);
INSERT INTO `message` VALUES (55, 'm78', 'pipi', '的风格和', '2020-04-18 20:44:52', 1);
INSERT INTO `message` VALUES (56, 'm78', 'pipi', '现场v吧', '2020-04-18 20:45:01', 1);
INSERT INTO `message` VALUES (57, 'pipi', 'm78', '的风格', '2020-04-18 20:45:41', 1);
INSERT INTO `message` VALUES (58, 'pipi', 'm78', '的风格v不会', '2020-04-18 20:45:57', 1);
INSERT INTO `message` VALUES (59, 'pipi', 'm78', '123', '2020-04-18 22:11:00', 1);
INSERT INTO `message` VALUES (60, 'm78', 'pipi', 'jnhkl', '2020-04-18 22:11:28', 1);
INSERT INTO `message` VALUES (61, 'm78', 'pipi', '546', '2020-04-18 22:11:37', 1);
INSERT INTO `message` VALUES (62, 'm78', 'pipi', 'vhgj', '2020-04-18 22:15:49', 1);
INSERT INTO `message` VALUES (63, 'm78', 'pipi', 'cy  ul v', '2020-04-18 22:16:01', 1);
INSERT INTO `message` VALUES (64, 'm78', 'pipi', 'iukj', '2020-04-18 22:17:57', 1);
INSERT INTO `message` VALUES (65, 'm78', 'pipi', 'yulvh', '2020-04-18 22:18:13', 1);
INSERT INTO `message` VALUES (66, 'm78', 'pipi', '45632', '2020-04-18 22:30:20', 1);
INSERT INTO `message` VALUES (67, 'm78', 'pipi', 'jhkl', '2020-04-18 22:38:35', 1);
INSERT INTO `message` VALUES (68, 'm78', 'pipi', '12346', '2020-04-18 22:39:44', 1);
INSERT INTO `message` VALUES (69, 'm78', 'pipi', 'asdf', '2020-04-18 22:46:52', 1);
INSERT INTO `message` VALUES (70, 'm78', 'pipi', 'sdtf', '2020-04-18 22:47:54', 1);
INSERT INTO `message` VALUES (71, 'm78', 'pipi', '热天荣誉感和', '2020-04-18 22:49:15', 1);
INSERT INTO `message` VALUES (72, 'pipi', 'm78', 'hey', '2020-04-19 14:57:25', 1);
INSERT INTO `message` VALUES (73, 'm78', 'pipi', 'Hi', '2020-04-19 14:57:44', 1);
INSERT INTO `message` VALUES (74, 'm78', 'pipi', 'See u around', '2020-04-19 14:58:03', 1);
INSERT INTO `message` VALUES (75, 'm78', 'pipi', 'sdfc', '2020-04-20 17:23:27', 1);
INSERT INTO `message` VALUES (76, '2016b11015', 'pipi', 'heyyy', '2020-04-20 17:51:47', 1);
INSERT INTO `message` VALUES (77, 'm78', 'pipi', '嗨', '2020-04-20 19:12:47', 1);
INSERT INTO `message` VALUES (78, 'm78', 'pipi', 'F', '2020-04-20 19:56:43', 1);
INSERT INTO `message` VALUES (79, '2016b11015', 'pipi', 'asdf', '2020-04-21 16:52:31', 1);
INSERT INTO `message` VALUES (80, '2016b11015', 'pipi', 'asder', '2020-04-21 16:53:48', 1);
INSERT INTO `message` VALUES (81, '2016b11015', 'pipi', 'asd', '2020-04-21 17:54:27', 1);
INSERT INTO `message` VALUES (82, '2016b11015', 'pipi', '456', '2020-04-21 18:05:57', 1);
INSERT INTO `message` VALUES (83, '2016b11015', 'pipi', 'aswe', '2020-04-21 18:15:03', 1);
INSERT INTO `message` VALUES (84, '2016b11015', 'pipi', '111', '2020-04-21 18:23:49', 1);
INSERT INTO `message` VALUES (85, '2016b11015', 'pipi', '阿斯顿法国红酒看来', '2020-04-21 18:32:12', 1);
INSERT INTO `message` VALUES (86, '2016b11015', 'pipi', '先吃饭v', '2020-04-21 18:32:50', 1);
INSERT INTO `message` VALUES (87, '2016b11015', 'pipi', 'asd', '2020-04-21 18:37:08', 1);
INSERT INTO `message` VALUES (88, '2016b11015', 'pipi', 'qwert', '2020-04-21 18:38:15', 1);
INSERT INTO `message` VALUES (89, '2016b11015', 'pipi', 'awsedrf', '2020-04-21 18:43:05', 1);
INSERT INTO `message` VALUES (90, '2016b11015', 'pipi', 'xdcfgvh', '2020-04-21 18:57:46', 1);
INSERT INTO `message` VALUES (91, '2016b11015', 'pipi', 'dfgyhj', '2020-04-21 18:57:56', 1);
INSERT INTO `message` VALUES (92, '2016b11015', 'pipi', 'asd', '2020-04-21 19:02:54', 1);
INSERT INTO `message` VALUES (93, 'pipi', '2016b11015', 'awsed', '2020-04-21 19:03:00', 1);
INSERT INTO `message` VALUES (94, 'pipi', '2016b11015', 'aew', '2020-04-21 19:04:41', 1);
INSERT INTO `message` VALUES (95, '2016b11015', 'pipi', 'sdf', '2020-04-21 19:07:30', 1);
INSERT INTO `message` VALUES (96, '2016b11015', 'pipi', 'asdfr', '2020-04-21 19:08:56', 1);
INSERT INTO `message` VALUES (97, 'pipi', '2016b11015', 'asdf', '2020-04-21 19:09:24', 1);
INSERT INTO `message` VALUES (98, 'pipi', 'GilesWi', 'jdtjux', '2020-05-01 17:06:18', 1);
INSERT INTO `message` VALUES (99, 'GilesWi', 'pipi', 'aeg', '2020-05-01 17:07:43', 1);
INSERT INTO `message` VALUES (100, 'pipi', 'GilesWi', 'bds', '2020-05-01 17:08:18', 1);
INSERT INTO `message` VALUES (101, 'm78', 'pipi', '132165', '2020-05-04 15:02:55', 1);
INSERT INTO `message` VALUES (102, 'm78', 'pipi', '📼', '2020-05-04 15:03:24', 1);
INSERT INTO `message` VALUES (103, 'm78', 'pipi', '123', '2020-05-06 12:51:15', 1);
INSERT INTO `message` VALUES (104, '2016b11015', 'pipi', 'hahahahaha', '2020-05-06 14:10:13', 1);
INSERT INTO `message` VALUES (105, 'm78', 'pipi', '😂', '2020-05-06 16:36:44', 1);
INSERT INTO `message` VALUES (106, 'm78', 'pipi', 'Hi~ o(*￣▽￣*)ブ', '2020-05-06 16:50:38', 1);
INSERT INTO `message` VALUES (107, 'pipi', 'm78', 'bfiuwLE', '2020-05-06 16:55:01', 1);
INSERT INTO `message` VALUES (108, 'pipi', 'm78', 'VSVF', '2020-05-06 16:55:10', 1);
INSERT INTO `message` VALUES (109, 'pipi', 'm78', 'VGWAERG', '2020-05-06 16:55:34', 1);
INSERT INTO `message` VALUES (110, 'pipi', 'm78', 'BERBR', '2020-05-06 16:55:43', 1);
INSERT INTO `message` VALUES (111, 'm78', 'pipi', 'asd', '2020-05-06 23:36:56', 0);
INSERT INTO `message` VALUES (112, 'm78', 'pipi', '123', '2020-05-07 00:10:44', 0);
INSERT INTO `message` VALUES (113, 'm78', 'pipi', 'gaergrh', '2020-05-07 00:29:47', 0);
INSERT INTO `message` VALUES (114, 'm78', 'pipi', 'hwthbwe4', '2020-05-07 00:30:04', 0);
INSERT INTO `message` VALUES (115, 'm78', 'pipi', 'ndznydmrt', '2020-05-07 00:31:19', 0);

-- ----------------------------
-- Table structure for notification
-- ----------------------------
DROP TABLE IF EXISTS `notification`;
CREATE TABLE `notification`  (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `uid` int(20) NOT NULL,
  `time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `read` int(2) NULL DEFAULT 0,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of notification
-- ----------------------------

-- ----------------------------
-- Table structure for post
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post`  (
  `p_id` int(32) NOT NULL AUTO_INCREMENT,
  `p_title` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `img_dir` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `upload_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `p_last_edit_time` timestamp(0) NULL DEFAULT NULL,
  `u_id` int(32) NOT NULL,
  `p_like_count` int(32) NULL DEFAULT 0,
  `p_fav_count` int(32) NULL DEFAULT 0,
  `publicity` int(2) NULL DEFAULT 1,
  PRIMARY KEY (`p_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of post
-- ----------------------------
INSERT INTO `post` VALUES (19, '哈哈哈哈哈哈哈哈哈哈哈哈哈哈', '/post_img/5db12fefad7845f2968737cf6d948523_aBoMd5Vb_700w_0.jpg', '2020-01-02 06:17:58', NULL, 7, 0, 0, 1);
INSERT INTO `post` VALUES (20, 'Confused cat', '/post_img/fa84d21e5af54481a9f207037450e074_aaxqZMoR_700w_0.jpg', '2020-01-02 06:19:33', NULL, 7, 0, 0, 1);
INSERT INTO `post` VALUES (21, 'hahahahahahhahahahahahhaa', '/post_img/1494ea8b8ddd4c2c97982ec496f637ab_8d05631fly1g5rcgw5yyfj20u00u0n67.jpg', '2020-01-02 06:25:36', NULL, 7, 0, 0, 1);
INSERT INTO `post` VALUES (23, 'Henry Cavill', '/post_img/c6d2980de9eb4a11aa3a9bce98650ab5_a1R0BYP_460swp.webp', '2020-01-02 07:56:37', NULL, 1, 0, 0, 1);
INSERT INTO `post` VALUES (28, 'Tom and Jerry', '/post_img/5f6c6ea559834531abd18937f68c468a_bf6af91490b38885fd8bdf9d000399b0.gif', '2020-01-02 14:47:35', NULL, 7, 0, 0, 1);
INSERT INTO `post` VALUES (29, 'T', '/post_img/91306e3732e241f4b6461111530890fa_f6c5eec3f46a3f97b543e337c4edc543.gif', '2020-01-02 15:00:32', NULL, 7, 0, 0, 1);
INSERT INTO `post` VALUES (31, '嘻嘻嘻嘻嘻嘻嘻', '/post_img/bfb0912a08914bef8c1cfceeda478d36_006zyZZ2ly1g6t74koqutj30v60fzta5.jpg', '2020-01-21 12:49:11', NULL, 5, 0, 0, 1);
INSERT INTO `post` VALUES (32, '123456', '/post_img/7f442fbbf5d144ebb4914bba974b4fd4_cyberpunk2077_cdn_wallpaper.png', '2020-02-20 13:30:33', NULL, 5, 0, 0, 1);
INSERT INTO `post` VALUES (34, 'window.location.href', '/post_img/13ad86f2b3444a8f865f80ca436952bd_Snipaste_2020-03-15_16-34-10.png', '2020-05-03 22:44:18', NULL, 5, 0, 0, 1);
INSERT INTO `post` VALUES (35, 'Russell', '/post_img/78bf7c855bb84900b12b2520f3e352cd_photo_2020-02-22_16-03-32.jpg', '2020-04-01 05:41:31', NULL, 5, 0, 0, 1);
INSERT INTO `post` VALUES (36, '一个标题', '/post_img/94ac5d0c16ac4ebb9fd95d32c8503bb2_Snipaste_2020-02-22_15-04-10.png', '2020-05-04 19:19:41', NULL, 5, 0, 0, 1);

-- ----------------------------
-- Table structure for postflag
-- ----------------------------
DROP TABLE IF EXISTS `postflag`;
CREATE TABLE `postflag`  (
  `flag_id` int(255) NOT NULL AUTO_INCREMENT,
  `p_id` int(32) NOT NULL,
  `p_like_flag` int(1) NULL DEFAULT 0,
  `p_fav_flag` int(1) NULL DEFAULT 0,
  `u_id` int(32) NOT NULL,
  PRIMARY KEY (`flag_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 31 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of postflag
-- ----------------------------
INSERT INTO `postflag` VALUES (1, 16, 1, 1, 1);
INSERT INTO `postflag` VALUES (2, 16, 1, 0, 7);
INSERT INTO `postflag` VALUES (19, 32, 0, 1, 5);
INSERT INTO `postflag` VALUES (20, 35, 1, 0, 5);
INSERT INTO `postflag` VALUES (21, 34, 1, 0, 6);
INSERT INTO `postflag` VALUES (22, 32, 1, 0, 6);
INSERT INTO `postflag` VALUES (23, 31, 1, 0, 6);
INSERT INTO `postflag` VALUES (24, 29, 0, 1, 6);
INSERT INTO `postflag` VALUES (25, 25, 0, 1, 6);
INSERT INTO `postflag` VALUES (26, 23, 0, 0, 6);
INSERT INTO `postflag` VALUES (27, 36, 1, 1, 5);
INSERT INTO `postflag` VALUES (28, 20, 1, 0, 5);
INSERT INTO `postflag` VALUES (29, 34, 1, 0, 5);
INSERT INTO `postflag` VALUES (30, 28, 0, 1, 5);
INSERT INTO `postflag` VALUES (31, 36, 1, 0, 1);
INSERT INTO `postflag` VALUES (32, 35, 1, 0, 1);
INSERT INTO `postflag` VALUES (33, 32, 1, 0, 1);
INSERT INTO `postflag` VALUES (34, 34, 1, 0, 1);
INSERT INTO `postflag` VALUES (35, 31, 1, 0, 1);
INSERT INTO `postflag` VALUES (36, 23, 1, 0, 5);

-- ----------------------------
-- Table structure for reports
-- ----------------------------
DROP TABLE IF EXISTS `reports`;
CREATE TABLE `reports`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fromId` int(11) NOT NULL,
  `pid` int(11) NOT NULL,
  `time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `duration` int(8) NULL DEFAULT 0,
  `operateTime` timestamp(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  `archived` int(2) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of reports
-- ----------------------------
INSERT INTO `reports` VALUES (1, 1, 19, '2020-04-26 21:35:59', 3, '2020-05-03 22:39:11', 1);

-- ----------------------------
-- Table structure for subscription
-- ----------------------------
DROP TABLE IF EXISTS `subscription`;
CREATE TABLE `subscription`  (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `own_id` int(16) NULL DEFAULT NULL,
  `sub_id` int(16) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of subscription
-- ----------------------------
INSERT INTO `subscription` VALUES (4, 6, 5);
INSERT INTO `subscription` VALUES (6, 5, 1);
INSERT INTO `subscription` VALUES (10, 5, 7);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(16) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `userState` int(1) NOT NULL DEFAULT 1,
  `avatar` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `frozeUntil` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username_UNIQUE`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '嘻嘻', 'm78', '123456', 1, '/user_avatar/default_user_avatar.jpg', NULL, NULL);
INSERT INTO `user` VALUES (2, '哈哈', '626', '123456', 1, '/user_avatar/default_user_avatar.jpg', NULL, '2120-04-06 11:52:12');
INSERT INTO `user` VALUES (4, '吼吼', 'm19', '123456', 1, '/user_avatar/default_user_avatar.jpg', NULL, NULL);
INSERT INTO `user` VALUES (5, '吼吼', 'pipi', '123456', 1, '/user_avatar/default_user_avatar.jpg', NULL, '2020-05-04 19:19:41');
INSERT INTO `user` VALUES (6, 'chenheng', '2016b11015', '123456', 1, '/user_avatar/default_user_avatar.jpg', NULL, NULL);
INSERT INTO `user` VALUES (7, 'pipi', 'GilesWi', '123456', 1, '/user_avatar/default_user_avatar.jpg', NULL, NULL);
INSERT INTO `user` VALUES (8, '嘻嘻', '1143468921@qq.com', '123456', 1, '/user_avatar/default_user_avatar.jpg', NULL, NULL);
INSERT INTO `user` VALUES (9, '嘻', 'bnkjsbeuk', '123456', 1, '/user_avatar/default_user_avatar.jpg', NULL, NULL);
INSERT INTO `user` VALUES (10, '嘻', 'gvjhkbj', '123456', 1, '/user_avatar/default_user_avatar.jpg', NULL, NULL);
INSERT INTO `user` VALUES (11, 'chenheng', 'fchgvjbkn', '123456', 1, '/user_avatar/default_user_avatar.jpg', NULL, NULL);
INSERT INTO `user` VALUES (14, 'pipi', 'qwerty', '123456', 1, '/user_avatar/default_user_avatar.jpg', NULL, NULL);
INSERT INTO `user` VALUES (15, 'pipi', 'sdfg', '123456', 1, '/user_avatar/default_user_avatar.jpg', NULL, NULL);
INSERT INTO `user` VALUES (16, 'pipi', 'sdfgh', '123456', 1, '/user_avatar/default_user_avatar.jpg', NULL, NULL);
INSERT INTO `user` VALUES (17, 'fgvb', 'hjk', '123456', 1, '/user_avatar/default_user_avatar.jpg', NULL, NULL);
INSERT INTO `user` VALUES (18, 'pipi', 'GilesW', '123456', 0, '/user_avatar/default_user_avatar.jpg', NULL, NULL);
INSERT INTO `user` VALUES (19, '222', '111', '33333', 1, NULL, NULL, NULL);
INSERT INTO `user` VALUES (20, '大帅比', 'obi-wan', 'qqrery', 1, NULL, NULL, NULL);
INSERT INTO `user` VALUES (21, 'GG', 'pipia', '123456', 1, '/user_avatar/default_user_avatar.jpg', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
