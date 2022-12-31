const ctx = document.getElementById('graph');

//const data = {
//  labels: labels,
//  datasets: [
//    {
//      label: 'Dataset 1',
//      data: Utils.numbers(NUMBER_CFG),
//      borderColor: Utils.CHART_COLORS.red,
//      backgroundColor: Utils.transparentize(Utils.CHART_COLORS.red, 0.5),
//    },
//    {
//      label: 'Dataset 2',
//      data: Utils.numbers(NUMBER_CFG),
//      borderColor: Utils.CHART_COLORS.blue,
//      backgroundColor: Utils.transparentize(Utils.CHART_COLORS.blue, 0.5),
//    }
//  ]
//};

const config = {
  type: 'bar',
  data: {
    labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
    datasets: [{
      label: '# of Votes',
      data: [12, 19, 3, 5, 2, 3],
      borderWidth: 1
    }]
  },
  options: {
    responsive: true,
    plugins: {
      legend: {
        position: 'top',
      },
      title: {
        display: true,
        text: 'Chart.js Bar Chart'
      }
    }
  },
};

new Chart(ctx, config);