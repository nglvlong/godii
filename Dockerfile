# ---------- Build stage ----------
FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

# Copy pom và mvnw trước để cache dependency
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

RUN chmod +x mvnw
RUN sed -i 's/\r$//' mvnw

# Download dependencies trước (cache layer)
RUN ./mvnw dependency:go-offline -B

# Copy source code
COPY src src

# Build jar
RUN ./mvnw clean package -DskipTests


# ---------- Runtime stage ----------
FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

# Set timezone (không cần apk nữa)
ENV TZ=Asia/Ho_Chi_Minh

# Copy jar từ build stage
COPY --from=build /app/target/*.jar app.jar

# Giới hạn RAM cho Render Free (512MB)
ENTRYPOINT ["java","-jar","app.jar"]

# Render sẽ inject PORT
EXPOSE 8080