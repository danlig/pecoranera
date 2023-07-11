import { fillTable, editRow, removeRow, addRow } from "./tableManager.js";

$(document).ready(function(){
    let dataToTable = function(artist){
        return `<tr>
                    <td>
                        <form id="${artist.id}" method="POST" class="edit" action="${context}/admin/artist/edit?id=${artist.id}"></form>
                        ${artist.id}
                    </td>
                    <td>
                        <input name="name" type="text" value="${artist.name }" form="${artist.id}">
                    </td>
                    <td>
                        <textarea name="description" placeholder="Insert Description" rows="4" form="${artist.id}">${artist.description}</textarea>
                    </td>
                    <td>
                        <input type="submit" value="Modifica" form="${artist.id}">
                    </td>
                    <td>
                        <a class="delete" href="${context}/admin/artist/delete?id=${artist.id}">Elimina</a>
                    </td>       
                </tr>`;
    };

    addRow($("#add-form"))
    removeRow($("table"));
    editRow($("table"));
    fillTable(dataToTable, "admin/artist/list");
});