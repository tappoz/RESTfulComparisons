var request = require('request');
var config = require('./config');
var path = require('path');
var fs = require('fs');
var logger = require('./logger');

var quandlApiRetrieval = {

  getData: function (data, callback) {

    var requestOptions = {
      method: 'GET',
      url: config.quandlUrl + data.tickerSymbol + '.json',
      qs: data.queryParams,
      timeout: 6000 // usually Quandl response time is around 4500 ms for this hardcoded query
    };

    logger.debug('requestOptions: %j', requestOptions, {});

    request(requestOptions, function (quandlError, quandlResponse, quandlBody) {

      if(quandlError) {
        
        logger.error('quandlError: %j', quandlError, {});
        // callback(quandlError);
        // rather than calling the callback with an error
        // we may want to provide static data loaded from a file
        var filePathName = path.join(__dirname, '../data/sampleQuandlTicker.json');
        logger.debug('About to read the file at the path: %s', filePathName);

        fs.readFile(filePathName, function (err, dataFromFile) {
          if (err) {
            logger.error('we were not able to read the JSON: %s', err);
            throw err;
          } else {
            
            var staticData = JSON.parse(dataFromFile);
            staticData.warning = 'this is static data loaded from the file system';

            logger.warn('loading static data from the file system: %j', staticData, {});
            callback(undefined, staticData);
          }
        });
      
      } else {
        callback(undefined, JSON.parse(quandlBody));
      }
    });
  }
};

module.exports = quandlApiRetrieval;