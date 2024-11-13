# Etapa 1: Compilar o projeto com Maven
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY backend/pom.xml /app/
COPY backend/src /app/src/
RUN mvn clean package -DskipTests

# Etapa 2: Criar a imagem final com o JAR gerado
FROM amazoncorretto:17-alpine
WORKDIR /app
COPY --from=build /app/target/projeto-integrador-iv-0.0.1-SNAPSHOT.jar /app/
CMD ["java", "-jar", "projeto-integrador-iv-0.0.1-SNAPSHOT.jar"]
