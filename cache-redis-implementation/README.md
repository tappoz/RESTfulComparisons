# Cache - Redis service

FATA[0000] Get http:///var/run/docker.sock/v1.18/images/json: dial unix /var/run/docker.sock: no such file or directory. Are you trying to connect to a TLS-enabled daemon without TLS?

$ sudo service docker start


# Images and containers

$ sudo docker build -t redis_i .

$ sudo docker run -d --name redis_c -i -t redis_i

$ docker ps

$ docker exec -it 110c75037fdd bash


