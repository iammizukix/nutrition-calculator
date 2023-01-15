$('#modalTriggerBtn').on('click', function(){ filter(0); });

function filter(categoryId){
    var url = '/api/food';
    if(categoryId != 0){
        url += '/' + categoryId;
    }
    clear('#food');
    $.ajax({
        url: url,
        type: 'GET',
        dataType: 'json',
        success : function(food){ appendFood(food); },
        error : function(){ appendError(); }
    });
}

function appendFood(json) {
    for(var i = 0; i < json.length; i++){
        $('#food').append('<option value="' + json[i].id + '">' + json[i].name + '</option>');
    }
}

function appendError(){
    $('#food').append('<option selected>Connection failed.try again.</option>');
}

function clear(element){
    $(element).text('');
}

// toast function
var toastElList = [].slice.call(document.querySelectorAll(".toast"));
var toastList = toastElList.map(function (toastEl) {
    return new bootstrap.Toast(toastEl, {
        // Option
        delay: 10000,
    });
});
function showToast(){
    toastList[0].show();
}