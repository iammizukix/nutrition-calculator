$('#modalTriggerBtn').on('click', function(){ filter(0); });

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