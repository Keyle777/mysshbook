function addCart(bookId){
    location.replace='http://localhost:8080/mysshbook/cart/addCart?id='+bookId;
}
function paging(currentPage){
    window.location.href='book.do?operate=paging&currentPage='+currentPage;
}
