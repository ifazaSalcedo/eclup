ARG MSVS_NAME=ms-ventas

FROM openjdk:17-alpine as build

ARG MSVS_NAME

WORKDIR /app/$MSVS_NAME

COPY ./pom.xml /app
COPY ./$MSVS_NAME/.mvn ./.mvn
COPY ./$MSVS_NAME/mvnw .
COPY ./$MSVS_NAME/pom.xml .

#RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.test.main -Dspring-boot-repackage.skip && rm -r ./target/
RUN ./mvnw dependency:go-offline

COPY ./$MSVS_NAME/src ./src

RUN ./mvnw clean package -DskipTests


FROM openjdk:17-alpine

ARG MSVS_NAME

WORKDIR /app
ARG TARGERT_FORDEL=/app/$MSVS_NAME/target
COPY --from=build $TARGERT_FORDEL/ms-ventas-0.0.1-SNAPSHOT.jar .

ARG PORT_APP=8081
ENV PORT=$PORT_APP

EXPOSE $PORT

ENTRYPOINT ["java", "-jar", "ms-ventas-0.0.1-SNAPSHOT.jar"]
