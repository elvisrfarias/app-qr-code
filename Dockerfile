FROM maven:3.9.6-eclipse-temurin-21 as build
WORKDIR /app

COPY pom.xml .
COPY src ./src
RUN clean package -DskipTest


FROM maven:3.9.6-eclipse-temurin-21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Argumentos, valores recebido através de cariaveis de ambiente
ARG AWS_ACESS_KEY_ID
ARG AWS_SECRET_ACESS_KEY

ENV AWS_REGION=us-east-1
ENV AWS_S3_BUCKET=elvis-qrcode-2025

ENTRYPOINT ["java", ".jar", "app.jar"]