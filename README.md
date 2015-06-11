# Financial Data

This project aims at showing differences in implementing the same bit of functionality with different programming languages or frameworks.

The applications developed in this project are providing some RESTful endpoints (HTTP GET, POST, etc.) where some path/query parameters can be specified, then the idea is to retrieve a JSON object from a remote API according to those parameteres, then adapting that remote JSON to some specific needs (get/set etc.), 
then showing the outcome as a reply to the original request to the RESTful endpoints.

# Architecture

The __backend__ service can be provided by 3 different applications:

 - The __Node.js__  and __Express.js__ application using the __request__ module to handle the HTTP protocol and __winston__ as the logging framwork, __grunt__ as the task runner;
 - The __Java__ application using __Dropwizard__ as the MVC implementation and __Dagger__ for dependency injection along with __Gradle__ as the building tool;
 - The __go (golang)__ application using __logrus__ as the logging framework and __gorilla__ as a set of utilities for the HTTP level.

 The frontend uses __React (React.js)__ and the __D3.js__ data visualization library.

## Implementations

* [Node.js RESTful implementation](./node-implementation)
* [Java RESTful implementation](./java-implementation)
* [Go (golang) RESTful implementation](./go-implementation)
* [Frontend implementation (React with D3.js)](./frontend-js-implementation)

## Running environment

A handy virtual machine containing _docker_ and this repository itself has been provided at this path: [vagrant-stuff](./vagrant-stuff).

