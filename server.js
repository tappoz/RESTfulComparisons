var express = require('express');
var config = require('./modules/config');
var tickers = require('./routes/tickers');

var app = express();

app.use('/tickers', tickers);

var server = app.listen(config.nodeServerPort,function(){

  console.log('Server started on port:', config.nodeServerPort);
});

