#!/bin/bash

[ "$#" -ne 1 ] && echo Required first parameter is PID file && exit 1

cd /www/resume-scala/
nohup java -Xms6M -Xmx80M -Xss1M -XX:+CMSClassUnloadingEnabled -XX:MaxPermSize=64M -jar /www/resume-scala/sbt-launch.jar -Dcom.twitter.finatra.config.env=production -Dport=8080 run &

echo $! > $1

