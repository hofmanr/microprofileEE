#!/bin/sh
mvn clean package && docker build -t nl.rhofman.mpf/mpj .
docker rm -f mpj || true && docker run -d -p 8080:8080 -p 4848:4848 --name mpj nl.rhofman.mpj/mpj
