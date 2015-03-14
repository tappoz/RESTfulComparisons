Financial data
==============

The data is retrieved via the API provided by Quandl. It is then internally filtered by the Javascript code. The filtered data is then provided as an internal API via an Express application server.

To run the code:
```
$ npm start
```
Bare in mind that Node.js is assuming there's a logging file at this path: `./logs/all-logs.log`.

To fetch the relevant data regarding a ticker perform an HTTP GET on `http://localhost:3000/tickers/:tickerCode` where e.g. `tickerCode` for Apple is `AAPL`.

