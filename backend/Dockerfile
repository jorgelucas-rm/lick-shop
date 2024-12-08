# Compilar o projeto com Maven
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY pom.xml /app/
COPY src /app/src/
RUN mvn clean package -DskipTests

# Criar a imagem final com o JAR gerado
FROM amazoncorretto:17-alpine
WORKDIR /app

# Copiar e executar o script wait-for-it.sh no container
COPY wait-for-it.sh /usr/local/bin/wait-for-it.sh
RUN apk add --no-cache bash
RUN chmod +x /usr/local/bin/wait-for-it.sh

# Copiar o JAR gerado da etapa de build
COPY --from=build /app/target/projeto-integrador-iv-0.0.1-SNAPSHOT.jar /app/

# Expor a porta onde a aplicação Spring vai rodar
EXPOSE 8080

# just do it
CMD ["/usr/local/bin/wait-for-it.sh", "database:3306", "--", "sh", "-c", "java -jar projeto-integrador-iv-0.0.1-SNAPSHOT.jar"]