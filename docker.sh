#!/bin/bash

git pull
./gradlew buildDocker
docker-compose down
docker-compose up -d
#cd ~/docker/nginx
#docker build -t nginx:1.0.0 .
#docker stop bbqnginx
#docker stop bbqtrello
#docker rm bbqnginx
#docker rm bbqtrello
#docker run -d --name bbqtrello bbqtrello-docker
#docker run -d --name bbqnginx --link bbqtrello -p 80:80 nginx:1.0.0