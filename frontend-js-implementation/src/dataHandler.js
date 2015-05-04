var $ = require('jquery');

var dataHandler = {
  fetchData: function(callback) {
    $.getJSON("http://localhost:8080/ticker/AAPL", function (dataFromRemoteHost) {
      // TODO set the "Access-Control-Allow-Origin" header in ALL the backends for CORS compatibility
      if (!dataFromRemoteHost) {
        $.getJSON("data/backendApiData.json", function (dataFromFileSystem) {
          console.log(new Date().getMilliseconds(), "Using data from the file system")
          callback(dataFromFileSystem);
        });
      } else {
        console.log(new Date().getMilliseconds(), "Using data from the remote host")
        callback(dataFromRemoteHost);
      }
    });
  }
}

module.exports = dataHandler;