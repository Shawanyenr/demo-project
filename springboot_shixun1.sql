/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : localhost:3306
 Source Schema         : springboot_shixun

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 03/04/2020 23:13:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for post
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post`  (
  `p_id` int(32) NOT NULL AUTO_INCREMENT,
  `p_title` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `img_dir` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `upload_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `p_last_edit_time` timestamp(0) NULL DEFAULT NULL,
  `u_id` int(32) NOT NULL,
  `p_like_count` int(32) NULL DEFAULT 0,
  `p_fav_count` int(32) NULL DEFAULT 0,
  PRIMARY KEY (`p_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of post
-- ----------------------------
INSERT INTO `post` VALUES (16, '一个游艇', '/post_img/f123b17ba2d64435aa1c54b717e2eb86_3a51c6a9dd358fbaddfb3d682d781cb7e35cb91e.jpg@518w_1e_1c.jpg', '2020-03-28 17:03:54', '2020-03-28 09:03:54', 1, 0, 0);
INSERT INTO `post` VALUES (19, '哈哈哈哈哈哈哈哈哈哈哈哈哈哈', '/post_img/5db12fefad7845f2968737cf6d948523_aBoMd5Vb_700w_0.jpg', '2020-01-02 06:17:58', NULL, 7, 0, 0);
INSERT INTO `post` VALUES (20, 'Confused cat', '/post_img/fa84d21e5af54481a9f207037450e074_aaxqZMoR_700w_0.jpg', '2020-01-02 06:19:33', NULL, 7, 0, 0);
INSERT INTO `post` VALUES (21, 'hahahahahahhahahahahahhaa', '/post_img/1494ea8b8ddd4c2c97982ec496f637ab_8d05631fly1g5rcgw5yyfj20u00u0n67.jpg', '2020-01-02 06:25:36', NULL, 7, 0, 0);
INSERT INTO `post` VALUES (23, 'Henry Cavill', '/post_img/c6d2980de9eb4a11aa3a9bce98650ab5_a1R0BYP_460swp.webp', '2020-01-02 07:56:37', NULL, 1, 0, 0);
INSERT INTO `post` VALUES (24, '哈哈哈哈哈哈哈哈哈哈哈哈哈哈', '/post_img/f2e2b93c1db5463390ef040ab55712cf_3a51c6a9dd358fbaddfb3d682d781cb7e35cb91e.jpg@518w_1e_1c.jpg', '2020-01-02 08:00:25', NULL, 1, 0, 0);
INSERT INTO `post` VALUES (25, '嘻嘻嘻嘻嘻嘻嘻嘻', '/post_img/8178b17b8a274254b889aca05f60f850_3a51c6a9dd358fbaddfb3d682d781cb7e35cb91e.jpg@518w_1e_1c.jpg', '2020-01-02 08:02:23', NULL, 1, 0, 0);
INSERT INTO `post` VALUES (28, 'Tom and Jerry', '/post_img/5f6c6ea559834531abd18937f68c468a_bf6af91490b38885fd8bdf9d000399b0.gif', '2020-01-02 14:47:35', NULL, 7, 0, 0);
INSERT INTO `post` VALUES (29, 'T', '/post_img/91306e3732e241f4b6461111530890fa_f6c5eec3f46a3f97b543e337c4edc543.gif', '2020-01-02 15:00:32', NULL, 7, 0, 0);
INSERT INTO `post` VALUES (31, '嘻嘻嘻嘻嘻嘻嘻', '/post_img/bfb0912a08914bef8c1cfceeda478d36_006zyZZ2ly1g6t74koqutj30v60fzta5.jpg', '2020-01-21 12:49:11', NULL, 5, 0, 0);
INSERT INTO `post` VALUES (32, '123456', '/post_img/7f442fbbf5d144ebb4914bba974b4fd4_cyberpunk2077_cdn_wallpaper.png', '2020-02-20 13:30:33', NULL, 5, 0, 0);
INSERT INTO `post` VALUES (33, 'vjhvjk', '/post_img/2e1ab055917a4f75a429346d3c8a8b2e_Snipaste_2020-02-27_18-03-38.png', '2020-03-25 21:12:11', NULL, 5, 0, 0);
INSERT INTO `post` VALUES (34, 'window.location.href', '/post_img/13ad86f2b3444a8f865f80ca436952bd_Snipaste_2020-03-15_16-34-10.png', '2020-03-26 03:57:53', NULL, 5, 0, 0);
INSERT INTO `post` VALUES (35, 'Russell', '/post_img/78bf7c855bb84900b12b2520f3e352cd_photo_2020-02-22_16-03-32.jpg', '2020-04-01 05:41:31', NULL, 5, 0, 0);
INSERT INTO `post` VALUES (36, '一个标题', '/post_img/94ac5d0c16ac4ebb9fd95d32c8503bb2_Snipaste_2020-02-22_15-04-10.png', '2020-04-01 10:54:02', NULL, 5, 0, 0);

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
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of postflag
-- ----------------------------
INSERT INTO `postflag` VALUES (1, 16, 1, 1, 1);
INSERT INTO `postflag` VALUES (2, 16, 1, 0, 7);
INSERT INTO `postflag` VALUES (19, 32, 0, 1, 5);

-- ----------------------------
-- Table structure for subscription
-- ----------------------------
DROP TABLE IF EXISTS `subscription`;
CREATE TABLE `subscription`  (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `own_id` int(16) NULL DEFAULT NULL,
  `sub_id` int(16) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

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
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username_UNIQUE`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '嘻嘻', 'm78', '123456', 1, '/user_avatar/default_user_avatar.jpg', NULL);
INSERT INTO `user` VALUES (2, '哈哈', '626', '123456', 1, '/user_avatar/default_user_avatar.jpg', NULL);
INSERT INTO `user` VALUES (4, '吼吼', 'm19', '123456', 1, '/user_avatar/default_user_avatar.jpg', NULL);
INSERT INTO `user` VALUES (5, '吼吼', 'pipi', '123456', 1, '/user_avatar/default_user_avatar.jpg', NULL);
INSERT INTO `user` VALUES (6, 'chenheng', '2016b11015', '123456', 1, '/user_avatar/default_user_avatar.jpg', NULL);
INSERT INTO `user` VALUES (7, 'pipi', 'GilesWi', '123456', 1, '/user_avatar/default_user_avatar.jpg', NULL);
INSERT INTO `user` VALUES (8, '嘻嘻', '1143468921@qq.com', '123456', 1, '/user_avatar/default_user_avatar.jpg', NULL);
INSERT INTO `user` VALUES (9, '嘻', 'bnkjsbeuk', '123456', 1, '/user_avatar/default_user_avatar.jpg', NULL);
INSERT INTO `user` VALUES (10, '嘻', 'gvjhkbj', '123456', 1, '/user_avatar/default_user_avatar.jpg', NULL);
INSERT INTO `user` VALUES (11, 'chenheng', 'fchgvjbkn', '123456', 1, '/user_avatar/default_user_avatar.jpg', NULL);
INSERT INTO `user` VALUES (14, 'pipi', 'qwerty', '123456', 1, '/user_avatar/default_user_avatar.jpg', NULL);
INSERT INTO `user` VALUES (15, 'pipi', 'sdfg', '123456', 1, '/user_avatar/default_user_avatar.jpg', NULL);
INSERT INTO `user` VALUES (16, 'pipi', 'sdfgh', '123456', 1, '/user_avatar/default_user_avatar.jpg', NULL);
INSERT INTO `user` VALUES (17, 'fgvb', 'hjk', '123456', 1, '/user_avatar/default_user_avatar.jpg', NULL);
INSERT INTO `user` VALUES (18, 'pipi', 'GilesW', '123456', 0, '/user_avatar/default_user_avatar.jpg', NULL);
INSERT INTO `user` VALUES (19, '222', '111', '33333', 1, NULL, NULL);
INSERT INTO `user` VALUES (20, '大帅比', 'obi-wan', 'qqrery', 1, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
