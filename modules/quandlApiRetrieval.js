var request = require('request');
var config = require('./config');
var logger = require('./logger');

var quandlApiRetrieval = {

	getData: function (data, callback) {

		console.log('data', data);

		var requestOptions = {
			method: 'GET',
			url: config.quandlUrl + data.tickerSymbol + '.json?',
			qs: data.queryParams
		};

		logger.debug('requestOptions: %j', requestOptions, {});

		request(requestOptions, function (quandlError, quandlResponse, quandlBody) {

			if(quandlError) {
				logger.error('quandlError: %j', quandlError, {});
				callback(quandlError);
			} else {
				callback(undefined, quandlBody);
			}
		});
	}

};

module.exports = quandlApiRetrieval;