FROM openjdk:21-jdk

WORKDIR /app

COPY . /app

EXPOSE 8084

ENTRYPOINT ["java", "-jar", "/app/build/aviatickets-0.0.1-SNAPSHOT.jar"]