FROM debian:wheezy

RUN apt-get update && apt-get install -y \ 
   build-essential \
   curl \
   tcl8.5

RUN mkdir /app
WORKDIR /app

RUN curl -L http://nodejs.org/dist/v0.12.2/node-v0.12.2.tar.gz -o node.tar.gz

RUN tar xvzf node.tar.gz

WORKDIR /app/node-v0.12.2
RUN ./configure
RUN make

# npm install -g redis-commander
#  $ redis-commander

EXPOSE 8080
CMD ["/bin/bash"]