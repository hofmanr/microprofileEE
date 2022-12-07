# Build
mvn clean package && docker build -t nl.rhofman.mpf/jtt .

# RUN

docker rm -f jtt || true && docker run -d -p 8080:8080 -p 4848:4848 --name jtt nl.rhofman.mpf/jtt 

# System Test

Switch to the "-st" module and perform:

mvn compile failsafe:integration-test