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
    }.bind(this));
  },
  render: function() {
    console.log(new Date().getMilliseconds(), "The dataToShow we found is:", this.state.dataToShow);
    var formattedData = JSON.stringify(this.state.dataToShow, null, 2);
    return (
      <div className="barChartRenderer">
        Hello, world! I am a BarChartRenderer managed by React! 
        <tt>{formattedData}</tt>
      </div>
    );
  }
});

React.render(
  <BarChartRenderer />,
  document.getElementById('reactD3BarChart')
);