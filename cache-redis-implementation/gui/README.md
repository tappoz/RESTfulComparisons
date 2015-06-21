# Docker commands

```
$ docker build -t redis_gui_i .
$ docker run -d -p 127.0.0.1:8081:8081 --name redis_gui_c --link redis_c:redis -i -t redis_gui_i
$ docker run -d -p 127.0.0.1:8081:8081 --name redis_gui_c --link redis_c:redis -i -t redis_gui_i ; docker logs -f redis_gui_c
$ docker exec -it redis_gui_c bash
```

The _docker link_ triggers the assignement of a series of _environment variables_ related to the redis cache running image. All these environment variables are identified by a prefix matching the link alias (cfr. the right end side of the colon `--link redis_c:redis`):
```
# env | grep REDIS
REDIS_PORT_6379_TCP_PROTO=tcp
REDIS_NAME=/redis_gui_c/redis
REDIS_PORT_6379_TCP_ADDR=172.17.0.59
REDIS_PORT_6379_TCP_PORT=6379
REDIS_PORT_6379_TCP=tcp://172.17.0.59:6379
REDIS_PORT=tcp://172.17.0.59:6379
```