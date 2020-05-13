/*
 Navicat Premium Data Transfer

 Source Server         : mysql8.0
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : user

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 13/05/2020 22:46:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for agency
-- ----------------------------
DROP TABLE IF EXISTS `agency`;
CREATE TABLE `agency`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '经纪机构名称',
  `address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '地址',
  `phone` char(13) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '手机号',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮箱',
  `about_us` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '描述',
  `mobile` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '电话',
  `web_site` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '网站',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of agency
-- ----------------------------
INSERT INTO `agency` VALUES (1, '江西陈家班', '江西南昌', '010-89898989', '010@gmail.com', '销售量领先', '100884', 'www.chenjia.com');
INSERT INTO `agency` VALUES (2, '赣州好房湾', '江西赣州', '010-87898989', '020@gmail.com', '陈总创办，服务一流', '100885', 'www.ganzhou.com');
INSERT INTO `agency` VALUES (3, '恒大名都府', '江西赣州', '010-87898586', '030@gmail.com', '归属于恒大集团', '100886', 'www.hengda.com');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户名',
  `phone` char(13) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '手机号',
  `email` varchar(90) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮箱',
  `aboutme` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '自我介绍',
  `passwd` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '密码（MD5）',
  `avatar` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '头像图片',
  `type` tinyint(1) DEFAULT 1 COMMENT '1普通用户，2经纪人',
  `create_time` datetime(0) DEFAULT '2019-06-06 00:00:00' COMMENT '创建时间',
  `enable` tinyint(1) DEFAULT 0 COMMENT '1启用2停用',
  `agency_id` int(11) DEFAULT 0 COMMENT '所属经纪机构',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `email`(`email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '陈志平', '15007975295', '2771109447@qq.com', '好房网总经理', '207acd61a3c1bd506d7e9a4535359f8a', 'dog-01.jpg', 2, '2019-08-09 16:55:23', 1, 2);
INSERT INTO `user` VALUES (2, '尚志刚', '15007975295', '2771109448@qq.com', '恒大副经理', '207acd61a3c1bd506d7e9a4535359f8a', 'dog-02.jpg', 2, '2019-08-09 17:00:53', 1, 3);
INSERT INTO `user` VALUES (3, '李文琪', '13007975295', '2771109449@qq.com', '最牛钉子户', '207acd61a3c1bd506d7e9a4535359f8a', 'dog-03.jpg', 1, '2019-08-10 01:36:16', 1, 0);

SET FOREIGN_KEY_CHECKS = 1;
