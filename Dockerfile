FROM openjdk:17-jdk
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests
CMD ["java", "-jar", "target/reservas-hoteis-0.0.1-SNAPSHOT.jar"]
