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