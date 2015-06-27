FROM debian:wheezy

RUN apt-get update && apt-get install -y \ 
   build-essential \
   curl \
   python \
   tcl8.5

RUN mkdir /app
WORKDIR /app

RUN curl -L http://nodejs.org/dist/v0.12.2/node-v0.12.2.tar.gz -o node.tar.gz

RUN tar xvzf node.tar.gz

WORKDIR /app/node-v0.12.2
RUN ./configure
RUN make
RUN make install

RUN npm install -g grunt-cli

RUN mkdir -p /app/app
WORKDIR /app/app
ADD . /app/app

RUN npm install
RUN grunt build

EXPOSE 8080
CMD node build/server.js

