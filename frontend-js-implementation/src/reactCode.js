var React = require('react'); 
var $ = require('jquery');
var barChart = require('../src/barChart');

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
    var formattedData = JSON.stringify(this.state.dataToShow, null, 2);
    return (
      <div className="barChartRenderer">
        <div id="chartToBeRendered"></div> 
        <br>Hello, world! I am a BarChartRenderer managed by React!</br>
        <br><tt>{formattedData}</tt></br>
      </div>
    );
  }
});

React.render(
  <BarChartRenderer />,
  document.getElementById('reactD3BarChart')
);