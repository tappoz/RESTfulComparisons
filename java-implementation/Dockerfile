FROM debian:jessie

# Including in the repositories list the backports
RUN echo "\ndeb http://http.debian.net/debian jessie-backports main" >> /etc/apt/sources.list

RUN apt-get update

RUN apt-get install -y \
  openjdk-8-jdk

EXPOSE 8080 8081
ENTRYPOINT bin/bash