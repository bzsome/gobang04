#!/bin/bash

#chkconfig: 2345 80 90
#description:NettyServer

JAVA_HOME=/usr/local/java/jdk1.8.0_181
CLASSPATH=$JAVA_HOME/lib/
PATH=$PATH:$JAVA_HOME/bin
export PATH JAVA_HOME PATH

nohup java -jar /usr/local/tomcat8/webapps/gobang04-netty.jar >/tmp/gobang04-netty.log &