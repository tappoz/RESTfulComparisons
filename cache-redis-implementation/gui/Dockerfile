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

RUN apt-get install -y \
    telnet

ENTRYPOINT ["redis-commander", "--redis-host", "127.0.0.1", "--redis-port", "9000"]
#ENTRYPOINT ["/bin/bash"]
