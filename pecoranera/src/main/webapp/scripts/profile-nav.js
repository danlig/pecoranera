$(document).ready(function(){
    $("a[href='logout']").on("click", function(e){
        e.preventDefault();

        $.ajax({
            type: 'POST',
            url: 'LogoutController',

            success: function(){
                window.location.replace(`index.jsp`);
            },

            statusCode: {
                401: function(message){
                    alert(JSON.stringify(message));
                },
            }
        });
    });
});