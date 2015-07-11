FROM debian:jessie

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