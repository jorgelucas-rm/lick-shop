FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY backend/pom.xml /app/
COPY backend/src /app/src/
RUN mvn clean package -DskipTests

FROM amazoncorretto:17-alpine
WORKDIR /app
COPY --from=build /app/target/projeto-integrador-iv-0.0.1-SNAPSHOT.jar /app/
