# 企业公积金汇缴支付字典表 - 列表以及脚本
# hf_payment_state
# hf_payment_account_type

-- ----------------------------
-- Table structure for hf_payment_state
-- ----------------------------
DROP TABLE IF EXISTS `hf_payment_state`;
CREATE TABLE `hf_payment_state` (
  `payment_state_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `payment_state_code` int(20) NOT NULL COMMENT '支付状态编号',
  `payment_state_value` varchar(20) NOT NULL COMMENT '支付状态数据',
  `is_active` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否可用',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `created_by` varchar(20) NOT NULL COMMENT '创建者登录名',
  `modified_by` varchar(20) NOT NULL COMMENT '修改者登录名',
  PRIMARY KEY (`payment_state_id`),
  KEY `IX_HF_PAYMENT_STATE_PAYMENT_STATE_CODE` (`payment_state_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='公积金汇缴支付批次支付状态字典表';

-- ----------------------------
-- Records of hf_payment_state
-- ----------------------------
INSERT INTO `hf_payment_state` VALUES ('1', '3', '可付(默认)', '', '2018-02-09 19:23:33', '2018-02-09 19:23:33', 'shenjian', 'shenjian');
INSERT INTO `hf_payment_state` VALUES ('2', '4', '申请中', '', '2018-02-09 19:23:48', '2018-02-09 19:23:48', 'shenjian', 'shenjian');
INSERT INTO `hf_payment_state` VALUES ('3', '5', '内部审批批退', '', '2018-02-09 19:24:04', '2018-02-09 19:24:04', 'shenjian', 'shenjian');
INSERT INTO `hf_payment_state` VALUES ('6', '6', '已申请到财务部', '', '2018-03-15 14:42:24', '2018-03-15 14:42:24', 'shenjian', 'shenjian');
INSERT INTO `hf_payment_state` VALUES ('7', '7', '财务部批退', '', '2018-03-15 14:42:28', '2018-03-15 14:42:28', 'shenjian', 'shenjian');
INSERT INTO `hf_payment_state` VALUES ('8', '8', '财务部支付成功', '', '2018-03-15 14:42:38', '2018-03-15 14:42:38', 'shenjian', 'shenjian');
INSERT INTO `hf_payment_state` VALUES ('9', '1', '未到帐', '', '2018-03-19 16:34:03', '2018-03-19 16:34:03', 'shenjian', 'shenjian');
INSERT INTO `hf_payment_state` VALUES ('10', '2', '无需支付', '', '2018-03-19 16:35:09', '2018-03-19 16:35:09', 'shenjian', 'shenjian');

-- ----------------------------
-- Table structure for hf_payment_account_type
-- ----------------------------
DROP TABLE IF EXISTS `hf_payment_account_type`;
CREATE TABLE `hf_payment_account_type` (
  `account_type_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `account_type_code` int(20) NOT NULL COMMENT '账户类型编号',
  `account_type_value` varchar(20) NOT NULL COMMENT '账户类型数据',
  `is_active` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否可用',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modified_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `created_by` varchar(20) NOT NULL COMMENT '创建者登录名',
  `modified_by` varchar(20) NOT NULL COMMENT '修改者登录名',
  PRIMARY KEY (`account_type_id`),
  KEY `IX_HF_ACCOUNT_TYPE_ACCOUNT_TYPE_CODE` (`account_type_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='公积金汇缴支付批次账户类型字典表';

-- ----------------------------
-- Records of hf_payment_account_type
-- ----------------------------
INSERT INTO `hf_payment_account_type` VALUES ('1', '1', '中智大库', '', '2018-02-09 19:23:33', '2018-02-09 19:23:33', 'shenjian', 'shenjian');
INSERT INTO `hf_payment_account_type` VALUES ('2', '2', '中智外包', '', '2018-02-09 19:23:48', '2018-02-09 19:23:48', 'shenjian', 'shenjian');
INSERT INTO `hf_payment_account_type` VALUES ('3', '3', '独立户', '', '2018-02-09 19:24:04', '2018-02-09 19:24:04', 'shenjian', 'shenjian');
