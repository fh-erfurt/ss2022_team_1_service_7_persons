FROM maven:3.8.5-openjdk-17-slim as build
WORKDIR /workspace/app
#COPY . .
# Copy POM
# modules
COPY person-client/pom.xml person-client/pom.xml
COPY person-service/pom.xml person-service/pom.xml
COPY web/pom.xml web/pom.xml
# main
COPY pom.xml .


# get all the downloads out of the way
RUN mvn org.apache.maven.plugins:maven-dependency-plugin:go-offline


# all modules
COPY person-client/src person-client/src
COPY person-service/src person-service/src
COPY web/src web/src

WORKDIR WORKDIR /workspace/app/web
RUN mvn package -DskipTests


FROM openjdk:11.0.15-jre-buster
ARG DEPENDENCY=/workspace/app/web/target

WORKDIR /app
COPY --from=build ${DEPENDENCY}/lib ./lib
COPY --from=build ${DEPENDENCY}/jab-server.jar .

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "jab-server.jar"]
