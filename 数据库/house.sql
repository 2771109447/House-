/*
 Navicat Premium Data Transfer

 Source Server         : mysql8.0
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : house

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 13/05/2020 22:45:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for city
-- ----------------------------
DROP TABLE IF EXISTS `city`;
CREATE TABLE `city`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `city_name` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '城市名称',
  `city_code` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '城市编码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of city
-- ----------------------------
INSERT INTO `city` VALUES (1, '赣州市', '110000');

-- ----------------------------
-- Table structure for community
-- ----------------------------
DROP TABLE IF EXISTS `community`;
CREATE TABLE `community`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `city_code` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '城市编码',
  `city_name` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '城市名称',
  `name` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '小区名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of community
-- ----------------------------
INSERT INTO `community` VALUES (1, '110000', '赣州市', '上林春天 ');
INSERT INTO `community` VALUES (2, '110000', '赣州市', '樱花公馆 ');
INSERT INTO `community` VALUES (3, '110000', '赣州市', '赣南客家园');

-- ----------------------------
-- Table structure for house
-- ----------------------------
DROP TABLE IF EXISTS `house`;
CREATE TABLE `house`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '房产名称',
  `type` tinyint(1) DEFAULT NULL COMMENT '1，销售，2出租',
  `price` int(11) DEFAULT NULL COMMENT '价格',
  `images` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '房屋图片',
  `area` int(11) DEFAULT NULL COMMENT '房屋面积',
  `beds` int(11) DEFAULT NULL COMMENT '卧室数量',
  `baths` int(11) DEFAULT NULL COMMENT '浴室数量',
  `rating` double DEFAULT 0 COMMENT '评分',
  `remarks` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '房产描述',
  `properties` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '房产属性',
  `floor_plan` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '户型图',
  `tags` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '标签',
  `create_time` datetime(0) DEFAULT '2019-06-06 00:00:00' ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `city_id` int(11) DEFAULT NULL COMMENT '城市id',
  `community_id` int(11) DEFAULT NULL COMMENT '小区id',
  `address` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '1' COMMENT '房产地址',
  `state` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '1' COMMENT '1上架2下架',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of house
-- ----------------------------
INSERT INTO `house` VALUES (1, '上林春天  120平米', 1, 600, 'property-01.jpg,property-04.jpg', 120, 2, 12, 3, '上林春天 120平方米 气派舒适', '得房率高,户型好,落地窗', 'floor-plan-01.jpg', '', '2020-03-20 18:32:01', 1, 1, '赣新大道306号', '1');
INSERT INTO `house` VALUES (2, '樱花公馆     180平 南北通透', 1, 800, 'property-02.jpg,property-05.jpg', 120, 2, 2, 2, '樱花公馆  180平方米  南北通透', '满五年,采光好,价格合理,税少,学区房', 'floor-plan-02.jpg', '', '2020-03-11 11:42:05', 1, 2, '樱花公园南', '1');
INSERT INTO `house` VALUES (3, '赣南客家园   三面采光 高楼层', 2, 140, 'property-03.jpg,property-06.jpg', 140, 2, 2, 3, '赣南客家园 三面采光 高楼层', '南北通透,环境好,带阳台', 'floor-plan-01.jpg', '', '2020-03-11 16:46:09', 1, 3, '客家文化城', '1');
INSERT INTO `house` VALUES (4, '赣南名苑', 2, 180, 'property-07.jpg,property-08.jpg', 120, 5, 2, 3, '赣江北岸，江南·客家风情', '南北通透,环境好,带阳台,没有遮挡', 'floor-plan-01.jpg', '   ', '2020-05-13 20:59:36', 1, 3, '江西赣州', '1');

-- ----------------------------
-- Table structure for house_user
-- ----------------------------
DROP TABLE IF EXISTS `house_user`;
CREATE TABLE `house_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `house_id` bigint(20) DEFAULT NULL COMMENT '房屋id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `create_time` datetime(0) DEFAULT '2019-06-06 00:00:00' COMMENT '创建时间',
  `type` tinyint(1) DEFAULT 0 COMMENT '1售卖或出租 2收藏',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `house_id`(`house_id`, `user_id`, `type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '房产用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of house_user
-- ----------------------------
INSERT INTO `house_user` VALUES (1, 1, 1, '2019-06-06 00:00:00', 1);
INSERT INTO `house_user` VALUES (2, 2, 1, '2019-06-06 00:00:00', 1);
INSERT INTO `house_user` VALUES (3, 3, 1, '2019-06-06 00:00:00', 1);
INSERT INTO `house_user` VALUES (4, 4, 2, '2019-08-10 01:50:35', 1);

-- ----------------------------
-- Table structure for user_msg
-- ----------------------------
DROP TABLE IF EXISTS `user_msg`;
CREATE TABLE `user_msg`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `msg` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '消息',
  `create_time` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `agent_id` bigint(20) DEFAULT 0 COMMENT '经纪人id',
  `house_id` bigint(20) DEFAULT 0 COMMENT '房产id',
  `user_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '房产信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_msg
-- ----------------------------
INSERT INTO `user_msg` VALUES (1, '这房子有优惠吗？', '2019-07-22 10:34:34', 1, 1, '尚志刚');
INSERT INTO `user_msg` VALUES (2, '一般般啦', '2019-08-09 03:18:05', 1, 1, '尚志刚');

SET FOREIGN_KEY_CHECKS = 1;
