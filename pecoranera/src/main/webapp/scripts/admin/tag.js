import { fillTable } from "./tableManager.js";

$(document).ready(function(){
    let tagToTable = function(tag){
        return `<tr id="${tag.id}">
                    <form method="POST" action="${context}/admin/tag/edit?id=${tag.id}">
                        <td> 
                            ${tag.id}
                        </td>
                        <td>
                            <input type="text" name="name" value="${tag.name}">
                        </td>	
                        <td>
                            <input data-mod="${tag.id}" type="submit" value="Modifica">
                        </td>
                        <td>
                            <a class="delete" href="${context}/admin/tag/delete?id=${tag.id}">Elimina</a>
                        </td>
                    </form>
                <tr>`;
    };

    $("table").on("click", ".delete", function(e){
        e.preventDefault();

        $.ajax({
            url: $(this).attr("href"),

            success: () =>{
                //location.reload();
            },

            error: () =>{
                alert("error");
            }
        });
    });
    

    fillTable(tagToTable);
});