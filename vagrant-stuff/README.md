# Main commands

To build the virtual machine run the command:
```
$ ./build-vagrant-box.sh
```
To start the just created virtual machine run the command:
```
$ vagrant up
```
To enter the running virtual machine run the command:

```
$ vagrant ssh
```

# Troubleshooting

From the host computer while running the Vagrant guest box:
```
$ sudo nmap -sS -P0 192.168.42.42/32
$ curl -v http://localhost:9090
$ telnet localhost 9091
```
From within the Vagrant box:
```
$ curl http://192.168.42.42:9090
$ iptables --list
$ netstat -nap
```

