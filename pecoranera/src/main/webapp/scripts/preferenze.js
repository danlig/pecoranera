import {moveToListAndSort, resetTags, ajaxTagRequest} from "./modules/eventTagManager.js";

$(document).ready(function(){
    ajaxTagRequest("#filter-choice div");
    moveToListAndSort("#selected-filter div", "#filter-choice div", false);
    moveToListAndSort("#filter-choice div", "#selected-filter div", true);
    resetTags($("input[type=reset]"), $("#selected-filter div"), $("#filter-choice div"))

    $("input[type=reset]").on("click", function(){
        
    })
});