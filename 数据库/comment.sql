/*
 Navicat Premium Data Transfer

 Source Server         : mysql8.0
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : comment

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 13/05/2020 22:46:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for blog
-- ----------------------------
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tags` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标签',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '内容',
  `create_time` datetime(0) DEFAULT '2019-06-06 00:00:00' ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `title` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '标题',
  `cat` int(11) DEFAULT 0 COMMENT '分类',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog
-- ----------------------------
INSERT INTO `blog` VALUES (1, '买房,售房', '<p>赣州籍家庭在赣州买房需连续5年(含)以上在本市缴纳个人所得税或社保，在资质审核时，个税的审核标准为<strong>在赣州连续缴纳满60个月。</strong></p>\n<h1>一、个税需满足什么条件？</h1>\n<p>&nbsp;</p>\n<p>根据北京的限购政策，非京籍家庭连续5年(含)以上在京缴纳社会保险或个人所得税，限购1套住房。</p>\n<p>此前购房资格审核时关于工资、薪金纳税人纳税记录的认定，是5年期间，每年至少1次纳税记录即可，自2017年3月22日起已<strong>改为申请月的上一个月开始往前推算连续60个月在赣州缴纳个人所得税。</strong></p>\n<p></p><p class=\"insert-img\"><img alt=\"个税审核标准的公告原文\" src=\"\" _src=\"\" class=\"insert-img-img\"><span class=\"insert-img-img-title\">个税审核标准的公告原文</span></p><p></p>\n<p>按照公告规定，个税缴纳记录分两种情形审核认定：</p>\n<p>（1）<strong>按“工资、薪金所得”缴税的纳税人</strong>，应从申请月的上一个月开始往前推算60个月在本市连续缴纳个人所得税。</p>\n<p>（2）<strong>按“个体工商户生产、经营所得”缴税的纳税人</strong>，包括取得“个体工商户生产、经营所得”的个体工商户经营者、个人独资企业出资人、合伙企业个人合伙人和个人，根据各自适用的计税期间，采取按月缴纳税款方式的，从申请月的上一个月开始往前推算60个月在本市连续缴纳个人所得税；采取按季缴纳税款方式的，从申请月的上一季度开始往前推算20个季度在本市连续缴纳个人所得税。</p>\n<p>&nbsp;</p>\n<h1>二、个税断了还能买房吗？</h1>\n<p>&nbsp;</p>\n<p>公告规定：对于因工作变动等原因造成未缴或补缴税款且不超过3个月的，视为连续缴纳。意味着连续的60个月中，<strong>个税有过断缴但不超过3个月的不影响购房资格。</strong></p>\n<p><strong>个税断缴超过3个月或者薪资未达到个税征收标准的非赣州籍家庭，</strong>想要买房需看社保有没有连续缴纳满60个月，且自2012年12月18日起，补缴社保在购房资格审核中不予认可，但因工作调转单位补缴不超过3个月的视为有效。</p>\n<p></p><p class=\"insert-img\"><img alt=\"非赣州买房，个税和社保缴纳标准满足一个即可\" src=\"\" _src=\"\" class=\"insert-img-img\"><span class=\"insert-img-img-title\">非京籍买房，个税和社保缴纳标准满足一个即可</span></p><p></p>\n<p>为了避免违约，想买房的非京籍家庭若个税或社保有断缴情况，建议先去不动产登记中心确认购房资质再签合同。</p>\n<p>资料来源：《关于进一步严格购房资格审核中个人所得税政策执行标准的公告》</p>\n', '2019-08-12 12:16:15', '非赣州籍家庭在赣州买房条件', NULL);
INSERT INTO `blog` VALUES (2, '买房,售房', '<div class=\"essay_abstract\">	 <p><span class=\"red\">[摘要]</span>相信赣州的朋友对于赣州首套房证明这个词语并不陌生，因为是赣州首套房，所以会享受到国家对于赣州的购买第一套房的相关优惠政策，比如贷款的利率打折、首付比例降低、契税减半等等，但是，享受这些的前提就是要提供赣州的首套房证明。那么赣州首套房证明可以代办吗?赣州首套房证明代办需要什么材料?</p>\r\n            		\r\n            	\r\n            </div>\r\n<div class=\"essay_zw\">\n             <p style=\"text-indent: 2em; text-align: center;\"><strong><img alt=\"描述\" src=\"\"></strong></p><p style=\"text-indent: 2em; text-align: left;\">相信赣州的朋友对于赣州<a href=\"http://www.fang.com/juhe/952/\" target=\"_blank\" \'=\"\">首套房证明</a>这个词语并不陌生，因为是赣州首套房，所以会享受到国家对于赣州的购买第一套房的相关优惠政策，比如贷款的利率打折、首付比例降低、契税减半等等，但是，享受这些的前提就是要提供赣州的首套房证明。那么赣州首套房证明可以代办吗?赣州首套房证明代办需要什么材料?</p><p style=\"text-indent: 2em; text-align: left;\"><strong>一、赣州首套房证明可以代办吗?</strong></p><p style=\"text-indent: 2em; text-align: left;\"><strong> 二、赣州首套房证明代办需要什么材料?</strong></p><p style=\"text-indent: 2em; text-align: left;\">&nbsp;1、赣州首套住房预约号时需携带以下材料：</p><p style=\"text-indent: 2em; text-align: left;\">&nbsp;(1)赣州首套房购房者本人(夫妻之间谁到场拿谁的身份证原件、复印件预约);</p><p style=\"text-indent: 2em; text-align: left;\">&nbsp;(2)查询以家庭为单位，18周岁以上70周岁以下的需本人到场，不能代办，代办时需持委托《公证书》);</p><p style=\"text-indent: 2em; text-align: left;\">&nbsp;赣州首套住房查询以家庭为单位，夫妻之间可以代办，其他人不可以代办，夫妻双方来一人就可以办理。</p><p style=\"text-indent: 2em; text-align: left;\">&nbsp;办理赣州首套住房证明的时候，购房者本人到所在区县的<a href=\"http://www.fang.com/juhe/31324/\" target=\"_blank\" \'=\"\">房地产</a>交易核心，带上身份证和购房合同(包括<a href=\"http://www.fang.com/juhe/2173/\" target=\"_blank\" \'=\"\">商品房预售</a>合同、商品房出售合同和房<a href=\"http://www.fang.com/juhe/31154/\" target=\"_blank\" \'=\"\">地产</a>买卖合同)。如果购房人现在有与父母共同购买住房的，申请时还要出具户籍等相关证明。相关材料提交后，房地产交易核心将在受理之日起7个工作日内出具头次购房证明。</p><p style=\"text-indent: 2em; text-align: left;\">&nbsp;(3)赣州首套房购房者本人身份证原件以及身份证复印件一份。</p><p style=\"text-indent: 2em; text-align: left;\">&nbsp;2、赣州首套住房查询时需携带以下材料：</p><p style=\"text-indent: 2em; text-align: left;\">&nbsp;(1)赣州首套房购房者身份证：男方(含单身)原件、复印件一份，女方(含单身)原件、复印件一份;</p><!-- 20170420 begin--><p style=\"text-indent: 2em; text-align: left;\">&nbsp;(2)赣州首套房购房者户口簿：男方(含单身)主页和本人页的原件、复印件一份，女方(含单身)主页和本人页原件、复印件一份;</p><p style=\"text-indent: 2em; text-align: left;\">&nbsp;(3)集体户的需开户籍证明，原件、复印件一份，有未成年小孩的需户口原件和复印件一份;</p><p style=\"text-indent: 2em; text-align: left;\">&nbsp;(4)结婚证的原件、复印件一份;</p><p style=\"text-indent: 2em; text-align: left;\">&nbsp;(5)单身(丧偶、离婚、未婚)需户籍所在地民政局出具的单身证明原件、复印件一份。</p><p style=\"text-indent: 2em; text-align: left;\">&nbsp;总的来说，首套房证明没有什么麻烦的步骤，不过不同行政区，有不同的规定，而且就算一个地区你找不同的人，都会出现不同的回答，还会出现推卸责任的状况，所以建议大家提前看一下当地的政府网站。</p>\n             </div>', '2019-08-12 00:00:00', '赣州首套房证明可以代办吗？需要什么材料', 0);

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `content` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '评论内容',
  `house_id` bigint(20) DEFAULT 0 COMMENT '房产id',
  `create_time` datetime(0) DEFAULT '2019-06-06 00:00:00' COMMENT '创建时间',
  `blog_id` int(11) DEFAULT 0 COMMENT '博客id',
  `type` tinyint(1) DEFAULT 0 COMMENT '1房产2博客',
  `user_id` bigint(20) DEFAULT 0 COMMENT '用户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (1, '你这房子也就一般般啦！至于辣么贵吗？', 4, '2019-08-11 10:33:12', 0, 1, 1);
INSERT INTO `comment` VALUES (2, '很好！very good! 物美价廉！', 4, '2019-08-11 10:43:46', 0, 1, 3);
INSERT INTO `comment` VALUES (3, '懂了！小老弟！', 0, '2019-08-11 10:45:30', 1, 2, 3);
INSERT INTO `comment` VALUES (4, '物美价廉，好！', 3, '2019-08-12 06:11:55', 0, 1, 3);
INSERT INTO `comment` VALUES (5, '好的！谢谢！', 0, '2019-08-12 06:16:57', 2, 2, 3);
INSERT INTO `comment` VALUES (6, '牛逼，哈哈哈！', 3, '2019-08-12 07:18:20', 0, 1, 2);

SET FOREIGN_KEY_CHECKS = 1;
