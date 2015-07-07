FROM debian:jessie

# Including in the repositories list the backports
RUN echo "\ndeb http://http.debian.net/debian jessie-backports main" >> /etc/apt/sources.list

RUN apt-get update

RUN apt-get install -y \
  openjdk-8-jdk \
  curl

RUN mkdir /app
WORKDIR /app

RUN curl -L https://services.gradle.org/distributions/gradle-2.4-bin.zip -o gradle-2.4-bin.zip
RUN apt-get install -y unzip
RUN unzip gradle-2.4-bin.zip

RUN echo 'export GRADLE_HOME=/app/gradle-2.4' >> $HOME/.bashrc
RUN echo 'export PATH=$PATH:$GRADLE_HOME/bin' >> $HOME/.bashrc
RUN /bin/bash -c "source $HOME/.bashrc"

RUN mkdir -p /app/app/src
WORKDIR /app/app
ADD src /app/app/src/
ADD build.gradle /app/app/

# RUN gradle test jar

EXPOSE 8080 8081
ENTRYPOINT /bin/bash