function filter(categoryId){
    var url = '/api/food';
    if(categoryId != 0){
        url += '/' + categoryId;
    }
    location.href = url;
//            var food = {
//                [
//                {
//                "id": "1", /*[[${}]]*/
//                "category":
//                    {
//                    "id": "1",
//                    "name": "肉",
//                    }
//                "name": "牛肉"
//                },...
//                ]
//            };
    var food = [];
    $.ajax({
        type: 'GET',
        url: 'api/food', // must be fixing
        dataType: 'json'
        }).then(
            function(json){
                for(var i = 0; i < json.length; i++){
                    food.push({
                        'id': json[i].id,
                        'category': json[i].category,
                        'name': json[i].name
                    });
                }
            }
        );
}

// Get filtered food list from json
//$(function(){
//    var food = [];
//    $.ajax({
//        type: 'GET',
//        url: 'api/food', // must be fixing
//        dataType: 'json'
//        }).then(
//            function(json){
//                for(var i = 0; i < json.length; i++){
//                    food.push({
//                        'id': json[i].id,
//                        'category': json[i].category,
//                        'name': json[i].name
//                    });
//                }
//            }
//        );
//});
