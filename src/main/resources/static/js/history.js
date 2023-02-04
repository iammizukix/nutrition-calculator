 window.addEventListener('DOMContentLoaded', function() {
    if(categoryId){
        document.getElementById("category").options[categoryId].selected = true;
    }
})
$('.trigger').on('click', function(){
    const mealId = $(this).val();
    const url = '/api/meal/' + mealId;
    $.ajax({
          url: url,
          type: 'GET',
          dataType: 'json',
          success : function(meal){
            const categoryId = meal.categoryId;
            filter(categoryId);
            $('#category').val(categoryId);
            $('#food').val(meal.foodId);
            $('#quantity').val(meal.quantity);
            $('#date').val(meal.date);
            $('#time').val(meal.time);
            $('#mealId').val(mealId);
          }
      });
})

$('#search').on('click', function(){
    let url = '/history/search';
    let params = [];

    const date = $('#_date').val();
    if(date){
        params.push('date=' + date);
    }
    const categoryId = $('#_category').val();
    if(categoryId){
        params.push('categoryId=' + categoryId);
    }
    const keyword = $('#_keyword').val();
    if(keyword){
        params.push('keyword=' +  keyword);
    }
    if(params.length){
        url += '?' + params.join('&');
    }
    location.href = url;
})

function deleteMeal(mealId){
    location.href = '/history/delete?mealId=' + mealId;
}