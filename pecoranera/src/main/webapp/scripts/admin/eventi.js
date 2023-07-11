import { fillTable, editRow, removeRow, addRow } from "./tableManager.js";

$(document).ready(function(){
    let taglist = [];

    let retrieve = async () =>{
        return $.ajax({
            url: `${context}/admin/tag/list`,

            dataType: "json",

            success: function(data){
                $.each(data, function(key, tag){
                    taglist.push({id: tag.id, name: tag.name});
                })
            }
        })
    };

    let dataToTable = function(event){
        let chosenTag = [];
        
        event.tags.forEach(tag => {
            chosenTag.push(tag.id);
        });

        let options = taglist.map( tag =>{
            return `<option value="${tag.id}" ${chosenTag.includes(tag.id) ? "selected": ""}>${tag.name}</option>`;
        }).join("");
        
        return `<tr>
                    <td>
                        <form id="${event.id}" method="POST" action="${context}/admin/event/edit?id=${event.id}" enctype="multipart/form-data"></form>
                        ${event.id}
                    </td>
                    
                    <td>
                        <input type="text" name="name" value="${event.name}" form="${event.id}">
                    </td>
                    
                    <td>
                        <textarea name="description" placeholder="Insert Description" form="${event.id}" rows="4">${event.description}</textarea>
                    </td>
                    
                    <td>
                        <input type="text" step="0.1" name="price" form="${event.id}" value="${event.price}">
                    </td>
                    
                    <td>
                        <input type="date" name="date" form="${event.id}" value="${event.date}">
                    </td>
                    
                    <td>
                        <input type="number" name="maxTickets" form="${event.id}" value="${event.maxTickets}">
                        <input type="hidden" name="availableTickets" form="${event.id}" value="${event.maxTickets}">
                    </td>
                    
                    <td>
                        <input type="file" name="photo" form="${event.id}">
                    </td>
                    
                    <td>
                        <a href="${context}/admin/event-artist/list?id_event=${event.id}">artisti</a>
                    </td>	
                    
                    <td>
                        <select name="tags" multiple>
                            ${options}          
                        </select>
                    </td>

                    <td>
                        <input type="submit" value="Modifica"  form="${event.id}">
                    </td>
                    
                    <td>
                        <a class="delete" href="${context}/admin/event/delete?id=${event.id}">Elimina</a>
                    </td>
            </tr>`;
    };

    $("table").on("input", "input[name='maxTickets']", function(){
        let event = $(this).attr("form");
        let val = $(this).value();
        $(`input[name='availableTickets'][form='${event}']`).value(val);
    })

    addRow($("#add-form"))
    removeRow($("table"));
    editRow($("table"));
    retrieve().then(fillTable(dataToTable, "admin/event/list"));
});