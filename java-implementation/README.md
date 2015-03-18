# Building the project

```
$ gradle --refresh-dependencies
$ gradle idea
$ gradle clean jar
```

# Running the project

```
$ java -jar build/libs/java-implementation-1.0.jar server src/main/resources/hello-world.yml
```

# Endpoints

 - `http://localhost:8080/hello-world`
 - `http://localhost:8080/hello-world?name=Tappoz`
 
## Metrics

`http://localhost:8081/`