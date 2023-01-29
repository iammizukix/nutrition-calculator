 window.addEventListener('DOMContentLoaded', function() {
    if(categoryId){
        document.getElementById("category").options[categoryId].selected = true;
    }
})
$('.trigger').on('click', function(){
    const url = '/api/meal/' + $(this).val();
    $.ajax({
          url: url,
          type: 'GET',
          dataType: 'json',
          success : function(meal){
            $('#_category').val(meal.categoryId) = true;
            $('#_food').val(meal.foodName);
            $('#_quantity').val(meal.quantity);
            $('#_date').val(meal.date);
            $('#_time').val(meal.time);
          }
      });
})

$('#search').on('click', function(){
    let url = '/history/search';
    let params = [];

    const date = $('#date').val();
    if(date){
        params.push('date=' + date);
    }
    const categoryId = $('#category').val();
    if(categoryId){
        params.push('categoryId=' + categoryId);
    }
    const keyword = $('#keyword').val();
    if(keyword){
        params.push('keyword=' +  keyword);
    }
    if(params.length){
        url += '?' + params.join('&');
    }
    location.href = url;
})