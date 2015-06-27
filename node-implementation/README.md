Financial data
==============

The data is retrieved via the API provided by Quandl. It is then internally filtered by the Javascript code. The filtered data is then provided as an internal API via an Express application server.

Running the code
----------------
To run the code you can use multiple tools like `npm` or the `grunt` task runner.

First of all you need to install all the local dependencies (according to the file `package.json`), to do that use the following command:

```
$ npm install
```

### Using npm

```
$ npm start
```
Bare in mind that Node.js is assuming there's a logging file at this path: `./logs/all-logs.log`.

### Using grunt

The _grunt task runner_ needs first of all to be installed as a _global_ dependency.
```
$ npm install -g grunt-cli
```

All the tasks that grunt can perform are described in the `Gruntfile.js`, e.g. the log file `./logs/all-logs.log` gets created automatically.

To run the application you need to execute the `server` task:
```
$ grunt server
```

Using the application
---------------------

To fetch the relevant data regarding a ticker perform an HTTP GET on `http://localhost:8080/tickers/:tickerCode` where e.g. `tickerCode` for Apple is `AAPL`.

You may want to use a _web browser plugin_ e.g. __postman__ for __Chrome__.


Containers
----------

```
$ docker build -t backend_node_i .
$ docker run -d -p 8082:8080 --name backend_node_c -i -t backend_node_i ; docker logs -f backend_node_c
```

If running the application inside a docker container, then you could perform an HTTP GET at `http://localhost:8082/ticker/AAPL` to check that everything is working fine.

