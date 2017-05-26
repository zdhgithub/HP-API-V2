#!/bin/bash
#待完善，当先提交一个新版本到当前目录（文件名中版本不同），当前运行机制肯定找不到对应的PID

JAR=`ls -t *.jar|head -1`

PID=$(ps -ef | grep ${JAR} | grep -v grep | awk '{ print $2 }')
if [ -z "$PID" ]
then
	echo Application is already stopped
else
	echo kill $PID
	kill $PID
fi
