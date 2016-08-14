--
-- Table structure for table `sys_menu`
--

DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(100) DEFAULT NULL COMMENT '系统url',
  `parentId` int(10) DEFAULT NULL COMMENT ' 父id 关联sys_menu.id',
  `deleted` int(1) NOT NULL DEFAULT '0' COMMENT '是否删除,0=未删除，1=已删除',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '修改时间',
  `rank` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  `actions` varchar(500) DEFAULT '0' COMMENT '注册Action 按钮|分隔',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;


--
-- Table structure for table `sys_menu_btn`
--

DROP TABLE IF EXISTS `sys_menu_btn`;
CREATE TABLE `sys_menu_btn` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `menuid` int(11) NOT NULL COMMENT ' 菜单id关联 sys_menu.id',
  `btnName` varchar(30) DEFAULT NULL COMMENT '按钮名称',
  `btnType` varchar(30) DEFAULT NULL COMMENT '按钮类型，用于列表页显示的按钮',
  `actionUrls` varchar(250) DEFAULT NULL COMMENT 'url注册，用"," 分隔 。用于权限控制UR',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;


--
-- Table structure for table `sys_role`
--

DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(12) NOT NULL AUTO_INCREMENT COMMENT 'id主键',
  `roleName` varchar(30) DEFAULT NULL COMMENT '角色名称',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `createBy` int(11) DEFAULT NULL COMMENT '创建人',
  `updateTime` datetime DEFAULT NULL COMMENT '修改时间',
  `updateBy` int(11) DEFAULT NULL COMMENT '修改人',
  `state` int(1) DEFAULT NULL COMMENT '状态0=可用 1=禁用',
  `descr` varchar(200) DEFAULT NULL COMMENT '角色描述',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;


--
-- Table structure for table `sys_role_rel`
--

DROP TABLE IF EXISTS `sys_role_rel`;
CREATE TABLE `sys_role_rel` (
  `roleId` int(11) NOT NULL COMMENT '角色主键 sys_role.id',
  `objId` int(11) NOT NULL COMMENT '关联主键 type=0管理sys_menu.id, type=1关联sys_user.id',
  `relType` int(1) DEFAULT NULL COMMENT '关联类型 0=菜单,1=用户'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Table structure for table `sys_user`
--

DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id主键',
  `email` varchar(50) NOT NULL COMMENT '邮箱也是登录帐号',
  `pwd` varchar(50) DEFAULT NULL COMMENT '登录密码',
  `nickName` varchar(50) DEFAULT NULL COMMENT '昵称',
  `state` int(1) NOT NULL DEFAULT '0' COMMENT '状态 0=可用,1=禁用',
  `loginCount` int(11) DEFAULT NULL COMMENT '登录总次数',
  `loginTime` datetime DEFAULT NULL COMMENT '最后登录时间',
  `deleted` int(1) NOT NULL DEFAULT '0' COMMENT '删除状态 0=未删除,1=已删除',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '修改时间',
  `createBy` int(11) DEFAULT NULL COMMENT '创建人',
  `updateBy` int(11) DEFAULT NULL COMMENT '修改人',
  `superAdmin` int(1) NOT NULL DEFAULT '0' COMMENT '是否超级管理员 0= 不是，1=是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;


--
-- Table structure for table `t_stock`
--

DROP TABLE IF EXISTS `t_stock`;
CREATE TABLE `t_stock` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `p_createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `p_modifyTime` datetime DEFAULT NULL COMMENT '修改时间',
  `p_creator` varchar(255) DEFAULT NULL COMMENT '创建人',
  `p_modifier` varchar(255) DEFAULT NULL COMMENT '修改人',
  `p_remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `p_deleted` bit(1) DEFAULT NULL COMMENT '是否删除',
  `p_name` varchar(255) DEFAULT NULL COMMENT '指数名称',
  `p_data` decimal(20,2) DEFAULT NULL COMMENT '数值',
  `p_date` datetime DEFAULT NULL COMMENT '日期',
  `p_add` decimal(20,2) DEFAULT NULL COMMENT '比上日增减',
  `p_rate` decimal(5,2) DEFAULT NULL COMMENT '幅度%',
  `p_highdata` decimal(20,2) DEFAULT NULL COMMENT '本年最高',
  `p_highdate` datetime DEFAULT NULL COMMENT '最高值日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35830 DEFAULT CHARSET=utf8;


--
-- Table structure for table `t_stockindex`
--

DROP TABLE IF EXISTS `t_stockindex`;
CREATE TABLE `t_stockindex` (
  `p_year` varchar(4) DEFAULT NULL,
  `p_mindate` datetime DEFAULT NULL,
  `p_maxdate` datetime DEFAULT NULL,
  `p_min` decimal(20,2) DEFAULT NULL,
  `p_max` decimal(20,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Temporary view structure for view `v_minmaxpe`
--

DROP VIEW IF EXISTS `v_minmaxpe`;
CREATE VIEW `v_minmaxpe`
AS
   SELECT LEFT(`t_stock`.`p_date`, 4) AS `p_year`,
          min(`t_stock`.`p_data`) AS `p_min`,
          max(`t_stock`.`p_data`) AS `p_max`
     FROM `t_stock`
    WHERE (`t_stock`.`p_name` = '股票平均市盈率')
   GROUP BY LEFT(`t_stock`.`p_date`, 4);


--
-- Temporary view structure for view `v_pe`
--

DROP VIEW IF EXISTS `v_pe`;
CREATE VIEW `v_pe`
AS
   SELECT `t_stock`.`id` AS `id`,
          `t_stock`.`p_createTime` AS `p_createTime`,
          `t_stock`.`p_modifyTime` AS `p_modifyTime`,
          `t_stock`.`p_creator` AS `p_creator`,
          `t_stock`.`p_modifier` AS `p_modifier`,
          `t_stock`.`p_remark` AS `p_remark`,
          `t_stock`.`p_deleted` AS `p_deleted`,
          `t_stock`.`p_name` AS `p_name`,
          `t_stock`.`p_data` AS `p_data`,
          `t_stock`.`p_date` AS `p_date`,
          `t_stock`.`p_add` AS `p_add`,
          `t_stock`.`p_rate` AS `p_rate`,
          `t_stock`.`p_highdata` AS `p_highdata`,
          `t_stock`.`p_highdate` AS `p_highdate`
     FROM `t_stock`
    WHERE (`t_stock`.`p_name` = '股票平均市盈率')
   ORDER BY `t_stock`.`id` DESC
   
--
-- Temporary view structure for view `v_szcz`
--

DROP VIEW IF EXISTS `v_szcz`;
CREATE VIEW `v_szcz`
AS
   SELECT `t_stock`.`id` AS `id`,
          `t_stock`.`p_createTime` AS `p_createTime`,
          `t_stock`.`p_modifyTime` AS `p_modifyTime`,
          `t_stock`.`p_creator` AS `p_creator`,
          `t_stock`.`p_modifier` AS `p_modifier`,
          `t_stock`.`p_remark` AS `p_remark`,
          `t_stock`.`p_deleted` AS `p_deleted`,
          `t_stock`.`p_name` AS `p_name`,
          `t_stock`.`p_data` AS `p_data`,
          `t_stock`.`p_date` AS `p_date`,
          `t_stock`.`p_add` AS `p_add`,
          `t_stock`.`p_rate` AS `p_rate`,
          `t_stock`.`p_highdata` AS `p_highdata`,
          `t_stock`.`p_highdate` AS `p_highdate`
     FROM `t_stock`
    WHERE (`t_stock`.`p_name` = '深证成指')
   ORDER BY `t_stock`.`id` DESC;
   
   
--
-- Temporary view structure for view `v_ype`
--

DROP VIEW IF EXISTS `v_ype`;
CREATE VIEW `v_ype`
AS
   SELECT `t`.`id` AS `id`,
          `t`.`p_createTime` AS `p_createTime`,
          `t`.`p_modifyTime` AS `p_modifyTime`,
          `t`.`p_creator` AS `p_creator`,
          `t`.`p_modifier` AS `p_modifier`,
          `t`.`p_remark` AS `p_remark`,
          `t`.`p_deleted` AS `p_deleted`,
          `t`.`p_name` AS `p_name`,
          `t`.`p_data` AS `p_data`,
          `t`.`p_date` AS `p_date`,
          `t`.`p_add` AS `p_add`,
          `t`.`p_rate` AS `p_rate`,
          `t`.`p_highdata` AS `p_highdata`,
          `t`.`p_highdate` AS `p_highdate`
     FROM (`t_stock` `t` JOIN `v_minmaxpe` `p`)
    WHERE   (    (`t`.`p_name` = '股票平均市盈率')
             AND (   (`t`.`p_data` = `p`.`p_min`)
                  OR (`t`.`p_data` = `p`.`p_max`))
             AND (left(`t`.`p_date`, 4) = `p`.`p_year`));

--
-- Temporary view structure for view `v_ypeeasy`
--

DROP VIEW IF EXISTS `v_ypeeasy`;
CREATE VIEW `v_ypeeasy`
AS
   SELECT `v_ype`.`id` AS `id`,
          left(`v_ype`.`p_date`, 4) AS `p_year`,
          max(`v_ype`.`p_data`) AS `p_max`,
          min(`v_ype`.`p_data`) AS `p_min`,
          max(`v_ype`.`p_date`) AS `max(p_date)`,
          if((max(`v_ype`.`p_date`) = `v_ype`.`p_highdate`),
             min(`v_ype`.`p_date`),
             max(`v_ype`.`p_date`))
             AS `p_mindate`,
          `v_ype`.`p_highdate` AS `p_maxdate`
     FROM `v_ype`
   GROUP BY left(`v_ype`.`p_date`, 4)
   ORDER BY `p_year` DESC;


-----------------------------------------------------
--
-- Table structure for table `t_gold`
--

DROP TABLE IF EXISTS `t_gold`;
CREATE TABLE `t_gold` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `p_createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `p_modifyTime` datetime DEFAULT NULL COMMENT '修改时间',
  `p_creator` varchar(255) DEFAULT NULL COMMENT '创建人',
  `p_modifier` varchar(255) DEFAULT NULL COMMENT '修改人',
  `p_remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `p_deleted` bit(1) DEFAULT NULL COMMENT '是否删除',
  `p_name` varchar(255) DEFAULT NULL COMMENT '合约',
  `p_opendata` decimal(20,2) DEFAULT NULL COMMENT '开盘价',
  `p_highdata` decimal(20,2) DEFAULT NULL COMMENT '最高价',
  `p_lowdata` decimal(20,2) DEFAULT NULL COMMENT '最低价',
  `p_closedata` decimal(20,2) DEFAULT NULL COMMENT '收盘价',
  `p_add` decimal(20,2) DEFAULT NULL COMMENT '涨跌（元）',
  `p_rate` decimal(5,2) DEFAULT NULL COMMENT '涨跌幅',
  `p_data` decimal(20,2) DEFAULT NULL COMMENT '加权平均价',
  `p_volume` decimal(20,2) DEFAULT NULL COMMENT '成交量',
  `p_amount` decimal(20,2) DEFAULT NULL COMMENT '成交金额',
  `p_openinterest` decimal(20,2) DEFAULT NULL COMMENT '持仓量',
  `p_settlement` decimal(20,2) DEFAULT NULL COMMENT '交收量',
  `p_date` datetime DEFAULT NULL COMMENT '日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

