$ sudo docker build -t redis_gui_i .

$ sudo docker run -d --name redis_gui_c -i -t redis_gui_i

$ docker exec -it 918b6dbad09e bash

