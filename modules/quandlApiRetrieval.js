var request = require('request');
var config = require('./config');

var quandlApiRetrieval = {

	getData: function (data, callback) {

		console.log('data', data);

		var requestOptions = {
			method: 'GET',
			url: config.quandlUrl + data.tickerSymbol + '.json?',
			qs: data.queryParams
		};

		console.log('requestOptions', requestOptions);

		request(requestOptions, function (quandlError, quandlResponse, quandlBody) {

			if(quandlError) {
				console.log('quandlError:', quandlError);
				callback(quandlError);
			} else {
				callback(undefined, quandlBody);
			}
		});
	}

};

module.exports = quandlApiRetrieval;