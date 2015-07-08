# Overview

This implementation uses __Dropwizard__ as the main dependency to develop a RESTful web service.
Dropwizard itself already contains lots of useful dependencies like __Jersey__ to deal with the HTTP protocol,
__Jetty__ as a servlet engine, __Jackson__ to deal with JSON object and __Logback__ as the logging framework.
For the __dependency injection__ I've used __Dagger__.
For testing purposes __Junit__, __Mockito__ and __Hamcrest__ have been used.

This application contains some "Hello world" end points as a minimum working example using Dropwizard,
it also contains the actual useful endpoints that deal with the Quandl API.


# Building the project

```
$ gradle --refresh-dependencies
$ gradle idea
$ gradle clean jar
```


# Tests

```
$ gradle clean test
```
To be sure all the test (both __unit tests__ and __integration tests__) won't fail they need to be run from the command line using `gradle`.
Some of the tests will fail if executed inside an IDE (e.g. IntelliJ IDEA), the reason is that there is some code missing
because of the Dagger dependency injection framework that needs to generate the source code according to the annotations found in the code.


# Running the project

```
$ java -jar build/libs/java-implementation-1.0.jar server src/main/resources/apiConfigurations.yml
```
The YAML configuration file is currently inside the `resources` folder as per Maven requirements, but that's not mandatory.
In fact it is currently used only at runtime with the `java -jar` command.

Inside the docker container this is the command:
```
java -jar build/libs/app-1.0.jar server src/main/resources/apiConfigurations.yml
```

The reason why the JAR name is different is because of the folder where the source code is contained in (this is because of how Dropwizard works by default).


# Endpoints

 - `http://localhost:8080/ticker?tickerCode=AAPL`
 - `http://localhost:8080/ticker/AAPL`


## Metrics

`http://localhost:8081/`

# Containers

```
$ docker build -t java_i .
$ docker stop java_c ; docker rm java_c
$ docker run -d -p 8083:8080 -p 8084:8081 --name java_c -i -t java_i ; docker logs -f java_c
$ docker exec -it java_c bash
```