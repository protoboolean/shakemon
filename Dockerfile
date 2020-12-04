FROM gradle:6.7-jdk11 as builder
ENV APP_HOME=/home/shakemon
WORKDIR $APP_HOME
COPY app/src app/src
COPY app/build.gradle app/build.gradle
COPY settings.gradle .
RUN gradle build

FROM openjdk:11-jre
WORKDIR /home/shakemon
COPY --from=builder /home/shakemon/app/build/distributions/app.zip app.zip
RUN unzip app.zip
RUN mv app/* .
RUN rm app.zip
RUN rmdir app
ENTRYPOINT ["bin/app"]
