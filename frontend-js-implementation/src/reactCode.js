var React = require('react'); 
var $ = require('jquery');
var barChart = require('../src/barChart');

var StockPricesList = React.createClass({
  render: function() {
    if (this.props.data) {
      console.log(new Date().getMilliseconds(), "Stock prices nodes from 'props':", this.props.data);
      var stockPricesNodes = this.props.data.map(function (stockPriceData) {
        return (
          <div id="stockPricesNode">
            <tt>On day {stockPriceData.date} the closing price was: {stockPriceData.closingPrice}</tt>
          </div>
        );
      });
    }
    return (
      <div className="stockPricesList">
        {stockPricesNodes}
      </div>
    );
  }
});

var BarChartRenderer = React.createClass({
  getInitialState: function() {
    var now = new Date();
    console.log('Started at:', now.getMilliseconds());
    return {started: now}; // this.state.started
  },
  componentWillMount: function() {
    $.getJSON("data/backendApiData.json", function (dataFromFileSystem) {
      console.log(new Date().getMilliseconds(), "This data has been loaded:", dataFromFileSystem);
      this.setState({dataToShow: dataFromFileSystem});
      this.renderChart();
    }.bind(this));
  },
  renderChart: function() {
    console.log(new Date().getMilliseconds(), "About to show this data:", this.state.dataToShow);
    barChart.getInstance(this.state.dataToShow, '#chartToBeRendered');
  },
  render: function() {
    console.log(new Date().getMilliseconds(), "The dataToShow we found is:", this.state.dataToShow);
    // var formattedData = JSON.stringify(this.state.dataToShow, null, 2);
    if (this.state.dataToShow) {
      console.log(new Date().getMilliseconds(), "Data to show!", this.state.dataToShow);
      return (
        <div className="barChartRenderer">
          <div id="chartToBeRendered"></div> 
          <StockPricesList data={this.state.dataToShow.dailyStockData} />
        </div>
      );
    } else {
      console.log(new Date().getMilliseconds(), "No data to show! :(");
      return (
        <div className="barChartRenderer">
          <div id="chartToBeRendered"></div>
        </div>
      );
    }
  }
});

React.render(
  <BarChartRenderer />,
  document.getElementById('reactD3BarChart')
);