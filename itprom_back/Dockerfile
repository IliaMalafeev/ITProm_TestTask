FROM maven:3.8.7-openjdk-18-slim AS build

WORKDIR /app

COPY ./pom.xml /app/pom.xml

COPY ./src/main/java/iliamalafeev/itpromtesttask/ItPromTestTaskApplication.java /app/src/main/java/iliamalafeev/itpromtesttask/ItPromTestTaskApplication.java

RUN mvn -f /app/pom.xml clean package

COPY ../.. /app

RUN mvn -f /app/pom.xml clean package


FROM openjdk:22-jdk-slim

WORKDIR /app

EXPOSE 8080

COPY --from=build /app/target/itprom_back-0.0.1-SNAPSHOT.jar /app/itprom_back.jar

ENTRYPOINT ["sh", "-c", "java -jar itprom_back.jar"]