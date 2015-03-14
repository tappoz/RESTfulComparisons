var express = require('express');
var quandlApiRetrieval = require('../modules/quandlApiRetrieval');


var router = express.Router();

router.get('/:tickerCode', function(req, res) {
  
  var dataDetails = {};

  dataDetails.tickerSymbol = req.params.tickerCode;

  dataDetails.queryParams = {};
  dataDetails.queryParams.trim_start = '2015-01-01';
  dataDetails.queryParams.trim_end = '2015-03-14';
  dataDetails.queryParams.column = '4'; // closing price

  quandlApiRetrieval.getData(dataDetails, function(errorData, retrievedData) {

    // console.log('errorData', errorData);
    // console.log('retrievedData', retrievedData);

    console.log('------------------------------------------------');
    var retrievedDataObj = JSON.parse(retrievedData);
    console.log('retrievedDataObj.data', retrievedDataObj.data);

    var relevantData = {};
    relevantData.ticker = retrievedDataObj.code;
    relevantData.frequency = retrievedDataObj.frequency;
    relevantData.closingPrice = retrievedDataObj.data;

    console.log('relevantData:', relevantData);

    res.send(relevantData);
  });
});

module.exports = router;