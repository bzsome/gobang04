/*
Navicat MySQL Data Transfer

Source Server         : tvps.bzchao.com_root
Source Server Version : 50536
Source Host           : tvps.bzchao.com:3306
Source Database       : gobang04

Target Server Type    : MYSQL
Target Server Version : 50536
File Encoding         : 65001

Date: 2018-08-24 21:21:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user_roles
-- ----------------------------
DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE `user_roles` (
  `username` varchar(255) NOT NULL,
  `role_name` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_roles
-- ----------------------------

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '用户密码',
  `email` varchar(255) DEFAULT NULL,
  `sign` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uid`),
  UNIQUE KEY `username` (`username`)
) ENGINE=MyISAM AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('2', 'chao', 'ed3c6f5acc8f9a9065cf57928f73c126', 'q2323@qq.com', '123未确wqrwe');
INSERT INTO `users` VALUES ('5', 'adminss', '4261aebd6e06de976322b6932d3480f3', '333333@qq.com', null);
INSERT INTO `users` VALUES ('6', 'admin2', '7f132dda17417466de0e73841ce00bbf', '', '124234234231');
INSERT INTO `users` VALUES ('13', 'chaoa', 'e10adc3949ba59abbe56e057f20f883e', '', '1234132人去玩儿去玩儿');
