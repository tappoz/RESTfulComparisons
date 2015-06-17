# Docker commands

```
$ docker build -t redis_gui_i .
$ docker run -d -p 127.0.0.1:8080:8080 -p 127.0.0.1:9000:9000 --name redis_gui_c -i -t redis_gui_i
$ docker exec -it redis_gui_c bash
```
