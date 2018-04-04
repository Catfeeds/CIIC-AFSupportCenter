# 企业公积金企业任务单字典表 - 列表以及脚本
# hf_com_task_category
# hf_type
# hf_com_task_payment_way
# hf_com_account_state
# hf_com_account_ukstore
# hf_com_account_type
# hf_com_account_payment_way
# hf_com_task_task_status
# hf_com_task_end_type
# hf_com_account_payment_bank

-- ----------------------------
-- Table structure for hf_com_task_category
-- ----------------------------
DROP TABLE IF EXISTS `hf_com_task_category`;
CREATE TABLE `hf_com_task_category` (
  `com_task_category_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `task_category_code` bigint(20) NOT NULL COMMENT '企业任务单类型编号',
  `task_category_name` varchar(20) NOT NULL COMMENT '企业公积金账户',
  `is_active` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否可用',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `created_by` varchar(20) NOT NULL COMMENT '创建者登录名',
  `modified_by` varchar(20) NOT NULL COMMENT '修改者登录名',
  PRIMARY KEY (`com_task_category_id`),
  KEY `IX_HF_COM_TASK_CATEGORY_COM_TASK_CATEGORY_CODE` (`task_category_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='公积金企业任务单类型表';

-- ----------------------------
-- Records of hf_com_task_category
-- ----------------------------
INSERT INTO `hf_com_task_category` VALUES ('1', '1', '开户', '', '2018-02-09 19:23:33', '2018-02-09 19:23:33', 'shenjian', 'shenjian');
INSERT INTO `hf_com_task_category` VALUES ('2', '2', '转入', '', '2018-02-09 19:23:48', '2018-02-09 19:23:48', 'shenjian', 'shenjian');
INSERT INTO `hf_com_task_category` VALUES ('3', '3', '变更', '', '2018-02-09 19:24:04', '2018-02-09 19:24:04', 'shenjian', 'shenjian');
INSERT INTO `hf_com_task_category` VALUES ('4', '4', '终止', '', '2018-02-09 19:24:13', '2018-02-09 19:24:13', 'shenjian', 'shenjian');
INSERT INTO `hf_com_task_category` VALUES ('5', '5', '销户', '', '2018-02-09 19:24:21', '2018-02-09 19:24:21', 'shenjian', 'shenjian');

-- ----------------------------
-- Table structure for hf_type
-- ----------------------------
DROP TABLE IF EXISTS `hf_type`;
CREATE TABLE `hf_type` (
  `hf_type_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `hf_type_code` bigint(20) NOT NULL COMMENT '公积金类型编号',
  `hf_type_name` varchar(20) NOT NULL COMMENT '公积金类型名称',
  `is_active` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否可用',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `created_by` varchar(20) NOT NULL COMMENT '创建者登录名',
  `modified_by` varchar(20) NOT NULL COMMENT '修改者登录名',
  PRIMARY KEY (`hf_type_id`),
  KEY `IX_HF_TYPE_HF_TYPE_CODE` (`hf_type_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='公积金类型表';

-- ----------------------------
-- Records of hf_type
-- ----------------------------
INSERT INTO `hf_type` VALUES ('1', '1', '基本公积金', '', '2018-02-09 19:23:33', '2018-02-09 19:23:33', 'shenjian', 'shenjian');
INSERT INTO `hf_type` VALUES ('2', '2', '补充公积金', '', '2018-02-09 19:23:48', '2018-02-09 19:23:48', 'shenjian', 'shenjian');

-- ----------------------------
-- Table structure for hf_com_task_payment_way
-- ----------------------------
DROP TABLE IF EXISTS `hf_com_task_payment_way`;
CREATE TABLE `hf_com_task_payment_way` (
  `hf_com_task_payment_way_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `com_task_payment_way_code` bigint(20) NOT NULL COMMENT '公积金企业任务单付款方式类型编号',
  `com_task_payment_way_name` varchar(20) NOT NULL COMMENT '公积金企业任务单付款方式类型名称',
  `is_active` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否可用',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `created_by` varchar(20) NOT NULL COMMENT '创建者登录名',
  `modified_by` varchar(20) NOT NULL COMMENT '修改者登录名',
  PRIMARY KEY (`hf_com_task_payment_way_id`),
  KEY `IX_HF_TYPE_HF_TYPE_CODE` (`com_task_payment_way_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='公积金企业任务单付款方式类型表';

-- ----------------------------
-- Records of hf_com_task_payment_way
-- ----------------------------
INSERT INTO `hf_com_task_payment_way` VALUES ('1', '1', '自付', '', '2018-02-09 19:23:33', '2018-02-09 19:23:33', 'shenjian', 'shenjian');
INSERT INTO `hf_com_task_payment_way` VALUES ('2', '2', '我司付款', '', '2018-02-09 19:23:48', '2018-02-09 19:23:48', 'shenjian', 'shenjian');
INSERT INTO `hf_com_task_payment_way` VALUES ('6', '3', '垫付', '', '2018-02-11 10:41:20', '2018-02-11 10:41:20', 'shenjian', 'shenjian');

-- ----------------------------
-- Table structure for hf_com_account_state
-- ----------------------------
DROP TABLE IF EXISTS `hf_com_account_state`;
CREATE TABLE `hf_com_account_state` (
  `com_account_state_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `com_account_state_code` int(20) NOT NULL COMMENT '企业公积金账户状态编号',
  `com_account_state_value` varchar(20) NOT NULL COMMENT '企业公积金账户状态值',
  `is_active` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否可用',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `created_by` varchar(20) NOT NULL COMMENT '创建者登录名',
  `modified_by` varchar(20) NOT NULL COMMENT '修改者登录名',
  PRIMARY KEY (`com_account_state_id`),
  KEY `IX_HF_COM_ACCOUNT_STATE_COM_ACCOUNT_STATE_CODE` (`com_account_state_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='企业公积金账户状态数据表';

-- ----------------------------
-- Records of hf_com_account_state
-- ----------------------------
INSERT INTO `hf_com_account_state` VALUES ('1', '0', '初始', '', '2018-02-09 19:23:33', '2018-02-09 19:23:33', 'shenjian', 'shenjian');
INSERT INTO `hf_com_account_state` VALUES ('2', '1', '有效', '', '2018-02-09 19:23:48', '2018-02-09 19:23:48', 'shenjian', 'shenjian');
INSERT INTO `hf_com_account_state` VALUES ('3', '2', '终止', '', '2018-02-09 19:24:04', '2018-02-09 19:24:04', 'shenjian', 'shenjian');

-- ----------------------------
-- Table structure for hf_com_account_ukstore
-- ----------------------------
DROP TABLE IF EXISTS `hf_com_account_ukstore`;
CREATE TABLE `hf_com_account_ukstore` (
  `com_account_ukstore_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ukstore_code` int(20) NOT NULL COMMENT '企业公积金账户UKeyStore编号',
  `ukstore_value` varchar(20) NOT NULL COMMENT '企业公积金账户UKeyStore值',
  `is_active` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否可用',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `created_by` varchar(20) NOT NULL COMMENT '创建者登录名',
  `modified_by` varchar(20) NOT NULL COMMENT '修改者登录名',
  PRIMARY KEY (`com_account_ukstore_id`),
  KEY `IX_HF_COM_ACCOUNT_UKSTORE_UKSTORE_CODE` (`ukstore_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='企业公积金账户U盾数据表';

-- ----------------------------
-- Records of hf_com_account_ukstore
-- ----------------------------
INSERT INTO `hf_com_account_ukstore` VALUES ('1', '0', '没有', '', '2018-02-09 19:23:33', '2018-02-09 19:23:33', 'shenjian', 'shenjian');
INSERT INTO `hf_com_account_ukstore` VALUES ('2', '1', '有(客户自办)', '', '2018-02-09 19:23:48', '2018-02-09 19:23:48', 'shenjian', 'shenjian');
INSERT INTO `hf_com_account_ukstore` VALUES ('3', '2', '有(中智代办) ', '', '2018-02-09 19:24:04', '2018-02-09 19:24:04', 'shenjian', 'shenjian');

-- ----------------------------
-- Table structure for hf_com_account_type
-- ----------------------------
DROP TABLE IF EXISTS `hf_com_account_type`;
CREATE TABLE `hf_com_account_type` (
  `com_account_type_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type_code` int(20) NOT NULL COMMENT '企业公积金账户类型编号',
  `type_value` varchar(20) NOT NULL COMMENT '企业公积金账户类型值',
  `is_active` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否可用',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `created_by` varchar(20) NOT NULL COMMENT '创建者登录名',
  `modified_by` varchar(20) NOT NULL COMMENT '修改者登录名',
  PRIMARY KEY (`com_account_type_id`),
  KEY `IX_HF_COM_ACCOUNT_TYPE_TYPE_CODE` (`type_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='企业公积金账户类型数据表';

-- ----------------------------
-- Records of hf_com_account_type
-- ----------------------------
INSERT INTO `hf_com_account_type` VALUES ('1', '1', '大库', '', '2018-02-09 19:23:33', '2018-02-09 19:23:33', 'shenjian', 'shenjian');
INSERT INTO `hf_com_account_type` VALUES ('2', '2', '外包', '', '2018-02-09 19:23:48', '2018-02-09 19:23:48', 'shenjian', 'shenjian');
INSERT INTO `hf_com_account_type` VALUES ('3', '3', '独立户', '', '2018-02-09 19:24:04', '2018-02-09 19:24:04', 'shenjian', 'shenjian');

-- ----------------------------
-- Table structure for hf_com_account_payment_way
-- ----------------------------
DROP TABLE IF EXISTS `hf_com_account_payment_way`;
CREATE TABLE `hf_com_account_payment_way` (
  `com_account_paymentway_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `paymentway_code` int(20) NOT NULL COMMENT '企业公积金账户支付方式编号',
  `paymentway_value` varchar(20) NOT NULL COMMENT '企业公积金账户支付方式值',
  `is_active` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否可用',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `created_by` varchar(20) NOT NULL COMMENT '创建者登录名',
  `modified_by` varchar(20) NOT NULL COMMENT '修改者登录名',
  PRIMARY KEY (`com_account_paymentway_id`),
  KEY `IX_HF_COM_ACCOUNT_PAYMENT_WAY_PAYMENTWAY_CODE` (`paymentway_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='企业公积金账户支付方式数据表';

-- ----------------------------
-- Records of hf_com_account_payment_way
-- ----------------------------
INSERT INTO `hf_com_account_payment_way` VALUES ('1', '1', '自付', '', '2018-02-09 19:23:33', '2018-02-09 19:23:33', 'shenjian', 'shenjian');
INSERT INTO `hf_com_account_payment_way` VALUES ('2', '2', '我司付款', '', '2018-02-09 19:23:48', '2018-02-09 19:23:48', 'shenjian', 'shenjian');
INSERT INTO `hf_com_account_payment_way` VALUES ('3', '3', '垫付', '', '2018-02-09 19:24:04', '2018-02-09 19:24:04', 'shenjian', 'shenjian');

-- ----------------------------
-- Table structure for hf_com_task_task_status
-- ----------------------------
DROP TABLE IF EXISTS `hf_com_task_task_status`;
CREATE TABLE `hf_com_task_task_status` (
  `com_task_task_status_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `task_status_code` int(20) NOT NULL COMMENT '企业公积金任务单状态编号',
  `task_status_value` varchar(20) NOT NULL COMMENT '企业公积金任务单状态值',
  `is_active` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否可用',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `created_by` varchar(20) NOT NULL COMMENT '创建者登录名',
  `modified_by` varchar(20) NOT NULL COMMENT '修改者登录名',
  PRIMARY KEY (`com_task_task_status_id`),
  KEY `IX_HF_COM_TASK_TASK_STATUS_TASK_STATUS_CODE` (`task_status_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='企业公积金任务单状态数据表';

-- ----------------------------
-- Records of hf_com_task_task_status
-- ----------------------------
INSERT INTO `hf_com_task_task_status` VALUES ('1', '0', '初始（材料收缴）', '', '2018-02-09 19:23:33', '2018-02-09 19:23:33', 'shenjian', 'shenjian');
INSERT INTO `hf_com_task_task_status` VALUES ('2', '1', '受理中', '', '2018-02-09 19:23:48', '2018-02-09 19:23:48', 'shenjian', 'shenjian');
INSERT INTO `hf_com_task_task_status` VALUES ('3', '2', '送审中', '', '2018-02-09 19:24:04', '2018-02-09 19:24:04', 'shenjian', 'shenjian');
INSERT INTO `hf_com_task_task_status` VALUES ('6', '3', '已完成', '', '2018-02-28 17:52:44', '2018-02-28 17:52:44', 'shenjian', 'shenjian');
INSERT INTO `hf_com_task_task_status` VALUES ('7', '4', '批退', '', '2018-02-28 17:52:54', '2018-02-28 17:52:54', 'shenjian', 'shenjian');

-- ----------------------------
-- Table structure for hf_com_task_end_type
-- ----------------------------
DROP TABLE IF EXISTS `hf_com_task_end_type`;
CREATE TABLE `hf_com_task_end_type` (
  `com_task_end_type_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `end_type_code` int(20) NOT NULL COMMENT '企业公积金任务单销户类型编号',
  `end_type_value` varchar(20) NOT NULL COMMENT '企业公积金任务单销户类型值',
  `is_active` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否可用',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `created_by` varchar(20) NOT NULL COMMENT '创建者登录名',
  `modified_by` varchar(20) NOT NULL COMMENT '修改者登录名',
  PRIMARY KEY (`com_task_end_type_id`),
  KEY `IX_HF_COM_TASK_END_TYPE_END_TYPE_CODE` (`end_type_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='企业公积金账户销户类型数据表';

-- ----------------------------
-- Records of hf_com_task_end_type
-- ----------------------------
INSERT INTO `hf_com_task_end_type` VALUES ('1', '1', '销户', '', '2018-02-09 19:23:33', '2018-02-09 19:23:33', 'shenjian', 'shenjian');
INSERT INTO `hf_com_task_end_type` VALUES ('2', '2', '公司自做', '', '2018-02-09 19:23:48', '2018-02-09 19:23:48', 'shenjian', 'shenjian');
INSERT INTO `hf_com_task_end_type` VALUES ('3', '3', '转其他代理商', '', '2018-02-09 19:24:04', '2018-02-09 19:24:04', 'shenjian', 'shenjian');

-- ----------------------------
-- Table structure for hf_com_account_payment_bank
-- ----------------------------
DROP TABLE IF EXISTS `hf_com_account_payment_bank`;
CREATE TABLE `hf_com_account_payment_bank` (
  `com_account_payment_bank_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `payment_bank_code` int(20) NOT NULL COMMENT '企业公积金账户缴费银行编号',
  `payment_bank_value` varchar(20) NOT NULL COMMENT '企业公积金账户缴费银行值',
  `is_active` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否可用',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `created_by` varchar(20) NOT NULL COMMENT '创建者登录名',
  `modified_by` varchar(20) NOT NULL COMMENT '修改者登录名',
  PRIMARY KEY (`com_account_payment_bank_id`),
  KEY `IX_HF_COM_ACCOUNT_PAYMENT_BANK_CODE` (`payment_bank_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='企业公积金账户缴费银行数据表';

-- ----------------------------
-- Records of hf_com_account_payment_bank
-- ----------------------------
INSERT INTO `hf_com_account_payment_bank` VALUES ('1', '15', '徐汇—X', '', '2018-02-09 19:23:33', '2018-02-09 19:23:33', 'shenjian', 'shenjian');
INSERT INTO `hf_com_account_payment_bank` VALUES ('2', '16', '西郊—C', '', '2018-02-09 19:23:48', '2018-02-09 19:23:48', 'shenjian', 'shenjian');
INSERT INTO `hf_com_account_payment_bank` VALUES ('3', '17', '东方路—P', '', '2018-02-09 19:24:04', '2018-02-09 19:24:04', 'shenjian', 'shenjian');
INSERT INTO `hf_com_account_payment_bank` VALUES ('6', '18', '卢湾—L', '', '2018-03-14 18:48:50', '2018-03-14 18:48:50', 'shenjian', 'shenjian');
INSERT INTO `hf_com_account_payment_bank` VALUES ('7', '0', '黄浦—H', '', '2018-03-14 18:49:04', '2018-03-14 18:49:04', 'shenjian', 'shenjian');