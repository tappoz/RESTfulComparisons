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

    var retrievedDataObj = JSON.parse(retrievedData);
    
    var relevantData = {};
    relevantData.ticker = retrievedDataObj.code;
    relevantData.frequency = retrievedDataObj.frequency;
    relevantData.closingPrice = retrievedDataObj.data;

    logger.debug('relevantData: %j', relevantData, {});

    logger.info('about to return data for ticker code: %s', retrievedDataObj.code);
    res.send(relevantData);
  });
});

module.exports = router;