FROM debian:jessie

RUN apt-get update && apt-get install -y \
    curl \
    gcc \
    git \
    libc6-dev \
    make \
    --no-install-recommends && \ 
    rm -rf /var/lib/apt/lists/*

RUN echo "alias ll='ls -laFh'" >> $HOME/.bashrc

ENV GOLANG_VERSION=1.4.2
ENV GOLANG_SRC_TGZ=go$GOLANG_VERSION.src.tar.gz

RUN curl --insecure  -o /tmp/$GOLANG_SRC_TGZ https://storage.googleapis.com/golang/$GOLANG_SRC_TGZ 
RUN tar zxvf /tmp/$GOLANG_SRC_TGZ -C /usr/src

RUN cd /usr/src/go/src && ./make.bash --no-clean 2>&1

ENV PATH /usr/src/go/bin:$PATH

# RUN mkdir -p /go/src /go/bin && chmod -R 777 /go
# ENV GOPATH /go
# ENV PATH /go/bin:$PATH
# WORKDIR /go

RUN mkdir -p /app
WORKDIR /app
# ENV GOPATH /app

ADD main.go /app/
ADD quandlStructs.go /app/
ADD sampleQuandlTicker.json /app/

RUN git config --global http.sslVerify false
RUN go get
RUN go build

ENTRYPOINT /bin/bash