#!/bin/bash
if [ -f /home/ec2-user/app/travis/deploy.sh ]

then

/home/ec2-user/app/travis/deploy.sh &

else

echo "why so serius"

