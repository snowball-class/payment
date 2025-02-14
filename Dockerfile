# Build stage
FROM gradle:7.6-jdk17-alpine AS build
WORKDIR /app
COPY . .
RUN ./gradlew clean build --no-daemon

# Runtime stage
FROM amazoncorretto:17.0.7-alpine AS runtime
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "app.jar"]