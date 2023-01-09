 window.addEventListener('DOMContentLoaded', function() {
      $.ajax({
          url: '/api/calories',
          type: 'GET',
          dataType: 'json',
          success : function(json){ displayGraph(json); }
      });
})

function displayGraph(data){
    const ctx = document.getElementById('graph');
    const config = {
       type: 'bar',
       data: {
         labels: ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'],
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
             text: 'Daily Calories'
           }
         }
       },
     };
     new Chart(ctx, config);
}
function parseToArray(json){
    var array = [];
    for(var i = 0; i < json.length; i++){
        array.push(json[i]);
    }
    return array;
}
