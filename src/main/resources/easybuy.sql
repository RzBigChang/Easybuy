/*
Navicat MySQL Data Transfer

Source Server         : wuyanzu
Source Server Version : 50554
Source Host           : localhost:3306
Source Database       : easybuy

Target Server Type    : MYSQL
Target Server Version : 50554
File Encoding         : 65001

Date: 2020-05-13 16:46:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for easybuy_collect
-- ----------------------------
DROP TABLE IF EXISTS `easybuy_collect`;
CREATE TABLE `easybuy_collect` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `userId` int(10) NOT NULL COMMENT '用户ID',
  `productId` int(10) NOT NULL COMMENT '商品ID',
  `productNum` int(10) NOT NULL COMMENT '商品数量',
  `type` int(1) NOT NULL COMMENT '类型',
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  KEY `productId` (`productId`),
  CONSTRAINT `easybuy_collect_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `easybuy_user` (`id`),
  CONSTRAINT `easybuy_collect_ibfk_2` FOREIGN KEY (`productId`) REFERENCES `easybuy_product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='收藏表';

-- ----------------------------
-- Records of easybuy_collect
-- ----------------------------
INSERT INTO `easybuy_collect` VALUES ('5', '2', '743', '0', '0');
INSERT INTO `easybuy_collect` VALUES ('6', '2', '733', '0', '0');
INSERT INTO `easybuy_collect` VALUES ('7', '2', '735', '0', '0');
INSERT INTO `easybuy_collect` VALUES ('9', '2', '734', '0', '0');
INSERT INTO `easybuy_collect` VALUES ('10', '2', '780', '0', '0');
INSERT INTO `easybuy_collect` VALUES ('11', '137', '734', '0', '0');

-- ----------------------------
-- Table structure for easybuy_fankui
-- ----------------------------
DROP TABLE IF EXISTS `easybuy_fankui`;
CREATE TABLE `easybuy_fankui` (
  `faxie` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `manyidu` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `ipone` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`faxie`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of easybuy_fankui
-- ----------------------------
INSERT INTO `easybuy_fankui` VALUES ('11', '1', '喂喂喂喂', '15871545');
INSERT INTO `easybuy_fankui` VALUES ('1221313123123123123', '1', 'zzazazazazazazaz', '15215654372');
INSERT INTO `easybuy_fankui` VALUES ('211212', '4', 'zzazazazazazazaz', '15215654372');
INSERT INTO `easybuy_fankui` VALUES ('hhhh', '4', '棒棒', '15871545');
INSERT INTO `easybuy_fankui` VALUES ('hhjhj', '1', '二狗', '15871545');
INSERT INTO `easybuy_fankui` VALUES ('wadawd', '3', '喂喂喂喂', '15871545');
INSERT INTO `easybuy_fankui` VALUES ('wfafafw', '3', '二狗', '15871545');
INSERT INTO `easybuy_fankui` VALUES ('wgaegeaheheahea', '1', '喂喂喂喂', '15871545');
INSERT INTO `easybuy_fankui` VALUES ('wwewewew', '4', '我打打我', '15871545');
INSERT INTO `easybuy_fankui` VALUES ('wwww', '1', '二', '15871545');
INSERT INTO `easybuy_fankui` VALUES ('太好用了', '5', '喂喂', '158715458');
INSERT INTO `easybuy_fankui` VALUES ('好', '1', '喂喂喂喂', '15871545');
INSERT INTO `easybuy_fankui` VALUES ('好好好好好哦好好哦好哦好啊哦哦', '4', '棒趟', '158715457');
INSERT INTO `easybuy_fankui` VALUES ('很好的卖家，谢谢喽。我的同事们都很喜欢呢。下次再来哦 ！', '1', '狗狗', '158715454');
INSERT INTO `easybuy_fankui` VALUES ('我的文档', '2', '棒棒', '13966971805');
INSERT INTO `easybuy_fankui` VALUES ('期盼之中，终于等到了心爱的东东，谢谢!', '2', '棒棒', '158715451');
INSERT INTO `easybuy_fankui` VALUES ('期盼之中，终于等到了心爱的东东，谢谢!期盼之中，终于等到了心爱的东东，谢谢!', '3', '棒', '158715450');
INSERT INTO `easybuy_fankui` VALUES ('棒棒棒棒', '3', '我打打我', '158715452');
INSERT INTO `easybuy_fankui` VALUES ('歌胡歌胡歌', '3', '棒棒', '15871545');
INSERT INTO `easybuy_fankui` VALUES ('比淘宝，京东还好用', '2', 'ww', '1587154');
INSERT INTO `easybuy_fankui` VALUES ('潍坊潍坊', '1', '棒棒', '13966971805');
INSERT INTO `easybuy_fankui` VALUES ('非常非常非常的好用，是个完美的购买网站', '1', '二狗', '15871545');
INSERT INTO `easybuy_fankui` VALUES ('鞋子很好看，颜色感觉比图片的更好看些，很好。包装很认真啊，看得出来，老板很用心。正品。态度更要赞一个! ', '2', '好', '158715458');
INSERT INTO `easybuy_fankui` VALUES ('鞋子还不错，穿着很舒服，做工很精致，物流给力速度快，性价比很高，好评。', '1', '我打', '15871545');

-- ----------------------------
-- Table structure for easybuy_news
-- ----------------------------
DROP TABLE IF EXISTS `easybuy_news`;
CREATE TABLE `easybuy_news` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `title` varchar(255) NOT NULL COMMENT '标题',
  `content` varchar(1024) NOT NULL COMMENT '内容',
  `createTime` date NOT NULL COMMENT '录入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=704 DEFAULT CHARSET=utf8 COMMENT='资讯表';

-- ----------------------------
-- Records of easybuy_news
-- ----------------------------
INSERT INTO `easybuy_news` VALUES ('531', '会员特惠月开始了', '会员特惠月开始了,亲们赶快下单啊,不到剁手誓不罢休啊,不到离婚誓不清空购物车啊。更多优惠，尽在易买网。', '2010-12-22');
INSERT INTO `easybuy_news` VALUES ('597', '迎双旦促销大酬宾', '迎双旦促销大酬宾', '2010-12-24');
INSERT INTO `easybuy_news` VALUES ('649', '加入会员，赢千万大礼包', '加入会员，赢千万大礼包', '2010-12-22');
INSERT INTO `easybuy_news` VALUES ('650', '新年不夜天，通宵也是开张了', '新年不夜天，通宵也是开张了', '2011-05-22');
INSERT INTO `easybuy_news` VALUES ('651', '积分兑换开始了', '积分兑换开始了', '2010-12-22');
INSERT INTO `easybuy_news` VALUES ('653', '团购阿迪1折起', '团购阿迪1折起', '2010-12-22');
INSERT INTO `easybuy_news` VALUES ('664', '最新酷睿笔记本', 'IBME系列全场促销中，最新酷睿双核处理器，保证CPU更高效的运转。', '2013-08-05');
INSERT INTO `easybuy_news` VALUES ('675', 'aa', '0123456789012345678901234567890123456789012345678967890123456789012345678901234567890123456789012345678901234567890123456789', '2013-08-14');
INSERT INTO `easybuy_news` VALUES ('676', 'ResultR', 'ResultResultResultResultResu', '2016-03-28');
INSERT INTO `easybuy_news` VALUES ('677', '会员特惠月开始了1', '会员特惠月开始了', '2010-12-22');
INSERT INTO `easybuy_news` VALUES ('678', '迎双旦促销大酬宾2', '迎双旦促销大酬宾', '2010-12-24');
INSERT INTO `easybuy_news` VALUES ('679', '加入会员，赢千万大礼包3', '加入会员，赢千万大礼包', '2010-12-22');
INSERT INTO `easybuy_news` VALUES ('680', '新年不夜天，通宵也是开张了4', '新年不夜天，通宵也是开张了', '2011-05-22');
INSERT INTO `easybuy_news` VALUES ('681', '积分兑换开始了5', '积分兑换开始了', '2010-12-22');
INSERT INTO `easybuy_news` VALUES ('682', '团购阿迪1折起6', '团购阿迪1折起', '2010-12-22');
INSERT INTO `easybuy_news` VALUES ('683', '最新酷睿笔记本7', 'IBME系列全场促销中，最新酷睿双核处理器，保证CPU更高效的运转。', '2013-08-05');
INSERT INTO `easybuy_news` VALUES ('684', 'aa8', '0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678923456789', '2013-08-14');
INSERT INTO `easybuy_news` VALUES ('685', 'ResultR9', 'ResultResultResultResultResu', '2016-03-28');
INSERT INTO `easybuy_news` VALUES ('686', '会员特惠月开始了11', '会员特惠月开始了', '2010-12-22');
INSERT INTO `easybuy_news` VALUES ('687', '迎双旦促销大酬宾21', '迎双旦促销大酬宾', '2010-12-24');
INSERT INTO `easybuy_news` VALUES ('688', '加入会员，赢千万大礼包31', '加入会员，赢千万大礼包', '2010-12-22');
INSERT INTO `easybuy_news` VALUES ('689', '新年不夜天，通宵也是开张了41', '新年不夜天，通宵也是开张了', '2011-05-22');
INSERT INTO `easybuy_news` VALUES ('690', '积分兑换开始了51', '积分兑换开始了', '2010-12-22');
INSERT INTO `easybuy_news` VALUES ('691', '团购阿迪1折起61', '团购阿迪1折起', '2010-12-22');
INSERT INTO `easybuy_news` VALUES ('692', '最新酷睿笔记本71', 'IBME系列全场促销中，最新酷睿双核处理器，保证CPU更高效的运转。', '2013-08-05');
INSERT INTO `easybuy_news` VALUES ('693', 'aa81', '01234567890123456789012301234567890123456789012345678901234567890123456789012345678901234567890123456789', '2013-08-14');
INSERT INTO `easybuy_news` VALUES ('694', 'ResultR91', 'ResultResultResultResultResu', '2016-03-28');
INSERT INTO `easybuy_news` VALUES ('695', '会员特惠月开始了111', '会员特惠月开始了', '2010-12-22');
INSERT INTO `easybuy_news` VALUES ('696', '迎双旦促销大酬宾211', '迎双旦促销大酬宾', '2010-12-24');
INSERT INTO `easybuy_news` VALUES ('697', '加入会员，赢千万大礼包311', '加入会员，赢千万大礼包', '2010-12-22');
INSERT INTO `easybuy_news` VALUES ('698', '新年不夜天，通宵也是开张了411', '新年不夜天，通宵也是开张了', '2011-05-22');
INSERT INTO `easybuy_news` VALUES ('699', '积分兑换开始了511', '积分兑换开始了', '2010-12-22');
INSERT INTO `easybuy_news` VALUES ('700', '团购阿迪1折起611', '团购阿迪1折起', '2010-12-22');
INSERT INTO `easybuy_news` VALUES ('701', '最新酷睿笔记本711', 'IBME系列全场促销中，最新酷睿双核处理器，保证CPU更高效的运转。', '2013-08-05');
INSERT INTO `easybuy_news` VALUES ('702', 'aa811', '012345678901234567890123456789012345678901234567890123456789012345678901234567890123456780123456789', '2013-08-14');
INSERT INTO `easybuy_news` VALUES ('703', 'ResultR911', 'ResultResultResultResultResu', '2016-03-28');

-- ----------------------------
-- Table structure for easybuy_order
-- ----------------------------
DROP TABLE IF EXISTS `easybuy_order`;
CREATE TABLE `easybuy_order` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `userId` int(10) NOT NULL COMMENT '用户ID',
  `loginName` varchar(20) NOT NULL COMMENT '登陆用户名',
  `userAddress` varchar(200) NOT NULL COMMENT '用户地址',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `cost` float(50,3) NOT NULL COMMENT '金额',
  `status` int(1) NOT NULL COMMENT '状态（1待审核,2审核通过,3配货,4卖家已发货,5已收货）',
  `type` int(1) NOT NULL COMMENT '类型',
  `serialNumber` varchar(64) NOT NULL COMMENT '订单号',
  `cancel` int(255) NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `message` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`,`status`),
  KEY `userId` (`userId`),
  CONSTRAINT `easybuy_order_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `easybuy_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COMMENT='订单表';

-- ----------------------------
-- Records of easybuy_order
-- ----------------------------
INSERT INTO `easybuy_order` VALUES ('1', '18', 'shangzezhong', '北京市花园路小区', '2016-06-02 14:51:46', '1721.000', '5', '1', '60B7487F47F9434EAA5FD1D9E22CB06C', '1', '2019-12-19 15:38:20', '垃圾啊啊啊啊啊啊');
INSERT INTO `easybuy_order` VALUES ('2', '18', 'shangzezhong', '北京市海淀区成府路', '2016-06-02 14:52:49', '8596.000', '5', '1', '8EF5E1557D55413781658A65FC301B8A', '0', '2019-11-25 13:49:42', '');
INSERT INTO `easybuy_order` VALUES ('3', '2', 'admin', '北京市海淀区大有庄', '2016-06-03 11:41:09', '456.000', '5', '1', '51718726C1274CC59504AB4E6FD64BA0', '0', '2019-11-25 13:49:44', '');
INSERT INTO `easybuy_order` VALUES ('4', '2', 'admin', '北京市海淀区大有庄', '2018-06-14 16:34:24', '760.000', '1', '1', 'E02BE450C529464789899F8198506C76', '0', '2019-11-25 13:49:47', '填写原因！！！');
INSERT INTO `easybuy_order` VALUES ('5', '2', 'admin', '北京市海淀区大有庄', '2019-11-19 09:21:19', '152.000', '1', '1', '043CE269809F4E2E8190813ACE411952', '0', '2019-11-25 13:49:49', '');
INSERT INTO `easybuy_order` VALUES ('6', '2', 'admin', '北京市海淀区大有庄', '2019-11-19 10:28:05', '1400.000', '1', '1', '6D36DEA807734A3A8D91DEC33F91D82B', '0', '2019-11-25 13:49:51', '11212！');
INSERT INTO `easybuy_order` VALUES ('7', '68', 'q', '安徽省', '2019-11-25 09:22:00', '456.000', '1', '1', '00E6B93BDA0D44EABD32B950A1709CAF', '0', '2019-11-25 13:49:54', '');
INSERT INTO `easybuy_order` VALUES ('8', '68', 'q', '安徽省', '2019-11-25 09:52:54', '152.000', '1', '1', '6E94950F96A1448589E2DD1A201EE902', '0', '2019-11-25 13:49:56', '');
INSERT INTO `easybuy_order` VALUES ('9', '68', 'q', '安徽省', '2019-11-25 09:54:26', '456.000', '1', '1', 'FCCF7DDA2C1D4824BCA438C782F0A87D', '0', '2019-11-25 13:49:59', '');
INSERT INTO `easybuy_order` VALUES ('10', '14', 'sb', '安徽省', '2019-11-25 09:56:54', '152.000', '1', '1', '86E9000B740B493BA4A14B71127C3E9D', '1', '2019-12-19 15:37:51', '填写原因！！！');
INSERT INTO `easybuy_order` VALUES ('11', '2', 'admin', '北京市海淀区大有庄', '2019-11-26 12:21:29', '999.000', '1', '1', '21A1CB3E8B4C4B84BB67AB47587F8A9B', '0', '2019-11-26 12:21:29', null);
INSERT INTO `easybuy_order` VALUES ('12', '2', 'admin', '北京市海淀区大有庄', '2019-11-26 16:13:38', '289.000', '1', '1', 'EC8C770812064A769B1F96854A6F3E57', '1', '2019-12-19 15:20:26', '垃圾');
INSERT INTO `easybuy_order` VALUES ('13', '87', 'lqq', '安徽省合肥市', '2019-11-27 14:26:27', '456.000', '1', '1', 'CD98B48126A240C59A39B1F7F199F419', '1', '2019-12-19 15:37:34', '垃圾啊啊啊啊');
INSERT INTO `easybuy_order` VALUES ('14', '87', 'lqq', '安徽省合肥市', '2019-11-27 14:28:16', '304.000', '1', '1', '7E7351E831154728B36065667B4D32E1', '1', '2019-12-19 15:20:38', '垃圾');
INSERT INTO `easybuy_order` VALUES ('15', '2', 'admin', '北京市海淀区大有庄', '2019-11-27 14:58:05', '5932.000', '1', '1', '3E6D1C545659483AA00793F935B7BFCB', '1', '2019-12-19 14:19:39', '垃圾');
INSERT INTO `easybuy_order` VALUES ('16', '2', 'admin', '北京市海淀区大有庄', '2019-11-28 09:42:49', '1672.000', '1', '1', '704396A6AE174D37B5F6E53E5B83280B', '1', '2019-12-19 13:49:42', '填写原因！！');
INSERT INTO `easybuy_order` VALUES ('17', '2', 'admin', '北京市海淀区大有庄', '2019-11-28 14:11:53', '200.000', '1', '1', '052BCA7E3E81449FA7D6AFE290A6B5F1', '1', '2019-11-28 14:29:45', '垃圾');
INSERT INTO `easybuy_order` VALUES ('18', '2', 'admin', '北京市海淀区大有庄', '2020-03-08 12:10:59', '29.800', '1', '1', 'A8414E82BB8E4363A112E070AD955281', '0', '2020-03-08 12:10:59', null);
INSERT INTO `easybuy_order` VALUES ('19', '2', 'admin', '北京市海淀区大有庄', '2020-03-12 02:59:46', '152.000', '1', '1', '6AC360702C4E40B2A13EA4A7F30353D7', '0', '2020-03-12 02:59:46', null);
INSERT INTO `easybuy_order` VALUES ('20', '2', 'admin', '安徽省', '2020-03-12 13:30:13', '456.000', '1', '1', '7B00BB1FE0204757A72F6F0ED575E4BD', '0', '2020-03-12 13:30:13', null);
INSERT INTO `easybuy_order` VALUES ('21', '2', 'admin', '安徽省', '2020-03-12 13:35:38', '15600.000', '1', '1', '1037114395B94817A64056190D0D312B', '0', '2020-03-12 13:35:38', null);
INSERT INTO `easybuy_order` VALUES ('22', '2', 'admin', '安徽省', '2020-03-12 13:38:50', '2981500.000', '1', '1', '923E45B1264343EBA0096F0706846256', '0', '2020-03-12 13:38:50', null);
INSERT INTO `easybuy_order` VALUES ('23', '2', 'admin', '安徽省', '2020-03-12 13:48:19', '760.000', '1', '1', '95F6F5E99E7D4CC4AE1654FD6202021E', '0', '2020-03-12 13:48:19', null);
INSERT INTO `easybuy_order` VALUES ('24', '2', 'admin', '安徽省', '2020-03-12 13:54:00', '4256.000', '1', '1', '45A502BAF0AD4D4CB7121B01EB0A17C6', '0', '2020-03-12 13:54:00', null);
INSERT INTO `easybuy_order` VALUES ('25', '2', 'admin', '安徽省', '2020-03-12 13:54:55', '136800.000', '1', '1', 'C5C758A5FA1B4B5F98AD0E7F57C7551E', '0', '2020-03-12 13:54:55', null);
INSERT INTO `easybuy_order` VALUES ('26', '2', 'admin', '安徽省', '2020-03-12 13:56:17', '136800.000', '1', '1', '238809F31CCF4413AC3BC046709127DF', '0', '2020-03-12 13:56:17', null);
INSERT INTO `easybuy_order` VALUES ('27', '2', 'admin', '北京市海淀区大有庄', '2020-03-12 13:57:40', '134976.000', '1', '1', '5A9F643901D447DC9F66AA1DC66307D3', '0', '2020-03-12 13:57:40', null);
INSERT INTO `easybuy_order` VALUES ('28', '2', 'admin', '安徽省', '2020-03-12 14:06:37', '3952.000', '1', '1', '9820F00844104DA4B1F9804BB0797017', '0', '2020-03-12 14:06:37', null);
INSERT INTO `easybuy_order` VALUES ('29', '137', 'ffff', '安徽省', '2020-03-15 15:37:13', '608.000', '1', '1', 'D3BECCBFE71E45CDA4E9DD682B8B68BC', '0', '2020-03-15 15:37:13', null);

-- ----------------------------
-- Table structure for easybuy_order_detail
-- ----------------------------
DROP TABLE IF EXISTS `easybuy_order_detail`;
CREATE TABLE `easybuy_order_detail` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `orderId` int(10) NOT NULL COMMENT '订单ID',
  `productId` int(10) NOT NULL COMMENT '商品ID',
  `quantity` int(11) NOT NULL COMMENT '数量',
  `cost` float(10,3) NOT NULL COMMENT '金额',
  PRIMARY KEY (`id`),
  KEY `productId` (`productId`),
  KEY `orderId` (`orderId`),
  CONSTRAINT `easybuy_order_detail_ibfk_1` FOREIGN KEY (`productId`) REFERENCES `easybuy_product` (`id`),
  CONSTRAINT `easybuy_order_detail_ibfk_2` FOREIGN KEY (`orderId`) REFERENCES `easybuy_order` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COMMENT='订单详情表';

-- ----------------------------
-- Records of easybuy_order_detail
-- ----------------------------
INSERT INTO `easybuy_order_detail` VALUES ('1', '1', '733', '5', '760.000');
INSERT INTO `easybuy_order_detail` VALUES ('2', '1', '734', '4', '608.000');
INSERT INTO `easybuy_order_detail` VALUES ('3', '1', '735', '1', '152.000');
INSERT INTO `easybuy_order_detail` VALUES ('4', '1', '738', '1', '45.000');
INSERT INTO `easybuy_order_detail` VALUES ('5', '1', '739', '1', '156.000');
INSERT INTO `easybuy_order_detail` VALUES ('6', '2', '755', '1', '8596.000');
INSERT INTO `easybuy_order_detail` VALUES ('7', '3', '733', '1', '152.000');
INSERT INTO `easybuy_order_detail` VALUES ('8', '3', '734', '1', '152.000');
INSERT INTO `easybuy_order_detail` VALUES ('9', '3', '735', '1', '152.000');
INSERT INTO `easybuy_order_detail` VALUES ('10', '4', '733', '5', '760.000');
INSERT INTO `easybuy_order_detail` VALUES ('11', '5', '736', '1', '152.000');
INSERT INTO `easybuy_order_detail` VALUES ('12', '6', '766', '7', '1400.000');
INSERT INTO `easybuy_order_detail` VALUES ('13', '7', '734', '3', '456.000');
INSERT INTO `easybuy_order_detail` VALUES ('14', '8', '734', '1', '152.000');
INSERT INTO `easybuy_order_detail` VALUES ('15', '9', '733', '3', '456.000');
INSERT INTO `easybuy_order_detail` VALUES ('16', '10', '734', '1', '152.000');
INSERT INTO `easybuy_order_detail` VALUES ('23', '11', '772', '1', '999.000');
INSERT INTO `easybuy_order_detail` VALUES ('24', '12', '771', '1', '289.000');
INSERT INTO `easybuy_order_detail` VALUES ('25', '13', '733', '3', '456.000');
INSERT INTO `easybuy_order_detail` VALUES ('26', '14', '734', '2', '304.000');
INSERT INTO `easybuy_order_detail` VALUES ('27', '15', '771', '20', '5780.000');
INSERT INTO `easybuy_order_detail` VALUES ('28', '15', '735', '1', '152.000');
INSERT INTO `easybuy_order_detail` VALUES ('29', '16', '734', '11', '1672.000');
INSERT INTO `easybuy_order_detail` VALUES ('30', '17', '766', '1', '200.000');
INSERT INTO `easybuy_order_detail` VALUES ('31', '18', '780', '1', '29.800');
INSERT INTO `easybuy_order_detail` VALUES ('32', '19', '736', '1', '152.000');
INSERT INTO `easybuy_order_detail` VALUES ('33', '20', '735', '3', '456.000');
INSERT INTO `easybuy_order_detail` VALUES ('34', '21', '748', '30', '15600.000');
INSERT INTO `easybuy_order_detail` VALUES ('35', '22', '765', '500', '2981500.000');
INSERT INTO `easybuy_order_detail` VALUES ('36', '23', '734', '5', '760.000');
INSERT INTO `easybuy_order_detail` VALUES ('37', '24', '735', '28', '4256.000');
INSERT INTO `easybuy_order_detail` VALUES ('38', '25', '734', '900', '136800.000');
INSERT INTO `easybuy_order_detail` VALUES ('39', '26', '734', '900', '136800.000');
INSERT INTO `easybuy_order_detail` VALUES ('40', '27', '734', '888', '134976.000');
INSERT INTO `easybuy_order_detail` VALUES ('41', '28', '736', '26', '3952.000');
INSERT INTO `easybuy_order_detail` VALUES ('42', '29', '734', '4', '608.000');

-- ----------------------------
-- Table structure for easybuy_product
-- ----------------------------
DROP TABLE IF EXISTS `easybuy_product`;
CREATE TABLE `easybuy_product` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '商品编号',
  `name` varchar(20) NOT NULL COMMENT '商品名字',
  `description` varchar(100) DEFAULT NULL COMMENT '商品描述',
  `price` float(10,3) NOT NULL COMMENT '商品价格',
  `stock` int(10) NOT NULL COMMENT '商品库存',
  `categoryLevel1` int(10) NOT NULL COMMENT '所属ID',
  `categoryLevel2` int(10) DEFAULT NULL COMMENT '所属二级分类ID',
  `categoryLevel3` int(10) DEFAULT NULL COMMENT '所属三级分类ID',
  `fileName` varchar(200) NOT NULL COMMENT '上传的文件名',
  `isDelete` int(1) DEFAULT '0' COMMENT '是否删除（1删除,0未删除）',
  PRIMARY KEY (`id`),
  KEY `categoryLevel1` (`categoryLevel1`),
  KEY `categoryLevel2` (`categoryLevel2`),
  KEY `categoryLevel3` (`categoryLevel3`),
  CONSTRAINT `easybuy_product_ibfk_1` FOREIGN KEY (`categoryLevel1`) REFERENCES `easybuy_product_category` (`id`),
  CONSTRAINT `easybuy_product_ibfk_2` FOREIGN KEY (`categoryLevel2`) REFERENCES `easybuy_product_category` (`id`),
  CONSTRAINT `easybuy_product_ibfk_3` FOREIGN KEY (`categoryLevel3`) REFERENCES `easybuy_product_category` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=796 DEFAULT CHARSET=utf8 COMMENT='商品表';

-- ----------------------------
-- Records of easybuy_product
-- ----------------------------
INSERT INTO `easybuy_product` VALUES ('733', '香奈儿', '订单', '152.000', '83', '548', '654', '692', '27A1789ED5764D82A5506DF3DC3933F9.jpg', '1');
INSERT INTO `easybuy_product` VALUES ('734', '洗面奶', '', '152.000', '956', '548', '654', '655', 'D6C9BD438C5643D6B1A6C52E5426FE22.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('735', '啫喱水', '', '152.000', '996', '548', '654', '655', '1A836D2B3A3348DDAB19807E6CEA8028.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('736', '香水5852', '', '152.000', '999', '548', '654', '655', '4D9499BAD92A42D291094E797BA2EA3F.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('737', '香水11', '', '152.000', '111', '548', '654', '655', 'A9924F9DB68B4DF99FDBF05902075AF0.jpg', '1');
INSERT INTO `easybuy_product` VALUES ('738', '润肤露', '', '45.000', '109', '548', '654', '655', '3B059EDB5237407980458CE9EA9D3204.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('739', '洁面装', '', '156.000', '99', '548', '654', '655', 'A62C6DF55116440CA3DE9DB37901ED4F.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('740', '电饭锅', '', '158.000', '100', '628', '656', '659', '40C3B76BA31246618E3CFC8723D33517.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('741', '婴儿喂奶装', '', '569.000', '100', '676', '637', '637', '401004B3D47C4C6FB1BC5EF19C21FC77.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('742', '坚果套餐', '', '158.000', '1000', '660', '661', '662', 'E03D74145A034F6D909879829CB99D80.jpg', '1');
INSERT INTO `easybuy_product` VALUES ('743', '超甜蜜崭', '', '589.000', '1000', '660', '661', '663', '7121E55099FC477680B1229205CE3D29.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('744', '华为5555', '', '589.000', '1000', '670', '671', '672', 'F24B4140A2284B3788A38F3B5AD1809A.jpg', '1');
INSERT INTO `easybuy_product` VALUES ('745', '荣耀3C', '', '589.000', '100', '670', '671', '672', 'F3921E12552A4D0AA3F75467B146A959.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('746', '小米手环', '', '963.000', '100', '670', '674', '675', '72F75A371B0B4C26A7F72FAAEF96FC68.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('747', '华为2265', '', '896.000', '1000', '670', '671', '673', '161F355A8A8549BA8F7F4CE3B4F07E40.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('748', '越南坚果', '', '520.000', '1000', '660', '661', '662', 'CBC98D3C9E544830821632F5C313D93E.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('749', '日本进口马桶', '', '5866.000', '100', '628', '657', '657', 'A5AF40825E6940B2A59A040100E181A8.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('750', '联想Y系列', '', '569.000', '1000', '670', '690', '691', '956DB0BEC41B41B8A06C05C950130E23.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('751', '脑白金1号', '', '589.000', '1000', '676', '677', '680', '66E96AF9E9714A5C9EA901811173D662.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('752', '莫里斯按', '', '589.000', '1000', '676', '678', '678', 'A7436BC607E74C81B392DCFE69D4AEAB.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('753', '三鹿好奶粉', '', '859.000', '100', '676', '679', '638', '3C465E7B8A324A8DA2A2EEE202E36166.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('754', '儿童牛奶', '', '5896.000', '100', '676', '679', '638', 'D1AC9AE71ED348FA8D880FD4279D3422.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('755', '软沙发', '', '8596.000', '99', '628', '696', '696', 'ED7921DE40FC47E18365754709A21194.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('756', '收纳盒', '', '5966.000', '100', '628', '696', '696', 'DB86CA25CA4F4B4AA906F46BE542C6A6.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('757', '洗衣液', '', '58.000', '1000', '628', '696', '696', 'E6CCDC343ACC471C908E9748776C6421.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('758', '红短沙发', '', '596.000', '123', '628', '696', '696', 'BD5C77465DC2466BBCE7F95FB9764392.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('759', '新西兰奶粉', '', '5896.000', '100', '676', '679', '638', '9ED375098D42497B8FC33167E06D0EE8.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('760', '婴儿车', '', '11000.000', '100', '681', '682', '687', '1DBC0930641D43C29D74A9E1B40FEEBB.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('761', '夏款婴儿车', '', '963.000', '100', '681', '682', '688', '16290C4DBEAC4F00A636667019621468.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('762', '抗压旅行箱', '', '569.000', '1000', '681', '683', '685', '272CC434BE7A4469AB0E7882BD1A85FF.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('763', '透明手提箱', '', '89.000', '1000', '681', '683', '684', 'EAA8E66259BF4239B4A2237B62520EF1.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('764', '婴儿果粉', '', '5896.000', '1000', '660', '661', '662', '08BE30BF7B5F4930B0093D8CC4056057.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('765', '椰子粉', '', '5963.000', '1000', '660', '661', '662', '9C006B8BD1AD45398F474A8471ADC50B.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('766', '坚果蛋糕', '', '200.000', '92', '660', '661', '663', '2E5A16E21E0640E0BAE03E9B995DCD28.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('767', '编制手提箱', '', '5896.000', '1000', '681', '682', '688', '2E1D2A5E65A94FEEA17C72E47C530057.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('768', '纸箱', '', '59.000', '100', '681', '682', '687', '443E5A4122064209AFE89250179A2FF0.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('769', '健胃液', '', '152.000', '1000', '676', '679', '679', '30B5547CD7384DAA8A2F4F4D8C0BBF89.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('770', '联想NTC', '', '8596.000', '100', '670', '671', '673', '48BC371A85A548B7A7589E3F542D911D.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('771', '小Biu音箱', '【能陪宝宝聊天的音箱】唱儿歌讲故事，独有百度百科大全，智能对话贴心陪伴', '289.000', '9976', '670', '697', '698', '211EE842C5AF44E58444BC004732B433.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('772', '海尔（Haier）空调', '经典定频，性价比好货！快速制冷，智能物联，一键自清洁', '999.000', '999', '670', '697', '699', 'D846E58CA6AB41DD9D9DC5EE4E17C248.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('773', '惠普HP光影精灵4代', '】暗影兄弟版！新品来袭！八代CPU，1050Ti，性能大提升！144电竞屏，刷新装备好时机', '6999.000', '500', '670', '690', '700', '963F109771FF4751A8DDE8DF7D535FA4.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('774', '布艺沙发', '618年中促 每1000减120可叠加可用券 送家电', '2498.000', '2000', '628', '696', '701', 'C2F93BE449614BC1ABB5CB2418704FD7.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('775', '榨汁机RZ-180V1迷你学生小型', '【苏宁自营】充电式迷你榨汁机，可拆卸刀头，3800毫安可供手机充电', '109.000', '1000', '628', '657', '702', '75F5BADC3B57421694FB675F62D68150.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('776', '美素佳儿（Friso）金装幼儿配方奶粉', '荷兰原装进口 自家牧场 鲜奶到罐装一次完成', '189.000', '10000', '676', '638', '703', '40D54896212E4AADBB81F659016CAC96.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('777', '汤臣倍健R多种维生素', '买送共150片 适合需要补充多种维生素的4-10岁儿童', '96.000', '5025', '676', '679', '704', '0263385C416D49EA949784870F54C221.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('778', '新生儿婴幼儿衣服套装', '彩棉面料，舒适柔软，适合新生儿宝宝穿着 ，宝宝满月礼，送礼贴心。', '99.000', '100', '676', '637', '705', 'C86EA95DCF2B4FEAA58FB37EDFE0FB2C.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('779', 'BY-HEALTH 维生素B', '补充B族营养 非swisse 非药 ', '188.000', '5258', '676', '678', '706', 'E79466D61DE7496FB9809E9B2482F54C.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('780', '卡乐米斯夹心饼干', '全店满68元包邮 马来西亚进口夹心饼干  ', '29.800', '5004', '660', '661', '707', '8FF6753D69CF41AB87B9316500BC1039.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('781', '钙芝高钙奶酪味威化饼干', '原装进口 香脆咖啡味 酥脆美味 ', '15.800', '9999', '660', '661', '707', 'C8D11FA4F99D4A8CAB8F6DC92293BB52.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('782', '印尼进口richoco丽巧克', '巧克力口味 ', '26.000', '4545', '660', '661', '707', '56F52AB1448441BF9BD4BC01AED2A048.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('783', '威海紫光金奥力牌浓缩大豆卵磷脂颗粒粉', '成人中老年人降血脂延缓衰老心脑血管保健品可搭配降血压降三高茶', '188.000', '4540', '676', '677', '708', '22D8ED6D2A1B48D0A9AE3E7B02283FE5.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('784', '飞亚达(FIYTA)', '【顺丰包邮】【全国两年联保】  ', '1749.000', '4540', '548', '709', '710', '474AB323732E4C6684575F2BED25708E.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('785', '女士T恤圆领', '美国男装大牌 时尚休闲 精致做工', '999.000', '5450', '548', '709', '711', '5E2A5706468C43FBB290D31DA68A46FD.jpg', '0');
INSERT INTO `easybuy_product` VALUES ('786', '华为荣耀手环3 碳晶黑 标准版', '50米防水，实时心率监控，超长待机30天续航！ ', '998.000', '4545', '670', '674', '712', 'E140FF458F0D4C35BD4C8D17E863483A.jpg', '1');
INSERT INTO `easybuy_product` VALUES ('787', 'OSM 欧诗漫珍珠白套装', '店铺优惠券 部分商品满 158-10；258-20；458-50  ', '489.000', '2323', '548', '654', '713', '7D111E965E0442A087D5E4D2677B71D0.jpg', '1');
INSERT INTO `easybuy_product` VALUES ('788', '家用商品', '', '111.000', '999', '628', '656', '658', 'EAA77C5440874F8F87F8A844C71DF7E0.jpg', '1');
INSERT INTO `easybuy_product` VALUES ('789', '手套', '', '50.000', '100', '548', '654', '655', '0F325B7CD0B945FDAFD7125DD998EA0D.jpg', '1');
INSERT INTO `easybuy_product` VALUES ('790', '香水211', '', '100.000', '100', '548', '654', '655', '5006B42BA844499383679B4E8D6D13F8.jpg', '1');
INSERT INTO `easybuy_product` VALUES ('791', '哈', 'aaa', '200.000', '1', '548', '654', '655', '6C5D6965D2A349BB94870DA8E3514D70.jpg', '1');
INSERT INTO `easybuy_product` VALUES ('792', 'j', 'aa', '1.000', '1', '548', '654', '655', '8DBFEBF2494846F9876DF3A6832C6D45.jpg', '1');
INSERT INTO `easybuy_product` VALUES ('793', '奶粉', 'aaa', '1.000', '1', '548', '638', '703', 'EEB328FE3AC74B36B11F033DFFE09B28.jpg', '1');
INSERT INTO `easybuy_product` VALUES ('794', '奶粉', '啊', '1.000', '1', '548', '638', '703', '0B217F49E5CC44608933F23E8310DF26.jpg', '1');
INSERT INTO `easybuy_product` VALUES ('795', '奶粉', '超级奶粉', '99999.000', '1', '548', '637', '669', '2982FCF0400D402FBF1B0B840172198C.jpg', '0');

-- ----------------------------
-- Table structure for easybuy_product_category
-- ----------------------------
DROP TABLE IF EXISTS `easybuy_product_category`;
CREATE TABLE `easybuy_product_category` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(20) NOT NULL COMMENT '名字',
  `parentId` int(10) NOT NULL COMMENT '父分类',
  `type` int(1) DEFAULT NULL COMMENT '类型（1一级分类,2二级分类,3三级分类）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=722 DEFAULT CHARSET=utf8 COMMENT='商品分类表';

-- ----------------------------
-- Records of easybuy_product_category
-- ----------------------------
INSERT INTO `easybuy_product_category` VALUES ('548', '化妆品', '0', '1');
INSERT INTO `easybuy_product_category` VALUES ('628', '家用商品', '0', '1');
INSERT INTO `easybuy_product_category` VALUES ('637', '婴儿用品', '548', '2');
INSERT INTO `easybuy_product_category` VALUES ('638', '奶粉', '548', '2');
INSERT INTO `easybuy_product_category` VALUES ('654', '面部护理', '548', '2');
INSERT INTO `easybuy_product_category` VALUES ('655', '少女派', '654', '3');
INSERT INTO `easybuy_product_category` VALUES ('656', '餐具', '628', '2');
INSERT INTO `easybuy_product_category` VALUES ('657', '卫具', '628', '2');
INSERT INTO `easybuy_product_category` VALUES ('658', '叉子', '719', '3');
INSERT INTO `easybuy_product_category` VALUES ('659', '锅11', '656', '3');
INSERT INTO `easybuy_product_category` VALUES ('660', '进口食品', '0', '1');
INSERT INTO `easybuy_product_category` VALUES ('661', '零食/糖果/巧克力', '660', '2');
INSERT INTO `easybuy_product_category` VALUES ('662', '坚果', '661', '3');
INSERT INTO `easybuy_product_category` VALUES ('663', '蜜饯', '661', '3');
INSERT INTO `easybuy_product_category` VALUES ('669', '孕期教育', '637', '3');
INSERT INTO `easybuy_product_category` VALUES ('670', '电子商品', '0', '1');
INSERT INTO `easybuy_product_category` VALUES ('671', '手机', '670', '2');
INSERT INTO `easybuy_product_category` VALUES ('672', '华为手机', '671', '3');
INSERT INTO `easybuy_product_category` VALUES ('673', '联想手机', '671', '3');
INSERT INTO `easybuy_product_category` VALUES ('674', '手环', '670', '2');
INSERT INTO `easybuy_product_category` VALUES ('675', '小米手环', '674', '3');
INSERT INTO `easybuy_product_category` VALUES ('676', '保健食品/母婴用品', '0', '1');
INSERT INTO `easybuy_product_category` VALUES ('677', '老年保健品', '676', '2');
INSERT INTO `easybuy_product_category` VALUES ('678', '中年营养品', '676', '2');
INSERT INTO `easybuy_product_category` VALUES ('679', '儿童保健品', '676', '2');
INSERT INTO `easybuy_product_category` VALUES ('680', '脑白金', '677', '3');
INSERT INTO `easybuy_product_category` VALUES ('681', '箱包', '0', '1');
INSERT INTO `easybuy_product_category` VALUES ('682', '旅行箱', '681', '2');
INSERT INTO `easybuy_product_category` VALUES ('683', '手提箱', '681', '2');
INSERT INTO `easybuy_product_category` VALUES ('684', '大型', '683', '3');
INSERT INTO `easybuy_product_category` VALUES ('685', '小型', '683', '3');
INSERT INTO `easybuy_product_category` VALUES ('686', '中型', '683', '3');
INSERT INTO `easybuy_product_category` VALUES ('687', '大型', '682', '3');
INSERT INTO `easybuy_product_category` VALUES ('688', '中型', '682', '3');
INSERT INTO `easybuy_product_category` VALUES ('689', '小型', '682', '3');
INSERT INTO `easybuy_product_category` VALUES ('690', '电脑', '670', '2');
INSERT INTO `easybuy_product_category` VALUES ('691', '联想电脑', '690', '3');
INSERT INTO `easybuy_product_category` VALUES ('692', '刀叉', '656', '3');
INSERT INTO `easybuy_product_category` VALUES ('696', '客厅专用', '628', '2');
INSERT INTO `easybuy_product_category` VALUES ('697', '智能家电', '670', '2');
INSERT INTO `easybuy_product_category` VALUES ('698', '蓝牙音箱', '697', '3');
INSERT INTO `easybuy_product_category` VALUES ('699', '冰箱/空调/洗衣机', '697', '3');
INSERT INTO `easybuy_product_category` VALUES ('700', '惠普笔记本', '690', '3');
INSERT INTO `easybuy_product_category` VALUES ('701', '沙发', '696', '3');
INSERT INTO `easybuy_product_category` VALUES ('702', '榨汁机', '657', '3');
INSERT INTO `easybuy_product_category` VALUES ('703', '原装进口奶粉', '638', '3');
INSERT INTO `easybuy_product_category` VALUES ('704', '咀嚼片(儿童型)', '679', '3');
INSERT INTO `easybuy_product_category` VALUES ('705', '衣服套装', '637', '3');
INSERT INTO `easybuy_product_category` VALUES ('706', '老年保健品营养品', '678', '3');
INSERT INTO `easybuy_product_category` VALUES ('707', '饼干', '661', '3');
INSERT INTO `easybuy_product_category` VALUES ('708', '冲剂', '677', '3');
INSERT INTO `easybuy_product_category` VALUES ('709', '当季流行', '548', '2');
INSERT INTO `easybuy_product_category` VALUES ('710', '手表', '709', '3');
INSERT INTO `easybuy_product_category` VALUES ('711', '女士下装', '709', '3');
INSERT INTO `easybuy_product_category` VALUES ('712', '华为荣耀手环', '674', '3');
INSERT INTO `easybuy_product_category` VALUES ('713', '护肤套装', '654', '3');
INSERT INTO `easybuy_product_category` VALUES ('714', '家用商品', '0', '1');
INSERT INTO `easybuy_product_category` VALUES ('715', '家用商品', '628', '2');
INSERT INTO `easybuy_product_category` VALUES ('717', '家用商品', '0', '1');
INSERT INTO `easybuy_product_category` VALUES ('718', '111', '0', '1');
INSERT INTO `easybuy_product_category` VALUES ('719', '222', '718', '2');
INSERT INTO `easybuy_product_category` VALUES ('721', '奶粉', '0', '1');

-- ----------------------------
-- Table structure for easybuy_user
-- ----------------------------
DROP TABLE IF EXISTS `easybuy_user`;
CREATE TABLE `easybuy_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户名',
  `userName` varchar(20) NOT NULL COMMENT '用户真实姓名',
  `loginName` varchar(20) NOT NULL COMMENT '用户登陆用户名',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `sex` int(1) NOT NULL COMMENT '性别',
  `identityCode` bigint(18) DEFAULT NULL COMMENT '身份证号',
  `email` varchar(80) NOT NULL COMMENT '电子邮箱',
  `mobile` varchar(11) DEFAULT NULL COMMENT '手机',
  `type` int(1) NOT NULL COMMENT '用户类型',
  `codeUrl` varchar(255) NOT NULL COMMENT '随机码',
  `activated` int(11) NOT NULL COMMENT '登录状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=139 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of easybuy_user
-- ----------------------------
INSERT INTO `easybuy_user` VALUES ('2', '究极管理员', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '1', '130406198302141869', 'hello11@bdqn.com', '15832335151', '1', '', '1');
INSERT INTO `easybuy_user` VALUES ('10', '程广宁a4', 'cgn', 'e10adc3949ba59abbe56e057f20f883e', '1', '140225189987854589', '1044732267@qq.com', '13366055012', '0', '', '1');
INSERT INTO `easybuy_user` VALUES ('11', '韩语良', 'hyl', 'e10adc3949ba59abbe56e057f20f883e', '1', '140225198874584539', '1044732267@qq.com', '13366055010', '0', '', '1');
INSERT INTO `easybuy_user` VALUES ('12', '陈康', 'ck', 'e10adc3949ba59abbe56e057f20f883e', '1', '140225189987854589', '1044732267@qq.com', '13366055010', '0', '', '1');
INSERT INTO `easybuy_user` VALUES ('13', '康有沈', 'kys', 'e10adc3949ba59abbe56e057f20f883e', '1', '140225198551254152', '1044732267@qq.com', '13366055010', '0', '', '1');
INSERT INTO `easybuy_user` VALUES ('14', '沈白', 'sb', 'e10adc3949ba59abbe56e057f20f883e', '1', '140225158987854589', '1044732267@qq.com', '13366055010', '0', '', '1');
INSERT INTO `easybuy_user` VALUES ('16', '李高伟', 'lgw', 'e10adc3949ba59abbe56e057f20f883e', '1', '140225189987854589', '1011322658@qq.com', '13369985545', '0', '', '1');
INSERT INTO `easybuy_user` VALUES ('18', '尚泽忠', 'szz', 'e10adc3949ba59abbe56e057f20f883e', '1', '140225198810013745', '1044888844@qq.com', '13366528458', '0', '', '1');
INSERT INTO `easybuy_user` VALUES ('21', 'a', 'aa', '123456', '1', '11111111111', 'a@qq.com', '11111111111', '1', '', '1');
INSERT INTO `easybuy_user` VALUES ('23', 'aaa', 'aaaa', '123456', '1', '33333333333333', 'aaa@qq.com', '33333333333', '1', '', '1');
INSERT INTO `easybuy_user` VALUES ('24', 'aaaa', 'aaaaa', '123456', '1', '22222222222222', 'aaaa@qq.com', '44444444444', '1', '', '1');
INSERT INTO `easybuy_user` VALUES ('25', 'aaaaa', 'aaaaa', '123456', '1', '2222222222222', 'aaaaa@qq.com', '55555555555', '1', '', '1');
INSERT INTO `easybuy_user` VALUES ('26', 'aaaaaa', 'aaaaaa', '123456', '1', '444444444444444', 'aaaaaa@qq.com', '66666666666', '1', '', '1');
INSERT INTO `easybuy_user` VALUES ('27', 'aaaaaaa', 'aaaaaaa', '123456', '1', '55555555555555555', 'aaaaaaa@qq.com', '77777777777', '1', '', '1');
INSERT INTO `easybuy_user` VALUES ('31', 'xiaoma', '小泽玛利亚', '123456', '1', '341224500029990011', '123444@qq.com', '12345654567', '1', '', '1');
INSERT INTO `easybuy_user` VALUES ('32', 'kaka', '啊啊啊', '123456', '2', '345554555545554455', '123456@qq.com', '12345678788', '0', '', '1');
INSERT INTO `easybuy_user` VALUES ('33', 'hu', 'dsss', '123456', '1', '111111111111111111', '137@163.com', '13522233344', '0', '', '1');
INSERT INTO `easybuy_user` VALUES ('34', 'de', 'ww', '123456', '1', '111111111111111111', '137@163.com', '13423456545', '0', '', '1');
INSERT INTO `easybuy_user` VALUES ('35', 'ss', 'sss', '123456', '1', '111111111111111111', '137@163.com', '13422223333', '0', '', '1');
INSERT INTO `easybuy_user` VALUES ('41', 're', 're', '123456', '1', '111111111111111111', '137@163.com', '13233332222', '0', '', '1');
INSERT INTO `easybuy_user` VALUES ('42', 're', 're', '123456', '1', '111111111111111111', '137@163.com', '13233332222', '0', '', '1');
INSERT INTO `easybuy_user` VALUES ('43', 'dd', 'dd', '123456', '1', '111111111111111111', '137@163.com', '13255544445', '0', '', '1');
INSERT INTO `easybuy_user` VALUES ('44', 'www', 'ww', '123456', '1', '111111111111111111', '137@168.com', '13244445555', '0', '', '1');
INSERT INTO `easybuy_user` VALUES ('45', 'admin', 'tt', '123456', '1', '222222222222222222', '137@168.com', '13255555555', '0', '', '1');
INSERT INTO `easybuy_user` VALUES ('46', 'hh', 'hh', '123456', '1', '111111111111111111', '137@163.com', '13244444444', '0', '', '1');
INSERT INTO `easybuy_user` VALUES ('47', 'ff', 'ff', '123456', '1', '111111111111111111', '123@qq.com', '12344445555', '0', '', '1');
INSERT INTO `easybuy_user` VALUES ('48', 'xiaoagiao', 'Giao', '123456', '1', '222222222222222222', '12222@qq.com', '12345676767', '0', '', '1');
INSERT INTO `easybuy_user` VALUES ('49', 'giao', 'Giao', '123456', '1', '111111111111111111', '12222@qq.com', '12344567898', '0', '', '1');
INSERT INTO `easybuy_user` VALUES ('50', 'qwer', 'qwer', '123456', '1', '111111111111111111', '1222@qq.com', '13876540984', '0', '', '1');
INSERT INTO `easybuy_user` VALUES ('52', 'sdad', 'ASDASD', '123456', '2', '341224555555555555', '1231231231@qq.com', '13864750946', '0', '', '1');
INSERT INTO `easybuy_user` VALUES ('53', 'zzzz', 'ZZZZ', '123456', '1', '321111111111111111', '12344@qq.com', '13855556666', '0', '', '1');
INSERT INTO `easybuy_user` VALUES ('54', 'xxxx', 'XXXXX', '123456', '1', '233333333333331111', '122121@qq.com', '13845679876', '0', '', '1');
INSERT INTO `easybuy_user` VALUES ('57', 'Chang', '常硕', '123456', '1', '111111111111111111', '111111@111.com', '13977864678', '1', '', '1');
INSERT INTO `easybuy_user` VALUES ('58', '李四', 'lis', '123456', '1', '111111111111111111', '1395583@163.com', '13111111111', '1', '8c144260be30450689f9c32d32bf51ced99fe3832dd041fcb5716467b3c09823', '1');
INSERT INTO `easybuy_user` VALUES ('60', '李四', 'liss', 'e10adc3949ba59abbe56e057f20f883e', '1', '111111111111111111', '13955839036@163.com', '11111111111', '0', 'd850fc71b021435593b037cd447c2e4e4b6384447a2244d586357b7e2d5d17bf', '0');
INSERT INTO `easybuy_user` VALUES ('61', '李四', 'lissss', 'e10adc3949ba59abbe56e057f20f883e', '1', '111111111111111111', '13955839036@163.com', '11111111111', '0', '2668670e1e8c451a8ca7d5e669134d06dedbea084d764955a9c97048882ba5a9', '1');
INSERT INTO `easybuy_user` VALUES ('62', '张三', 'zhangs', 'e10adc3949ba59abbe56e057f20f883e', '1', '111111111111111111', '13955839036@163.com', '11111111111', '0', 'ab63672e6cb445729bd76ebe1ccd3e46671cf3c5459e4975b6886fc52f9050bd', '1');
INSERT INTO `easybuy_user` VALUES ('63', '张三', 'zhangss', 'e10adc3949ba59abbe56e057f20f883e', '1', '111111111111111111', '13955839036@163.com', '13111111111', '0', 'c39ec71872794b30b4d2bf42cd74ccafd53cb1e640f94dbdb340ce74120c988d', '1');
INSERT INTO `easybuy_user` VALUES ('65', '王二', 'wangee', 'e10adc3949ba59abbe56e057f20f883e', '1', '111111111111111111', '13955839036@163.com', '11111111111', '0', '6416763aab804f818a181fb8cab68ee1f87943ccd78f489fbbedbf1cdcba08ea', '1');
INSERT INTO `easybuy_user` VALUES ('66', '王二', 'wangeee', 'e10adc3949ba59abbe56e057f20f883e', '2', '111111111111111111', '13955839036@163.com', '11111111111', '0', '9341d1c50643494e9c1c8c8522a60a7f5b02a3fa7db44869accacf4ee5fb846e', '1');
INSERT INTO `easybuy_user` VALUES ('67', '王二', 'wangeeee', 'e10adc3949ba59abbe56e057f20f883e', '1', '111111111111111111', '13955839036@163.com', '11111111111', '0', '977345734e7f4ae6a6f5e39e0fa8c151266249d7ecac499cabf0eea12ef09efa', '1');
INSERT INTO `easybuy_user` VALUES ('68', '123456', 'q', 'e10adc3949ba59abbe56e057f20f883e', '1', '111111111111111111', '13955839036@163.com', '11111111111', '1', '5b51dbe988524df8a1f3436ff8f38d2a524b9549fa9a44129ab426b3b29b3d04', '1');
INSERT INTO `easybuy_user` VALUES ('69', '啊qq', 'qq', 'e10adc3949ba59abbe56e057f20f883e', '1', '111111111111111111', '13955839036@163.com', '11111111111', '0', 'dc376331601f423d8a5e84da0799cb4d59a4cf7b7abe40c298de94c512febf5f', '0');
INSERT INTO `easybuy_user` VALUES ('70', '啊qqq', 'qqq', 'e10adc3949ba59abbe56e057f20f883e', '1', '111111111111111111', '13955839036@163.com', '11111111111', '0', '6f8d9ffd601045e5a02f1d50e52457b7933ec770813941b29562b369d1687ca6', '0');
INSERT INTO `easybuy_user` VALUES ('75', '张三', 'zs', '123456', '1', '1111111111111111111', '13955839036@163.com', '11111111111', '1', '21saweew2adsr3rw22rwra', '1');
INSERT INTO `easybuy_user` VALUES ('78', '十一', 'cs', 'e10adc3949ba59abbe56e057f20f883e', '1', '111111111111111111', '13955839036@163.com', '11111111111', '1', '00519ff917b643a7aa08bea1e9fc8504442b02f78127438ba0fb7c331d37f46d', '1');
INSERT INTO `easybuy_user` VALUES ('87', '二狗', 'lqq', 'e10adc3949ba59abbe56e057f20f883e', '1', '342601155555555555', 'a13955839036@163.com', '13866967588', '1', 'ab3d11b89fcb47038bfe49218188a6d9a6483ac0ffdf481588ff6ca6c50f7ebc', '1');
INSERT INTO `easybuy_user` VALUES ('92', 'admin', 'GiaoGiao', 'e10adc3949ba59abbe56e057f20f883e', '1', '111111111111111111', '13955839036@163.com', '13988888888', '1', 'b1370582741e42f4a5805c9d8ac43ca53646c3d413ce4ef5bfbc85f0db63884e', '0');
INSERT INTO `easybuy_user` VALUES ('96', 'admin', 'Giao', '123456', '1', '111111111111111111', '13955839036@163.com', '11111111111', '1', '70cb36928e204b24b3dd55866007ffe1b275c2a4a29841d1a9f800b4428fc1b9', '0');
INSERT INTO `easybuy_user` VALUES ('97', '健桑', 'jian', 'e10adc3949ba59abbe56e057f20f883e', '1', '111111111111111111', '13955839036@163.com', '13955783930', '0', 'e2f27d4ceaf14d6ab46a12937a609a91ea9ad0ab9e8344b8805765534ef9fc7c', '1');
INSERT INTO `easybuy_user` VALUES ('98', 'cc', 'cc', 'e10adc3949ba59abbe56e057f20f883e', '1', '111111111111111111', '13955839036@163.com', '13955839036', '0', '3dfe6a79559a47ec84479284a3b8c0c87a30a4fa8a9b47a48bb37c0aa558a368', '0');
INSERT INTO `easybuy_user` VALUES ('99', 'cc', 'cc', 'e10adc3949ba59abbe56e057f20f883e', '1', '111111111111111111', '13955839036@163.com', '13955839036', '0', '910f0ec882ab4eb1b669ce614380a54e587f5b9099d24431b14a33636532ab3f', '0');
INSERT INTO `easybuy_user` VALUES ('100', 'cc', 'cc', 'e10adc3949ba59abbe56e057f20f883e', '1', '111111111111111111', '13955839036@163.com', '13955839036', '0', '22d21c5368cb4c6db45889ac98d357a35260d206d1634c7193386ca0b5b1ad67', '0');
INSERT INTO `easybuy_user` VALUES ('101', 'cc', 'cc', 'e10adc3949ba59abbe56e057f20f883e', '1', '111111111111111111', '13955839036@163.com', '13955839036', '0', 'c99498115232428fb81ca8fdd851bf50226d3b9da837441cb8968f35d7f06d9c', '0');
INSERT INTO `easybuy_user` VALUES ('102', 'cc', 'cc', 'e10adc3949ba59abbe56e057f20f883e', '1', '111111111111111111', '13955839036@163.com', '13955839036', '0', 'c93bea107549447691d080593cbb01b726d10bc15c1b4726adc501197079c094', '0');
INSERT INTO `easybuy_user` VALUES ('103', 'cc', 'cc', 'e10adc3949ba59abbe56e057f20f883e', '1', '111111111111111111', '13955839036@163.com', '13955839036', '0', 'eb126ac585b34769be0979dea3c7533a6ca317c79c3e479d823fdd2bab7a5cc7', '0');
INSERT INTO `easybuy_user` VALUES ('104', 'cc', 'cc', 'e10adc3949ba59abbe56e057f20f883e', '1', '111111111111111111', '13955839036@163.com', '13955839036', '0', 'c0d613d286d54507bbdcf040d7de25d46f30a0eaa1334dd99ffb2d945fefde37', '0');
INSERT INTO `easybuy_user` VALUES ('105', 'cc', 'cc', 'e10adc3949ba59abbe56e057f20f883e', '1', '111111111111111111', '13955839036@163.com', '13955839036', '0', 'abebe090590f4331b178eb47737a5777351ee59160844722b6eb460907821353', '0');
INSERT INTO `easybuy_user` VALUES ('106', 'cc', 'cc', 'e10adc3949ba59abbe56e057f20f883e', '1', '111111111111111111', '13955839036@163.com', '13955839036', '0', '532fecda241a4c069be80691dbdbe702cedc6025a2704af3995028132ed6e25e', '0');
INSERT INTO `easybuy_user` VALUES ('107', 'cc', 'cc', 'e10adc3949ba59abbe56e057f20f883e', '1', '111111111111111111', '13955839036@163.com', '13955839036', '0', '5c1885d544824b7494952a9dd5738d3a0e67c9782de14b298fb3e4248a2dda01', '0');
INSERT INTO `easybuy_user` VALUES ('108', 'cc', 'cc', 'e10adc3949ba59abbe56e057f20f883e', '1', '111111111111111111', '13955839036@163.com', '13955839036', '0', '549341045551421081d3e1d044443cb8633f580d58d743238dae768825571451', '0');
INSERT INTO `easybuy_user` VALUES ('109', 'cc', 'cc', 'e10adc3949ba59abbe56e057f20f883e', '1', '111111111111111111', '13955839036@163.com', '13955839036', '0', '07026c469e174dea80231c71a649a0ce2ef30a2ea94d45b5931d16dea275c649', '0');
INSERT INTO `easybuy_user` VALUES ('110', 'cc', 'cc', 'e10adc3949ba59abbe56e057f20f883e', '1', '111111111111111111', '13955839036@163.com', '13955839036', '0', '31c83ad89de4451fbb18372895829ccd4cd404f2320f40e4b9e58643d012ce3c', '0');
INSERT INTO `easybuy_user` VALUES ('111', 'cc', 'cc', 'e10adc3949ba59abbe56e057f20f883e', '1', '111111111111111111', '13955839036@163.com', '13955839036', '0', 'b47a7cba1c3b43649139fec2d679911ed65e1f4bd4344f9695982d21e1082a9c', '0');
INSERT INTO `easybuy_user` VALUES ('112', 'cc', 'cc', 'e10adc3949ba59abbe56e057f20f883e', '1', '111111111111111111', '13955839036@163.com', '13955839036', '0', '40f6792a67204c7f9c064cad0ac5f1c2240bfe239a424cebb8b2f28a7b38eed4', '0');
INSERT INTO `easybuy_user` VALUES ('113', 'cc', 'cc', 'e10adc3949ba59abbe56e057f20f883e', '1', '111111111111111111', '13955839036@163.com', '13955839036', '0', '12e65ceb3d75459481ac23060267ec554bce9d6a30704f1dbeea478ccc319edb', '0');
INSERT INTO `easybuy_user` VALUES ('114', 'cc', 'cc', 'e10adc3949ba59abbe56e057f20f883e', '1', '111111111111111111', '13955839036@163.com', '13955839036', '0', '8de1f33982694d68ae4725e2b8dfcd0275568710a6e1419a8e1d894b83a89d8a', '0');
INSERT INTO `easybuy_user` VALUES ('115', 'cc', 'cc', 'e10adc3949ba59abbe56e057f20f883e', '1', '111111111111111111', '13955839036@163.com', '13955839036', '0', 'fbc9eab2e0ce45148701edc724c5ecd5a697b08cb9504be3af11ae59f58c37db', '0');
INSERT INTO `easybuy_user` VALUES ('116', 'cc', 'cc', 'e10adc3949ba59abbe56e057f20f883e', '1', '111111111111111111', '13955839036@163.com', '13955839036', '0', '214e07b0bb9c4c31bc188972bdf8ccf581f40a4c98f443808bd2bdce8ce45785', '0');
INSERT INTO `easybuy_user` VALUES ('117', 'cc', 'cc', 'e10adc3949ba59abbe56e057f20f883e', '1', '111111111111111111', '13955839036@163.com', '13955839036', '0', 'c4bf86edbb2c43188231285707f7978e6771583fa0064044b76005df2e9fc538', '0');
INSERT INTO `easybuy_user` VALUES ('118', 'cc', 'cc', 'e10adc3949ba59abbe56e057f20f883e', '1', '111111111111111111', '13955839036@163.com', '13955839036', '0', '84a5897ada534167b4680784dbb0ca455c02308d23014ceeadcca670b0102064', '0');
INSERT INTO `easybuy_user` VALUES ('119', 'cc', 'cc', 'e10adc3949ba59abbe56e057f20f883e', '1', '111111111111111111', '13955839036@163.com', '13955839036', '0', '1a1869a19e134e608696a2aa9b5cc3a4ee6382cfbe7943879d9d961c7d2654e8', '0');
INSERT INTO `easybuy_user` VALUES ('120', 'cc', 'ccc', 'e10adc3949ba59abbe56e057f20f883e', '1', '111111111111111111', '13955839036@163.com', '13955839036', '0', 'd395054f6d954a8398a48f59759c30040f4412e90cc54b1891a2444d5cebc58b', '0');
INSERT INTO `easybuy_user` VALUES ('121', 'cc', 'cc', 'e10adc3949ba59abbe56e057f20f883e', '1', '121111111111111111', '13955839036@163.com', '13955839036', '0', '5243a514b5504b16a7a9cd0457b3075e62656aebd054459f88b46b58558afa3e', '0');
INSERT INTO `easybuy_user` VALUES ('122', 'admincccc', 'ccccccccccc', 'e10adc3949ba59abbe56e057f20f883e', '1', '111111111111111111', '13955839036@163.com', '13955839036', '0', 'de10c59cbbe7412780ef19a1957c3e168ce6e713447e41b9bc8571e516c99271', '0');
INSERT INTO `easybuy_user` VALUES ('123', 'admincvvvvv', 'ccccccccc', 'e10adc3949ba59abbe56e057f20f883e', '1', '111111111111111111', '13955839036@163.com', '13955839036', '0', '65ae905220704475aa8f9e28550ae153af0b73e4937b4e238c72e8f428b23cf5', '0');
INSERT INTO `easybuy_user` VALUES ('124', 'adminawdsadas', 'cccccccccc', 'e10adc3949ba59abbe56e057f20f883e', '1', '111111111111111111', '13955839036@163.com', '13955839036', '0', 'e5e81b2750d8470480b1aa1d2972b33b49966c457a814035b11e39cf3bab7357', '0');
INSERT INTO `easybuy_user` VALUES ('125', 'adminawdsadas', 'cccccccccc', 'e10adc3949ba59abbe56e057f20f883e', '1', '111111111111111111', '13955839036@163.com', '13955839036', '0', '716d2af226bd4d4b961c15ab9f64e06f0c7bc9eb43494da9ad980835bea22c68', '0');
INSERT INTO `easybuy_user` VALUES ('137', 'adminaaaaaoo', 'ffff', 'e10adc3949ba59abbe56e057f20f883e', '1', '999999999999999999', '13955839036@163.com', '13955839036', '0', '8af8378522cc48e79ee233401c1e7f91e1c18accc57144a4a42140d4ea65d6c9', '0');
INSERT INTO `easybuy_user` VALUES ('138', 'admin', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '1', '999999999999999999', '13955839036@163.com', '13955839036', '0', '4b966dd30bc5442bb016309ebb9ad01c5e3cf2f863674d41ac558ac64b2331c1', '1');

-- ----------------------------
-- Table structure for easybuy_user_address
-- ----------------------------
DROP TABLE IF EXISTS `easybuy_user_address`;
CREATE TABLE `easybuy_user_address` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `address` varchar(20) NOT NULL COMMENT '地址',
  `createTime` varchar(20) NOT NULL COMMENT '创建时间',
  `userId` int(10) NOT NULL COMMENT '用户主键',
  `isDefault` int(1) NOT NULL COMMENT '是否是默认主键',
  `remark` varchar(18) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  CONSTRAINT `easybuy_user_address_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `easybuy_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='用户地址表';

-- ----------------------------
-- Records of easybuy_user_address
-- ----------------------------
INSERT INTO `easybuy_user_address` VALUES ('11', '北京市海淀区大有庄', '2018-06-07 21:06:18', '2', '0', '朋友家');
INSERT INTO `easybuy_user_address` VALUES ('12', '北京市海淀区大有庄', '2018-06-07 21:06:18', '2', '0', '女朋友公司');
INSERT INTO `easybuy_user_address` VALUES ('13', '北京市西直门大桥芬兰国际大厦', '2018-06-07 21:06:18', '10', '0', '女朋友地址');
INSERT INTO `easybuy_user_address` VALUES ('14', '北京市花园路小区', '2018-06-07 21:06:18', '18', '0', '家里');
INSERT INTO `easybuy_user_address` VALUES ('15', '北京市海淀区成府路', '2018-06-07 21:06:18', '18', '0', '公司');
INSERT INTO `easybuy_user_address` VALUES ('16', '安徽省', '2019-11-25 09:22:00', '68', '0', '合肥市');
INSERT INTO `easybuy_user_address` VALUES ('17', '安徽省', '2019-11-25 09:56:54', '14', '0', '合肥市');
INSERT INTO `easybuy_user_address` VALUES ('18', '安徽省', '2019-11-25 11:14:59', '78', '0', '合肥市');
INSERT INTO `easybuy_user_address` VALUES ('19', '安徽省合肥市', '2019-11-27 14:26:26', '87', '0', '二十');
INSERT INTO `easybuy_user_address` VALUES ('20', '安徽省', '2020-03-12 13:35:38', '2', '0', '合肥市');
