**调试运行说明：**
* 添加配置主文件：--config，配置到config.properties文件，需要输入协议，如classpath:config.properties，或path:/data/deploy/***/config.properties
* 添加日志配置文件（可选）：--logging.config，配置到logback.xml文件
* 端口冲突可添加配置项（可选）：--server.port，指定目标端口

**可选配置项说明：**
* 跨域来源：--cors.origins，默认*
* 跨域方法：--cors.methods，默认*
