FROM maven:3.9.9-eclipse-temurin-21-jammy as build

WORKDIR /app

COPY pom.xml .

COPY src ./src

RUN mvn clean package -DskipTests

FROM openjdk:21-jdk  

WORKDIR /app

COPY --from=build /app/target/day1-0.1.jar app.jar

CMD ["java", "-jar", "app.jar"]