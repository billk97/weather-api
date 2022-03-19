FROM amazoncorretto:17.0.2
WORKDIR /app
COPY target/quarkus-app /app/
ENTRYPOINT "java" "-jar" "/app/quarkus-run.jar"
EXPOSE 5200

