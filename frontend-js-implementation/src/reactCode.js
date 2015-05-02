// tutorial1.js
var BarChartRenderer = React.createClass({

	componentWillMount: function() {

    var jsonFilePath = 'data/backendApiData.json';
    // TODO jQuery / AJAX to load the content
  },
  render: function() {
    return (
      <div className="barChartRenderer">
        Hello, world! I am a BarChartRenderer managed by React!
      </div>
    );
  }
});
React.render(
  <BarChartRenderer />,
  document.getElementById('reactD3BarChart')
);