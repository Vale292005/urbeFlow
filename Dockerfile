# Paso 1: Construir el archivo JAR usando Maven y Java 21 🚀 (Cambiado de 17 a 21)
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package 

# Paso 2: Ejecutar la aplicación con Java 21 compatible con Mac M1/M2/M3 🍏 (Cambiado de 17 a 21)
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Exponer el puerto del backend y de la consola H2
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]