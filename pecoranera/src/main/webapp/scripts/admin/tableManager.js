let fillTable = function(_formatter, controller){
    $.ajax({
        url: `${context}/${controller}`,

        dataType: "json",

        success: function(data){
            $.each(data, function(key, elem){
                $("table").append(_formatter(elem));
            })
        }
    });
};

let editRow = function(elem){
    $(elem).on("submit", ".edit", function(e){
        e.preventDefault();

        $.ajax({
            url: $(this).attr("action"),

            data : $(this).serialize(),

            statusCode:{

                200: () =>{
                    location.reload();
                },

                401: () =>{
                    alert("error");
                }

            }

        });
    });
}

let removeRow = function(elem){
    $(elem).on("click", ".delete", function(e){
        e.preventDefault();

        $.ajax({
            url: $(this).attr("href"),

            success: () =>{
                location.reload();
            },

            error: () =>{
                alert("error");
            }
        });
    });
}

let addRow = function(elem){
    $(elem).on("submit", function(e){
        e.preventDefault();

        $.ajax({
            url: $(this).attr("action"),

            data: $(this).serialize(),

            statusCode:{

                200: () =>{
                    location.reload();
                },

                401: () =>{
                    alert("error");
                }

            }
        });
    })
}

export {fillTable, editRow, removeRow, addRow};