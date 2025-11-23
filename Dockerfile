# Use Java 17
FROM eclipse-temurin:17-jdk-alpine

# Install Maven
RUN apk update && apk add maven

# Set Work Directory
WORKDIR /app

# Copy everything
COPY . .

# Give execution permission to mvnw (important!)
RUN chmod +x mvnw || true

# Build the Spring Boot project
RUN ./mvnw clean package -DskipTests || mvn clean package -DskipTests

# Run the generated jar file
CMD ["sh", "-c", "java -jar target/*.jar"]
