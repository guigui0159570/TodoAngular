# Création du Jar
FROM eclipse-temurin:latest AS build
WORKDIR /app
COPY ./ .
RUN ./gradlew bootJar

# Création du exécutable
ARG port=8080

FROM eclipse-temurin:latest as app
WORKDIR /app
COPY --from=build /app/build/libs/app.jar app.jar
ENV server.address 0.0.0.0
ENV server.port $port
EXPOSE $port

ENTRYPOINT [ "java", "-jar", "app.jar" ]