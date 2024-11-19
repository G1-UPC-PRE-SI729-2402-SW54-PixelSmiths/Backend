FROM openjdk:21

WORKDIR /app

COPY target/TakeMyCar-0.0.1-SNAPSHOT.jar /app/takemycar.jar

EXPOSE 3000

CMD ["java", "-jar", "/app/takemycar.jar"]
