#!/bin/bash

REPOSITORY=/home/ec2-user/app/travis
PROJECT_NAME=spring-webservice

echo "> Build 파일 복사"

cp $REPOSITORY/zip/*.jar $REPOSITORY/

CURRENT_PID=$(pgrep -fl spring-webservice | grep jar | awk '{print $1}')

echo "> 현재 구동중인 애플리케이션 pid : $CURRENT_PID"

if [ -z $CURRENT_PID ]; then
    echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다."
else
    echo "> kill -15 $CURRENT_PID"
    kill -15 $CURRENT_PID
    sleep 5
fi

echo "> 새 어플리케이션 배포"

========여기부터
cp $REPOSITORY/build/build/libs/*.jar $REPOSITORY/jar/

JAR_NAME=$(ls -tr $REPOSITORY/jar/ |grep 'spring-webservice' | tail -n 1)

echo "> JAR Name: $JAR_NAME"

nohup java -jar $REPOSITORY/jar/$JAR_NAME &
~
