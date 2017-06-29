**1.2.0**
* 添加用户模块
* 用户表添加支持字段，在f_user_register_time字段后：
```
ALTER TABLE `t_user`
ADD COLUMN `f_province_id`  int NULL COMMENT '省份id' AFTER `f_user_register_time`,
ADD COLUMN `f_province`  varchar(50) NULL COMMENT '省份' AFTER `f_province_id`,
ADD COLUMN `f_city_id`  int NULL COMMENT '城市id' AFTER `f_province`,
ADD COLUMN `f_city`  varchar(100) NULL COMMENT '城市' AFTER `f_city_id`;
```

**1.1.0**
* 新增对营销点赞活动支持

**1.0.0**
* 网络体验中心模块
