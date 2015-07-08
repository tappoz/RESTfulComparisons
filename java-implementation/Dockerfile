FROM debian:jessie

# Including in the repositories list the backports
RUN echo "\ndeb http://http.debian.net/debian jessie-backports main" >> /etc/apt/sources.list

RUN apt-get update

RUN apt-get install -y \
  openjdk-8-jdk \
  curl

RUN echo "alias ll='ls -laFh'" >> $HOME/.bashrc

RUN mkdir /app
WORKDIR /app

RUN curl -L https://services.gradle.org/distributions/gradle-2.4-bin.zip -o gradle-2.4-bin.zip
RUN apt-get install -y unzip
RUN unzip gradle-2.4-bin.zip

# RUN echo 'export GRADLE_HOME=/app/gradle-2.4' >> $HOME/.bashrc
# RUN echo 'export PATH=$PATH:$GRADLE_HOME/bin' >> $HOME/.bashrc
# RUN /bin/bash -c "source $HOME/.bashrc"

ENV GRADLE_HOME=/app/gradle-2.4
ENV PATH=$PATH:$GRADLE_HOME/bin

# RUN env

RUN mkdir -p /app/app/src
WORKDIR /app/app
ADD src /app/app/src/
ADD build.gradle /app/app/

RUN gradle test jar

EXPOSE 8080 8081
ENTRYPOINT java -jar build/libs/app-1.0.jar server src/main/resources/apiConfigurations.yml