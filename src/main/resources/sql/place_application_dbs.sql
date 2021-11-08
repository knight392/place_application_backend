/*
MySQL Backup
Source Server Version: 5.5.28
Source Database: place_application_dbs
Date: 2021/11/4 21:54:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
--  Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `admin_no` char(9) NOT NULL,
  `admin_name` char(8) NOT NULL,
  `admin_password` char(16) NOT NULL,
  PRIMARY KEY (`admin_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `apl_procedure`
-- ----------------------------
DROP TABLE IF EXISTS `apl_procedure`;
CREATE TABLE `apl_procedure` (
  `pro_no` int(11) NOT NULL AUTO_INCREMENT,
  `pro_name` char(30) NOT NULL,
  `pro_form_name` char(30) NOT NULL,
  PRIMARY KEY (`pro_no`),
  UNIQUE KEY `pro_name` (`pro_name`),
  UNIQUE KEY `pro_name_2` (`pro_name`),
  UNIQUE KEY `pro_name_3` (`pro_name`),
  UNIQUE KEY `pro_form_name` (`pro_form_name`),
  UNIQUE KEY `pro_form_name_2` (`pro_form_name`),
  UNIQUE KEY `pro_form_name_3` (`pro_form_name`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `apl_record`
-- ----------------------------
DROP TABLE IF EXISTS `apl_record`;
CREATE TABLE `apl_record` (
  `record_no` int(11) NOT NULL AUTO_INCREMENT,
  `apl_no` int(11) DEFAULT NULL,
  `status_time` datetime NOT NULL,
  `status_info` varchar(200) NOT NULL,
  PRIMARY KEY (`record_no`),
  KEY `apl_no` (`apl_no`),
  CONSTRAINT `apl_record_ibfk_1` FOREIGN KEY (`apl_no`) REFERENCES `place_application` (`apl_no`)
) ENGINE=InnoDB AUTO_INCREMENT=171 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `image`
-- ----------------------------
DROP TABLE IF EXISTS `image`;
CREATE TABLE `image` (
  `img_no` int(11) NOT NULL AUTO_INCREMENT,
  `path` varchar(300) NOT NULL,
  `type` varchar(30) NOT NULL,
  `height` int(11) DEFAULT NULL,
  `width` int(11) DEFAULT NULL,
  PRIMARY KEY (`img_no`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `info_msg`
-- ----------------------------
DROP TABLE IF EXISTS `info_msg`;
CREATE TABLE `info_msg` (
  `info_no` int(11) NOT NULL,
  `info_content` char(80) NOT NULL,
  `s_no` char(9) NOT NULL,
  `info_time` datetime NOT NULL,
  `is_read` tinyint(4) NOT NULL,
  PRIMARY KEY (`info_no`),
  KEY `s_no` (`s_no`),
  CONSTRAINT `info_msg_ibfk_1` FOREIGN KEY (`s_no`) REFERENCES `student` (`s_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `place_application`
-- ----------------------------
DROP TABLE IF EXISTS `place_application`;
CREATE TABLE `place_application` (
  `apl_no` int(11) NOT NULL AUTO_INCREMENT,
  `place_no` int(11) DEFAULT NULL,
  `pro_no` int(11) DEFAULT NULL,
  `s_no` char(9) NOT NULL,
  `cur_status` tinyint(4) NOT NULL,
  `cur_step` tinyint(4) NOT NULL,
  `s_phone` char(11) NOT NULL,
  `tutor_name` char(8) DEFAULT NULL,
  `org_name` char(30) NOT NULL,
  `begin_time` datetime NOT NULL,
  `end_time` datetime NOT NULL,
  `purpose` char(100) NOT NULL,
  `apl_date` datetime NOT NULL,
  `max_step` tinyint(4) DEFAULT '1',
  `is_copy` tinyint(4) DEFAULT '0',
  `refuse_reason` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`apl_no`),
  KEY `place_no` (`place_no`),
  KEY `pro_no` (`pro_no`),
  KEY `s_no` (`s_no`),
  CONSTRAINT `place_application_ibfk_1` FOREIGN KEY (`place_no`) REFERENCES `place_msg` (`place_no`),
  CONSTRAINT `place_application_ibfk_2` FOREIGN KEY (`pro_no`) REFERENCES `apl_procedure` (`pro_no`),
  CONSTRAINT `place_application_ibfk_3` FOREIGN KEY (`s_no`) REFERENCES `student` (`s_no`)
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `place_msg`
-- ----------------------------
DROP TABLE IF EXISTS `place_msg`;
CREATE TABLE `place_msg` (
  `place_no` int(11) NOT NULL AUTO_INCREMENT,
  `place_name` char(30) NOT NULL,
  `pro_no` int(11) DEFAULT NULL,
  `place_info` char(100) NOT NULL,
  `img_no` int(11) DEFAULT NULL,
  `available` tinyint(4) DEFAULT '0',
  PRIMARY KEY (`place_no`),
  UNIQUE KEY `place_name` (`place_name`),
  UNIQUE KEY `place_name_2` (`place_name`),
  KEY `pro_no` (`pro_no`),
  KEY `FK_IMG_ID` (`img_no`),
  CONSTRAINT `FK_IMG_ID` FOREIGN KEY (`img_no`) REFERENCES `image` (`img_no`),
  CONSTRAINT `place_msg_ibfk_1` FOREIGN KEY (`pro_no`) REFERENCES `apl_procedure` (`pro_no`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `position_msg`
-- ----------------------------
DROP TABLE IF EXISTS `position_msg`;
CREATE TABLE `position_msg` (
  `position_no` int(11) NOT NULL AUTO_INCREMENT,
  `position_name` char(15) NOT NULL,
  `position_info` char(50) NOT NULL,
  `teacher_no` char(9) DEFAULT NULL,
  `pro_no` int(11) DEFAULT NULL,
  `pro_order` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`position_no`),
  UNIQUE KEY `position_name` (`position_name`),
  UNIQUE KEY `position_name_2` (`position_name`),
  KEY `teacher_no` (`teacher_no`),
  KEY `pro_no` (`pro_no`),
  CONSTRAINT `position_msg_ibfk_1` FOREIGN KEY (`teacher_no`) REFERENCES `teacher` (`teacher_no`),
  CONSTRAINT `position_msg_ibfk_2` FOREIGN KEY (`pro_no`) REFERENCES `apl_procedure` (`pro_no`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `student`
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `s_no` char(9) NOT NULL,
  `s_name` char(8) NOT NULL,
  `s_password` char(16) NOT NULL,
  `s_dwmc` char(30) NOT NULL,
  `s_phone` char(11) DEFAULT NULL,
  `s_avatar` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`s_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `task`
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `task_no` int(11) NOT NULL AUTO_INCREMENT,
  `is_finished` tinyint(4) NOT NULL,
  `apl_no` int(11) NOT NULL,
  `teacher_no` char(9) NOT NULL,
  `generate_time` datetime NOT NULL,
  `finished_time` datetime DEFAULT NULL,
  `is_resentence` tinyint(4) DEFAULT '0',
  `refuse_reason` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`task_no`),
  KEY `apl_no` (`apl_no`),
  KEY `teacher_no` (`teacher_no`),
  CONSTRAINT `task_ibfk_1` FOREIGN KEY (`apl_no`) REFERENCES `place_application` (`apl_no`),
  CONSTRAINT `task_ibfk_2` FOREIGN KEY (`teacher_no`) REFERENCES `teacher` (`teacher_no`)
) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `teacher`
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `teacher_no` char(9) NOT NULL,
  `teacher_name` char(8) NOT NULL,
  `teacher_password` char(16) NOT NULL,
  PRIMARY KEY (`teacher_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records 
-- ----------------------------
INSERT INTO `admin` VALUES ('123456789','冯远安','abc12345');
INSERT INTO `apl_procedure` VALUES ('3','北教申请流程','北教申请表'), ('8','北苑申请流程','北苑申请表'), ('11','实验楼申请流程','实验楼教室申请表'), ('12','青年广场申请流程','青年广场申请表'), ('14','南苑申请流程','宿舍申请表'), ('15','办公室申请流程','办公室申请表'), ('16','教室申请流程','教室申请表');
INSERT INTO `apl_record` VALUES ('140','92','2021-10-09 23:30:01','申请提交成功'), ('141','93','2021-10-09 23:30:43','申请提交成功'), ('142','94','2021-10-09 23:31:53','申请提交成功'), ('143','94','2021-10-09 23:33:32','邓超审批通过'), ('144','94','2021-10-09 23:33:33','申请成功'), ('145','92','2021-10-09 23:34:29','邓超审批通过'), ('146','93','2021-10-09 23:36:43','妖姬审批打回。打回原因：不能扣篮哦.'), ('147','93','2021-10-09 23:37:36','申请补正并提交成功'), ('148','93','2021-10-09 23:37:52','妖姬再审通过'), ('149','93','2021-10-09 23:39:14','小明审批拒绝。拒绝原因：篮球场需要维修，暂停外借。'), ('150','93','2021-10-09 23:39:15','申请失败'), ('151','96','2021-10-09 23:41:37','申请提交成功'), ('152','96','2021-10-09 23:43:41','小明审批通过'), ('153','97','2021-10-09 23:51:55','申请提交成功'), ('154','98','2021-10-09 23:53:41','申请提交成功'), ('155','99','2021-10-09 23:54:41','申请提交成功'), ('156','99','2021-10-09 23:56:05','妖姬审批打回。打回原因：不信.'), ('157','98','2021-10-09 23:56:16','妖姬审批拒绝。拒绝原因：AA制。'), ('158','98','2021-10-09 23:56:17','申请失败'), ('159','97','2021-10-09 23:56:21','妖姬审批通过'), ('160','99','2021-10-09 23:57:06','申请补正并提交成功'), ('161','99','2021-10-09 23:57:17','妖姬再审通过'), ('162','101','2021-10-09 23:58:21','申请提交成功'), ('163','101','2021-10-09 23:58:46','妖姬审批拒绝。拒绝原因：学习！打什么游戏！。'), ('164','101','2021-10-09 23:58:47','申请失败'), ('165','102','2021-10-10 09:36:59','申请提交成功'), ('166','92','2021-10-10 09:39:47','申请中断'), ('167','103','2021-10-10 11:01:18','申请提交成功'), ('168','103','2021-10-10 13:06:15','邓超审批打回。打回原因：需补正.'), ('169','103','2021-10-10 13:11:06','申请补正并提交成功'), ('170','103','2021-10-10 13:12:05','邓超再审打回。打回原因：10月10号没有空余摊位了，需改时间.');
INSERT INTO `image` VALUES ('1','/images/student/swiper/bg1007.jpg','stu_swiper',NULL,NULL), ('2','/images/student/swiper/bg1008.jpg','stu_swiper',NULL,NULL), ('3','/images/student/swiper/bg1010.jpg','stu_swiper',NULL,NULL), ('4','/images/student/swiper/bg1012.jpg','stu_swiper',NULL,NULL), ('5','/images/student/swiper/bg1013.jpg','stu_swiper',NULL,NULL), ('6','/images/student/swiper/bg1014.jpg','stu_swiper',NULL,NULL), ('7','/images/student/swiper/bg1015.jpg','stu_swiper',NULL,NULL), ('8','/images/upload/places/place (9).jpg','place','427','640'), ('9','/images/upload/places/place (8).jpg','place','480','720'), ('10','/images/upload/places/place (7).jpg','place','719','1080'), ('11','/images/upload/places/place (6).jpg','place','720','540'), ('12','/images/upload/places/place (2).jpg','place','450','800'), ('13','/images/upload/places/109951163952452264.jpg','place','0','0'), ('14','/images/upload/places/109951164296225641.jpg','place','0','0'), ('15','/images/upload/places/109951164317086969.jpg','place','0','0'), ('16','/images/upload/places/109951163919149089.jpg','place','0','0'), ('17','/images/upload/places/109951163952453734.jpg','place','0','0'), ('18','/images/upload/places/109951164315835911.jpg','place','0','0'), ('19','/images/upload/places/109951163952453734.jpg','place','0','0'), ('20','/images/upload/places/109951163952453734.jpg','place','0','0'), ('21','/images/upload/places/109951163952453734.jpg','place','0','0'), ('22','/images/upload/places/109951164296225641.jpg','place','0','0'), ('23','/images/upload/places/109951164294823179.jpg','place','0','0'), ('24','/images/upload/places/109951163952451308.jpg','place','0','0'), ('25','/images/upload/places/109951163952451308.jpg','place','0','0'), ('26','/images/upload/places/109951164317086969.jpg','place','0','0'), ('27','/images/upload/places/109951164296225641.jpg','place','0','0'), ('28','/images/upload/places/191543209蔡沛.jpg','place','0','0'), ('29','/images/upload/places/109951163919149064.jpg','place','0','0'), ('30','/images/upload/places/109951163952451308.jpg','place','0','0'), ('31','/images/upload/places/109951164315835911.jpg','place','0','0'), ('32','/images/upload/places/109951163919149064.jpg','place','0','0'), ('33','/images/upload/places/109951163919149064.jpg','place','0','0'), ('34','/images/upload/places/109951164315835911.jpg','place','0','0'), ('35','/images/upload/places/金融.jpg','place','0','0'), ('36','/images/upload/places/109951163919149064.jpg','place','0','0'), ('37','/images/upload/places/191543214冯远安.png','place','0','0'), ('38','/images/upload/places/109951163919149064.jpg','place','0','0'), ('39','/images/upload/places/109951164315835911.jpg','place','0','0'), ('40','/images/upload/places/109951163952453734.jpg','place','0','0'), ('41','/images/upload/places/109951163919149064.jpg','place','0','0'), ('42','/images/upload/places/191543209蔡沛.jpg','place','0','0'), ('43','/images/upload/places/8463585.jpg','place','0','0'), ('44','/images/upload/places/109951163952451842.jpg','place','0','0'), ('45','/images/upload/places/109951163952453734.jpg','place','0','0'), ('46','/images/upload/places/109951164315840793.jpg','place','0','0'), ('47','/images/upload/places/109951164315843664.jpg','place','0','0'), ('48','/images/upload/places/109951164297053959.jpg','place','250','250'), ('49','/images/upload/places/109951164296225641.jpg','place','589','772'), ('50','/images/upload/places/timgKBARZEIZ.jpg','place','273','512'), ('51','/images/upload/places/109951163952451308.jpg','place','1093','896'), ('52','/images/upload/places/place (4).jpg','place','720','1080'), ('53','/images/upload/places/defaultPlace.jpg','place','720','1080'), ('54','/images/upload/places/beijiao.jpg','place','720','1280'), ('55','/images/upload/places/office.jpg','place','401','700'), ('56','/images/upload/places/computerRoom.jpg','place','462','800'), ('57','/images/upload/places/basketballPlace.jpg','place','2368','4208'), ('58','/images/upload/places/badroom.jpg','place','867','650'), ('59','/images/upload/places/109951163919149064.jpg','place','1080','1080'), ('60','/images/upload/places/109951164315835911.jpg','place','690','690');
INSERT INTO `place_application` VALUES ('92','11','8','191543214','5','2','13030200997','刘晓燕','互联网青协','2021-10-10 08:28:00','2021-10-10 17:28:00','校园电脑义修','2021-10-09 23:30:01','2','0',NULL), ('93','14','14','191543214','4','2','18948679265','姚明','校篮球队','2021-10-09 23:28:05','2021-10-10 03:28:00','打篮球训练','2021-10-09 23:30:43','2','0','篮球场需要维修，暂停外借'), ('94','16','15','191543214','2','3','18948679265','邓郑杰','1915432','2021-10-09 23:28:05','2021-10-10 02:28:00','想和校长喝茶','2021-10-09 23:31:53','3','0',NULL), ('95','14','14','191543214','1','1','18948679265','姚明','校篮球队','2021-10-09 23:28:05','2021-10-10 03:28:00','打篮球扣篮','2021-10-09 23:30:43','1','1',NULL), ('96','19','3','191543214','1','2','18948679265','钟雪灵','爪哇部落','2021-10-09 23:34:35','2021-10-10 00:34:35','爪哇部落软件设计大赛宣讲会','2021-10-09 23:41:37','2','0',NULL), ('97','4','14','191543214','1','2','18948679265','刘晓燕','校红会','2021-10-09 23:48:58','2021-10-10 03:48:00','核酸检查','2021-10-09 23:51:55','2','0',NULL), ('98','17','14','191543214','4','1','18948679265','冯远安','207宿舍','2021-10-09 23:48:58','2021-10-10 00:48:58','睡觉睡觉','2021-10-09 23:53:41','1','0','AA制'), ('99','9','14','191543214','1','2','13211261152','马冬梅','连一大队','2021-10-13 23:48:00','2021-10-14 00:48:00','干饭联谊，真的老师','2021-10-09 23:54:41','2','0',NULL), ('100','9','14','191543214','1','1','13211261152','马冬梅','连一大队','2021-10-13 23:48:00','2021-10-14 00:48:00','干饭联谊','2021-10-09 23:54:41','1','1',NULL), ('101','17','14','191543214','4','1','18948679265','王思雨','207','2021-10-09 23:48:58','2021-10-13 00:48:00','打游戏','2021-10-09 23:58:21','1','0','学习！打什么游戏！'), ('102','13','11','191543214','1','1','18948679265','李里','19计科2班','2021-10-10 09:32:21','2021-10-10 10:32:21','开会','2021-10-10 09:36:59','1','0',NULL), ('103','11','16','191543214','3','0','18948178364','刘晓燕','互联网院青协','2021-10-10 10:51:11','2021-10-10 18:51:00','举办校园义卖活动','2021-10-10 11:01:18','1','0','10月10号没有空余摊位了，需改时间'), ('104','11','16','191543214','1','1','18948178364','刘晓燕','互联网院青协','2021-10-10 10:51:11','2021-10-10 18:51:00','举办校园义卖活动','2021-10-10 11:01:18','1','1',NULL), ('105','11','16','191543214','1','1','18948178364','刘晓燕','互联网院青协','2021-10-10 10:51:11','2021-10-10 18:51:00','举办校园义卖活动','2021-10-10 11:01:18','1','1',NULL);
INSERT INTO `place_msg` VALUES ('2','青年广场','12','只能开放10个摊位','8','1'), ('4','南苑空地','14','用于社团活动摆摊','9','1'), ('9','南苑5楼','14','可以用于举办饭局和会议','10','1'), ('11','北苑旁空地','16','用于社团活动摆摊','11','1'), ('12','北教102','3','333升水','12','1'), ('13','实验楼706','11','给爪哇部落开会','56','1'), ('14','南苑篮球场','14','篮球比赛用地','57','1'), ('16','校长办公室','15','得闲喝茶啊','55','1'), ('17','11栋-207','14','睡觉','58','1'), ('18','北苑三楼','8','干饭','53','1'), ('19','北阶101','3','用来大型校园活动','54','1'), ('20','融创空间',NULL,'打麻将','59','0'), ('21','阿健',NULL,'烧烤','60','0');
INSERT INTO `position_msg` VALUES ('13','南苑审批2','负责南苑2轮申请','12344','14','1'), ('14','南苑审批1','负责南苑首批','123123','14','2'), ('15','电教审批1','负责电教首批','333333','11','1'), ('16','电教审批2','负责电教2轮审批','12345','11','2'), ('18','青年广场审批','负责青年广场审批','789632','12','1'), ('19','北教审批2','负责北教2轮审批','78934','3','2'), ('22','北苑审批1','负责北苑首批','78935','16','1'), ('23','北教审批1','负责北教申请首批','123123','3','1'), ('24','北苑审批2','负责北苑2轮审批','789633','8','1'), ('25','办公室审批','负责审批办公室','78935','15','1');
INSERT INTO `student` VALUES ('191543214','冯远安','yya063514','互联网金融与信息工程学院',NULL,'https://thirdwx.qlogo.cn/mmopen/vi_32/DaQE4icqusBp7eX1ictodMOTP0pFvuBAqLODIyOcwJyX3cuw1aZGElrlmCPHibaUCOqDhzhbPrpa1lq6ibpk3qZwkA/132');
INSERT INTO `task` VALUES ('79','1','92','78935','2021-10-09 23:30:01','2021-10-09 23:34:29','0',NULL), ('80','2','95','12344','2021-10-09 23:30:43','2021-10-09 23:36:43','0','不能扣篮哦'), ('81','1','94','78935','2021-10-09 23:31:53','2021-10-09 23:33:32','0',NULL), ('82','4','92','789633','2021-10-09 23:34:29','2021-10-10 09:39:47','0',NULL), ('83','1','93','12344','2021-10-09 23:37:36','2021-10-09 23:37:52','1',NULL), ('84','3','93','123123','2021-10-09 23:37:52','2021-10-09 23:39:14','0','篮球场需要维修，暂停外借'), ('85','1','96','123123','2021-10-09 23:41:37','2021-10-09 23:43:41','0',NULL), ('87','1','97','12344','2021-10-09 23:51:55','2021-10-09 23:56:21','0',NULL), ('88','3','98','12344','2021-10-09 23:53:41','2021-10-09 23:56:16','0','AA制'), ('89','2','100','12344','2021-10-09 23:54:41','2021-10-09 23:56:05','0','不信'), ('90','0','97','123123','2021-10-09 23:56:21',NULL,'0',NULL), ('91','1','99','12344','2021-10-09 23:57:06','2021-10-09 23:57:17','1',NULL), ('92','0','99','123123','2021-10-09 23:57:17',NULL,'0',NULL), ('93','3','101','12344','2021-10-09 23:58:21','2021-10-09 23:58:46','0','学习！打什么游戏！'), ('94','0','102','333333','2021-10-10 09:36:59',NULL,'0',NULL), ('95','2','104','78935','2021-10-10 11:01:18','2021-10-10 13:06:15','0','需补正'), ('96','2','105','78935','2021-10-10 13:11:06','2021-10-10 13:12:05','1','10月10号没有空余摊位了，需改时间');
INSERT INTO `teacher` VALUES ('123123','小明','123123'), ('12344','妖姬','123123'), ('12345','鲁夫','123123'), ('333333','陈嘻嘻','11111'), ('78934','马东梅','123123'), ('78935','邓超','123123'), ('789632','李力','123123'), ('789633','黄牛','123123');

-- ----------------------------
--  Trigger definition for `apl_procedure`
-- ----------------------------
DELIMITER ;;
CREATE TRIGGER `apl_procedure_trigger` BEFORE DELETE ON `apl_procedure` FOR EACH ROW begin
	update position_msg set pro_no = null, pro_order = null
	where pro_no = old.pro_no;
	update place_msg set pro_no = null
	where pro_no = old.pro_no;
	update place_application set pro_no = null
	where pro_no = old.pro_no;
end
;;
DELIMITER ;

-- ----------------------------
--  Trigger definition for `place_msg`
-- ----------------------------
DELIMITER ;;
CREATE TRIGGER `place_msg_trigger` BEFORE DELETE ON `place_msg` FOR EACH ROW begin
update place_application set place_no = null
where place_no = old.place_no;
end
;;
DELIMITER ;

-- ----------------------------
--  Trigger definition for `teacher`
-- ----------------------------
DELIMITER ;;
CREATE TRIGGER `teacher_trigger` BEFORE DELETE ON `teacher` FOR EACH ROW begin
	update position_msg set teacher_no = null, pro_no = null, pro_order = null
	where teacher_no = old.teacher_no;
end
;;
DELIMITER ;
