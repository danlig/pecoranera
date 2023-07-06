import {moveToListAndSort, resetTags, ajaxTagRequest, selectedTags} from "./modules/eventTagManager.js";

$(document).ready(function(){
    ajaxTagRequest("#filter-choice div");
    moveToListAndSort("#selected-filter div", "#filter-choice div", false);
    moveToListAndSort("#filter-choice div", "#selected-filter div", true);
    resetTags($("input[type=reset]"), $("#selected-filter div"), $("#filter-choice div"))

    $("input[type=reset]").on("click", function(){

    });

    $("#save-changes").on("click", function(){
        $.ajax({
            url: 'TagController',

            type: "POST",

            dataType: "json",

            data:{
                ids: JSON.stringify(selectedTags)
            },

            success: function(){
                alert("Preferenze salvate");
            },

            error: function(){
                alert("Errore nel salvataggio preferenze");
            }
        });
    });
});