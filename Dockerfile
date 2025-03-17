FROM eclipse-temurin:21.0.6_7-jdk-alpine-3.21 as build

ARG JAR_FILE
WORKDIR /build

ADD $JAR_FILE application.jar
RUN java -Djarmode=layertools -jar application.jar extract --destination extracted

FROM eclipse-temurin:21.0.6_7-jdk-alpine-3.21

RUN addgroup spring-boot-group && adduser -D -G spring-boot-group spring-boot
USER spring-boot:spring-boot-group
VOLUME /tmp
WORKDIR /application

COPY --from=build /build/extracted/dependencies .
COPY --from=build /build/extracted/spring-boot-loader .
COPY --from=build /build/extracted/snapshot-dependencies .
COPY --from=build /build/extracted/application .

ENTRYPOINT exec java ${JAVA_OPTS} org.springframework.boot.loader.launch.JarLauncher ${0} ${@}