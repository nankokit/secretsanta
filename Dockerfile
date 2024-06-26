FROM maven:3-eclipse-temurin-21-alpine AS build

WORKDIR /app

COPY src/main/resources/checkstyle.xml pom.xml ./
RUN mvn clean verify --fail-never -DskipTests
COPY src ./src
RUN mvn package -DskipTests

FROM eclipse-temurin:21-alpine
WORKDIR /app

COPY --from=build /app/target/secretsanta-0.0.1-SNAPSHOT.jar secretsanta.jar

CMD ["java", "-jar", "secretsanta.jar"]