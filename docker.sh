#!/bin/bash

echo "=======SHELL SCRIPT PROCESS STARTS======="
#git pull origin master
./gradlew buildDocker
docker-compose -f src/main/docker/docker-compose.yml down
docker-compose -f src/main/docker/docker-compose.yml up -d
#cd ~/docker/nginx
#docker build -t nginx:1.0.0 .
#docker stop bbqnginx
#docker stop bbqtrello
#docker rm bbqnginx
#docker rm bbqtrello
#docker run -d --name bbqtrello bbqtrello-docker
#docker run -d --name bbqnginx --link bbqtrello -p 80:80 nginx:1.0.0: