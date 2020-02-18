FROM openjdk:13-alpine
ARG JAR_FILE
ADD target/${JAR_FILE} /opt/authenteq-greeting-wicket.jar
EXPOSE 8080
ENV SERVER_PORT=8080
ENTRYPOINT ["java", "-jar", "/opt/authenteq-greeting-wicket.jar", "$@"]
