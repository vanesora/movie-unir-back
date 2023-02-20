FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/*.jar movie.jar
ENTRYPOINT ["java","-jar","/movie.jar"]