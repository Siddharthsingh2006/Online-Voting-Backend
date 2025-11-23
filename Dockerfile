# Use Java 17
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy entire project
COPY . .

# If you have mvnw, use it, otherwise use Maven
RUN ./mvnw clean package -DskipTests || mvn clean package -DskipTests

# Run jar (auto-detect the jar name)
CMD ["sh", "-c", "java -jar target/*.jar"]
