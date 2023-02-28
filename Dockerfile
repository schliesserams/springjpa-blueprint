FROM bellsoft/liberica-openjdk-alpine-musl:17

RUN apk update && apk upgrade

# Add app user
ARG APPLICATION_USER=appuser
RUN adduser --no-create-home -u 1000 -D $APPLICATION_USER

# Configure working directory
RUN mkdir /app && \
    chown -R $APPLICATION_USER /app

# Configure the newrelic directory
RUN mkdir /opt/newrelic && \
    chown -R $APPLICATION_USER /app

USER 1000

COPY --chown=1000:1000 ./newrelic/newrelic.yml /opt/newrelic/newrelic.yml
COPY --chown=1000:1000 ./newrelic/newrelic.jar /opt/newrelic/newrelic.jar
COPY --chown=1000:1000 ./target/spring-jpa-blueprint-0.0.1-SNAPSHOT.jar /app/app.jar
WORKDIR /app

EXPOSE 8080
ENV NEW_RELIC_LOG_FILE_NAME=STDOUT
ENTRYPOINT ["java", "-javaagent:/opt/newrelic/newrelic.jar", "-Xms1536m", "-jar", "/app/app.jar"]