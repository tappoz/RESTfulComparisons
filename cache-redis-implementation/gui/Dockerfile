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

RUN npm install -g redis-commander
#  $ redis-commander

# installing ifconfig and telnet
RUN apt-get install -y \
    net-tools \
    telnet

# $ redis-commander --redis-host $REDIS_PORT_6379_TCP_ADDR --redis-port $REDIS_PORT_6379_TCP_PORT
EXPOSE 8081
CMD redis-commander --redis-host $REDIS_PORT_6379_TCP_ADDR --redis-port $REDIS_PORT_6379_TCP_PORT
