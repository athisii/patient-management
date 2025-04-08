# ---- Stage 1: Build the Application ----
FROM gradle:8.9.0-jdk21 AS builder

WORKDIR /app

COPY gradle gradle
COPY gradlew .
COPY build.gradle.kts .
COPY settings.gradle.kts .
RUN ./gradlew dependencies --no-daemon
COPY src src
RUN ./gradlew clean bootJar --no-daemon

# ---- Stage 2: Run the Application ----
FROM amazoncorretto:21-alpine AS runner

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar
EXPOSE 8084
ENTRYPOINT ["java", "-jar", "app.jar"]


