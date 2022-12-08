# Build
```
mvn clean package && docker build -t nl.rhofman.mpj/mpj .
```
# RUN
```
docker rm -f mpj || true && docker run -d -p 8080:8080 -p 4848:4848 --name mpj nl.rhofman.mpj/mpj
```
# System Test
Switch to the "-st" module and perform:
```
mvn compile failsafe:integration-test
```