# Cache - Redis service

# Docker stuff

```
FATA[0000] Get http:///var/run/docker.sock/v1.18/images/json: dial unix /var/run/docker.sock: no such file or directory. Are you trying to connect to a TLS-enabled daemon without TLS?
```
The previous message means you need to start docker as a daemon:
```
$ sudo service docker start
```

## Images and containers

```
$ docker build -t redis_i .
$ docker run -d -p 6379:6379 --name redis_c -i -t redis_i
$ docker run -d -p 6379:6379 --name redis_c -i -t redis_i ; docker logs -f redis_c
$ docker exec -it redis_i bash
```

# Telnet connection

```
$ telnet localhost 6379
Trying 127.0.0.1...
Connected to localhost.
Escape character is '^]'.


ping
+PONG
^]

telnet> quit
Connection closed.
$
```
