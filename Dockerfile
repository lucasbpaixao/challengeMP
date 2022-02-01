FROM maven:3.8.4-openjdk-8-slim

WORKDIR /app

COPY . /app

RUN mvn clean package -DskipTests

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar

RUN chmod +x /app/entrypoint.sh

CMD ["/app/entrypoint.sh"]