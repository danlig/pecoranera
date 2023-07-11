import { fillTable, editRow, removeRow, addRow } from "./tableManager.js";

$(document).ready(function(){
    let dataToTable = function(tag){
        return `<tr>
                    <td>
                        <form id="${tag.id}" class="edit" method="POST" action="${context}/admin/tag/edit?id=${tag.id}"></form>
                        ${tag.id}
                    </td>
                    <td>
                        <input type="text" name="name" value="${tag.name}" form="${tag.id}" required>
                    </td>	
                    <td>
                        <input type="submit" value="Modifica" form="${tag.id}">
                    </td>
                    <td>
                        <a class="delete" href="${context}/admin/tag/delete?id=${tag.id}">Elimina</a>
                    </td>
                <tr>`;
    };

    addRow($("#add-form"))
    removeRow($("table"));
    editRow($("table"));
    fillTable(dataToTable,`admin/tag/list`);
});