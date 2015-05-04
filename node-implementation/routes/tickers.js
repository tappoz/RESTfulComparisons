var express = require('express');
var quandlApiRetrieval = require('../modules/quandlApiRetrieval');
var logger = require('../modules/logger');

var router = express.Router();

router.get('/:tickerCode', function(req, res) {
  
  var dataDetails = {};

  dataDetails.tickerSymbol = req.params.tickerCode;

  dataDetails.queryParams = {};
  dataDetails.queryParams.trim_start = '2015-01-01';
  dataDetails.queryParams.trim_end = '2015-03-14';
  dataDetails.queryParams.column = '4'; // closing price

  quandlApiRetrieval.getData(dataDetails, function(errorData, retrievedData) {

    logger.debug('dataDetails: %j', dataDetails, {});

    var relevantData = {};
    relevantData.tickerCode = retrievedData.code;
    relevantData.frequency = retrievedData.frequency;
    // relevantData.closingPrice = retrievedData.data;
    relevantData.dailyStockData = [];
    for(var i = 0; i < retrievedData.data.length; i++) {
      var currentElement = {};
      currentElement.date = retrievedData.data[i][0];
      currentElement.closingPrice = retrievedData.data[i][1];
      relevantData.dailyStockData.push(currentElement);
    }
    logger.debug('Just pushed ' + relevantData.dailyStockData.length + ' elements for the closing price!');

    if(retrievedData.warning) {

      logger.warn('The data is coming from a static loading of a file');
      relevantData.warning = retrievedData.warning;
    }

    logger.debug('relevantData: %j', relevantData, {});

    logger.info('About to return data for ticker code: %s', retrievedData.code);
    res.send(relevantData);
  });
});

module.exports = router;