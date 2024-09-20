#######################################################################################################################
#
# Build stage
#
#######################################################################################################################
FROM ghcr.io/graalvm/graalvm-ce:ol9-java17-22.3.0 as builder
WORKDIR /app

# Gradle Wrapper 복사
COPY gradlew .
COPY gradle gradle
COPY gradle/ .
RUN chmod +x ./gradlew

# 소스코드 복사
COPY . .

# Install necessary tools
RUN microdnf install -y findutils

# 빌드 진행
RUN ./gradlew nativeCompile


#######################################################################################################################
#
# Runtime stage
#
#######################################################################################################################
FROM oraclelinux:9-slim
WORKDIR /app

# Copy the native executable from the builder stage
COPY --from=builder /app/build/native/nativeCompile/graalvm-example /app/application

# Set the executable permissions
RUN chmod +x /app/application

# Expose the port the app runs on
EXPOSE 8080

# Run the application
CMD ["/app/application"]
