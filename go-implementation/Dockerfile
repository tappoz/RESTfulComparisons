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

RUN mkdir -p /go/src/app/
RUN mkdir -p /go/bin/
RUN chmod -R 777 /go 
ENV GOPATH /go
ENV PATH /go/bin:$PATH

ADD main.go /go/src/app/
ADD quandlStructs.go /go/src/app/
ADD sampleQuandlTicker.json /go/src/app/

RUN git config --global http.sslVerify false
WORKDIR /go/src/app
RUN go get
RUN go build

EXPOSE 8080
ENTRYPOINT /go/bin/app