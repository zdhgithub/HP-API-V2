<p>
#调试运行说明：  
* 添加配置主文件：--config，配置到config.properties文件 
* 添加日志配置文件：--logging.config，配置到logback.xml文件
</p>

<p>
#可选配置项说明：
* 设置cors。origin：--cors.origin，默认为*
</p>

##1.0  
<p>
##新增库结构  
CREATE TABLE `t_mp_alliance` (
  `f_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '加盟商流水号',
  `f_uid` int(11) NOT NULL COMMENT '用户id',
  `f_phone_number` varchar(15) NOT NULL COMMENT '手机号码',
  `f_shop_name` varchar(100) NOT NULL COMMENT '渔具店名称',
  `f_name` varchar(50) NOT NULL COMMENT '姓名',
  `f_address` varchar(100) NOT NULL COMMENT '店铺地址',
  `f_longitude` double NOT NULL COMMENT '经度',
  `f_latitude` double NOT NULL COMMENT '纬度',
  `f_status` int(11) NOT NULL COMMENT '状态',
  `f_stock` varchar(1000) DEFAULT NULL COMMENT '体验库存',
  `f_apply_time` datetime DEFAULT NULL COMMENT '申请加盟时间',
  `f_audit_time` datetime DEFAULT NULL COMMENT '审核时间',
  PRIMARY KEY (`f_id`),
  UNIQUE KEY `f_uid` (`f_uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
</p>