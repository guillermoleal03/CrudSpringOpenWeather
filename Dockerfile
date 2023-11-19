FROM openjdk:20
VOLUME /tmp
EXPOSE 8080
ADD ./target/weather-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT["java","-jar","/app.jar"]