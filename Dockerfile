FROM openjdk:17-jdk-alpine3.13
ADD target/codenature-0.0.1-SNAPSHOT.jar .
CMD java -jar codenature-0.0.1-SNAPSHOT.jar
