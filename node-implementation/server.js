var express = require('express');
var config = require('./modules/config');
var tickers = require('./routes/tickers');
var logger = require('./modules/logger');

var app = express();

app.use(function(req, res, next) {
  res.header("Access-Control-Allow-Origin", "*");
  res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
  next();
});

app.use('/ticker', tickers);

var server = app.listen(config.nodeServerPort,function(){

  logger.info('Server started on port: %d', config.nodeServerPort);
});

