FROM eclipse-temurin:17
LABEL authors="antonkurbet"
COPY build/libs/lab5-0.1.0.jar /app/lab5.jar
ENTRYPOINT java ${JAVA_OPTS:-"-XX:MaxRAMPercentage=85.0"} -jar /app/lab5.jar
EXPOSE 8080

