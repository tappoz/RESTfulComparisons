FROM debian:jessie

RUN apt-get update 
RUN apt-get install -y \ 
	   build-essential \
       curl \
       tcl8.5

RUN echo "alias ll='ls -laFh'" >> $HOME/.bashrc

RUN mkdir /app
WORKDIR /app

RUN curl -L http://download.redis.io/releases/redis-3.0.1.tar.gz -o redis.tar.gz

RUN tar xvzf redis.tar.gz

WORKDIR /app/redis-3.0.1 
RUN make
# RUN make test
# RUN make install

EXPOSE 6379
ENTRYPOINT src/redis-server