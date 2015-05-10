FROM debian:wheezy

RUN apt-get update
RUN apt-get install -y build-essential
RUN apt-get install -y curl 
RUN apt-get install -y tcl8.5

RUN echo '   ~~~> Done with OS setup'

RUN mkdir /app
WORKDIR /app

RUN curl -L http://download.redis.io/releases/redis-3.0.1.tar.gz -o redis.tar.gz

RUN tar xvzf redis.tar.gz

WORKDIR /app/redis-3.0.1 
RUN pwd
RUN make
# RUN make test
# RUN make install 

RUN echo '   ~~~> Done with REDIS download and installation in: ' `pwd`

EXPOSE 6379
ENTRYPOINT src/redis-server
