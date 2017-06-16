#!/bin/bash
#待完善，目前仅支持后台启动，并不支持做为Linux服务启动，大概原因在于取jar文件的时候执行路径是相对目录

PROG_ARGS="--config=file:conf/config.properties"
PROG_ARGS="${PROG_ARGS} --logging.config=conf/logback.xml"
PROG_ARGS="${PROG_ARGS} --server.port=8180"

VM_ARGS="-server"

JAR=`ls -t *.jar|head -1`

nohup java ${VM_ARGS} -jar ${JAR} ${PROG_ARGS} 1>>std.log 2>>err.log &
