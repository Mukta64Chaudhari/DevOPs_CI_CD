# Stage 1 - Build
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2 - Run
FROM tomcat:9.0-jdk17

WORKDIR /usr/local/tomcat
RUN rm -rf webapps/*

COPY --from=build /app/target/library.war webapps/ROOT.war

# Env config
ENV BOOKS_FILE=/data/books.json

# Health check
HEALTHCHECK --interval=30s --timeout=5s \
CMD curl -f http://localhost:8080/ || exit 1

EXPOSE 8080

CMD ["catalina.sh", "run"]