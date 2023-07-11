let fillTable = function(_formatter){
    $.ajax({
        url: `${context}/TagRetrieveController`,

        dataType: "json",

        success: function(data){
            $.each(data, function(key, elem){
                $("table").append(_formatter(elem));
            })
        }
    });
};

export {fillTable};