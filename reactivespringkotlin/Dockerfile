FROM openjdk:alpine
RUN mkdir -p /usr/src/app
COPY build/libs/reactivespringkotlin-0.0.1-SNAPSHOT.jar /usr/src/app/app.jar
WORKDIR /usr/src/app/
ENTRYPOINT ["/usr/bin/java"]
CMD ["-jar", "-Djava.security.egd=file:/dev/./urandom -Xmx256m", "app.jar"]