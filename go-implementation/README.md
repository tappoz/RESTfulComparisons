# Setup of the environment

To get a working environment with multiple versions of the go programming language you may want to have a look at `gvm`.

At the root level of this ___go project___ you need to run some `go get` commands to import the 3rd party packages e.g.: `go get github.com/Sirupsen/logrus` for the logging framework (where you can set different logging levels), `go get github.com/gorilla/mux` for the router that needs to be attached to the server provided by `net/http`.

# Build process

You need to run `go build`, this will generate an executable file with the same name of the containing folder of this project (`go-implementation`).
You can then run the program from the command line with:
```
./go-implementation
```
You should see the logging statements directly in the console standard output.

# Run the application

You need to perform some HTTP GET requests (you may want to use `postman` on the Google Chrome browser):

- `http://localhost:8080/ticker/{tickerCode}` where `{tickerCode}` for example could be `AAPL`
- `http://localhost:8080/ticker?tickerCode={tickerCode}` where `{tickerCode}` for example could be `AAPL`

# Docker commands

```
$ docker stop golang_c ; docker rm golang_c
$ docker build -t golang_i .
$ docker run -d --name golang_c -i -t golang_i ; docker logs -f golang_c
$ docker exec -it golang_c bash
```

