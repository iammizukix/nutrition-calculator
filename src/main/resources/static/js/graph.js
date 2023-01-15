 window.addEventListener('DOMContentLoaded', function() {
    if(message){ showToast(); }
    callCalorieApi();
})
function callCalorieApi(){
  $.ajax({
      url: '/api/calories',
      type: 'GET',
      dataType: 'json',
      success : function(json){
          displayDailyCalories(json[6]);
          displayGraph(json);
      }
  });
}
function displayGraph(data){
    const ctx = document.getElementById('graph');
    const config = {
       type: 'bar',
       data: {
         labels: ['6 days ago', '5 days ago', '4 days ago', '3 days ago', '2 days ago', 'yesterday', 'Today'],
         datasets: [{
           label: '# of Calories',
           data: data,
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
             text: 'Daily calories for the week'
           }
         }
       },
     };
     new Chart(ctx, config);
}

function displayDailyCalories(calories){
     $('#dailyCalories').append(calories);
}
