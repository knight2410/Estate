
window.onload = function(){ 
   var getClassTextLeft = document.getElementsByClassName("text-left");
   for (let index = 0; index < getClassTextLeft.length; index++) {
       getClassTextLeft[index].onclick = function(){
          
        $.ajax({
            url: 'http://localhost:8080/estate-platform-web/api/admin/building',
            type: 'DELETE',
            contentType:'application/json',
            // data: JSON.stringify(data),
            success: function(res) {
                window.location.href = "<c:url value='/admin/building/list?message=delete_success'/>";
            },
            error: function(res) {
                console.log(res);
                window.location.href = "<c:url value='/admin/building/list?message=error_system'/>";
            }
        });
       }
   }
};

function checkButton(){
    alert("checkButton");
}
