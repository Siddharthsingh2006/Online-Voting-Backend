# Java 17 image
FROM eclipse-temurin:17-jdk-alpine

# Install Maven
RUN apk update && apk add maven

# Set working directory
WORKDIR /app

# Copy everything
COPY . .

# Build using Maven
RUN mvn clean package -DskipTests

# Run the jar file
CMD ["sh", "-c", "java -jar target/*.jar"]
