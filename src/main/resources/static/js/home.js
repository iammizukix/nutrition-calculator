function filter(categoryId){
    let url = '/home/filter';
    if(categoryId != 0){
        url += '?categoryId=' + categoryId;
    }
    location.href = url;
}