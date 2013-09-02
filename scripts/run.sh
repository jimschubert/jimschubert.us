#!/bin/bash

screen -dm java -Xms512M -Xmx1536M -Xss1M -XX:+CMSClassUnloadingEnabled -XX:MaxP
ermSize=384M -jar `dirname $0`/sbt-launch.jar -Dport=8080 run
