FROM maven:latest as DEPS
WORKDIR /opt/app
COPY person-client/pom.xml person-client/pom.xml
COPY person-service/pom.xml person-service/pom.xml
COPY web/pom.xml web/pom.xml
COPY pom.xml .

RUN mvn dependency:go-offline


FROM maven:latest as BUILDER
WORKDIR /opt/app
COPY --from=deps /root/.m2 /root/.m2
COPY --from=deps /opt/app/ /opt/app
COPY person-client/src /opt/app/person-client/src
COPY person-service/src /opt/app/person-service/src
COPY web/src /opt/app/web/src

RUN mvn -B -e -o clean install -DskipTests=true


FROM eclipse-temurin:17-alpine
ARG DEPENDENCY=/opt/app/web/target
WORKDIR /opt/app

COPY --from=BUILDER ${DEPENDENCY}/lib ./lib
COPY --from=BUILDER ${DEPENDENCY}/jab-server.jar .

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "jab-server.jar"]
