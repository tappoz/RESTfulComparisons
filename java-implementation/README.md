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


# Running the project

```
$ java -jar build/libs/java-implementation-1.0.jar server src/main/resources/hello-world.yml
$ java -jar build/libs/java-implementation-1.0.jar server src/main/resources/apiConfigurations.yml
```


# Endpoints

 - `http://localhost:8080/hello-world`
 - `http://localhost:8080/hello-world?name=Tappoz`
 - `http://localhost:8080/ticker?tickerCode=AAPL`
 - `http://localhost:8080/ticker/AAPL`
 
## Metrics

`http://localhost:8081/`