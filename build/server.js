var express = require('express');
var config = require('./modules/config');
var tickers = require('./routes/tickers');
var logger = require('./modules/logger');

var app = express();

app.use('/tickers', tickers);

var server = app.listen(config.nodeServerPort,function(){

  logger.info('Server started on port: %d', config.nodeServerPort);
});

