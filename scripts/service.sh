#!/bin/bash

cd /www/resume-scala/
java -Xms512M -Xmx1536M -Xss1M -XX:+CMSClassUnloadingEnabled -XX:MaxPermSize=384M -jar /www/resume-scala/sbt-launch.jar -Dport=8080 run

