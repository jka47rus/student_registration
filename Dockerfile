FROM openjdk:21-oracle

WORKDIR /app

COPY build/libs/... app.jar # вставить собранный образ jar

CMD ["java", "-jar", "app.jar"]