FROM debian:jessie

RUN apt-get update
RUN apt-get install -y \ 
       build-essential \
       curl \
       python \
       tcl8.5

RUN echo "alias ll='ls -laFh'" >> $HOME/.bashrc

RUN mkdir /app
WORKDIR /app

ENV NODE_VERSION=0.12.7
ENV NODE_FILE_NAME=node-v$NODE_VERSION
RUN curl -L http://nodejs.org/dist/v$NODE_VERSION/$NODE_FILE_NAME.tar.gz -o node.tar.gz

RUN tar xvzf node.tar.gz

WORKDIR /app/$NODE_FILE_NAME
RUN ./configure
RUN make
RUN make install

RUN npm install --global gulp

RUN mkdir -p /app/code/src/
ADD ./src /app/code/src/
ADD gulpfile.js /app/code/
ADD index.html /app/code/
ADD package.json /app/code/

WORKDIR /app/code/
RUN npm install
RUN gulp js

EXPOSE 8000
CMD python -m SimpleHTTPServer 