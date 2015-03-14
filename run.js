
var quandlApiRetrieval = require('./modules/quandlApiRetrieval');

var getAppleData = function () {

	// AAPL.json?trim_start=2015-01-01&trim_end=2015-03-14&column=4
	var dataDetails = {};

	dataDetails.tickerSymbol = 'AAPL';

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
	});
};

console.log('Here we go...');
getAppleData();