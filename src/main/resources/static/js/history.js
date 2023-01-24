$(document).ready(){

}
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
