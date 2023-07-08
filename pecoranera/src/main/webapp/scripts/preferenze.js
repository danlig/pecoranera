import {moveToListAndSort, resetTags, ajaxTagRequest, selectedTags, tags} from "./modules/eventTagManager.js";

$(document).ready(function(){
    let getUserTags = async function(){
        
        await $.ajax({
            url: 'TagUserController',

            type: 'Post',

            success: function(data) {
                
                $.each(data, function(key, val) {
                    selectedTags.push(val.id);
                });

                /*$(".filter-tag").each(function() {
                    if(selectedTags.includes(parseInt($(this).attr("id")))){
                        $(this).remove();
                        $("#selected-filter div").append($(this));
                    }
                });*/
        
            },
        });

        $(".filter-tag").each(function() {
            if(selectedTags.includes(parseInt($(this).attr("id")))){
                $(this).remove();
                $("#selected-filter div").append($(this));
            }
        });
    }

    // getUserTags();
    ajaxTagRequest("#filter-choice div").then(getUserTags());
    moveToListAndSort("#selected-filter div", "#filter-choice div", false);
    moveToListAndSort("#filter-choice div", "#selected-filter div", true);
    resetTags($("input[type=reset]"), $("#selected-filter div"), $("#filter-choice div"))

    $("#save-changes").on("click", function(){
        $.ajax({
            url: 'TagSaveController',

            type: "POST",

            data:{
                ids: JSON.stringify(selectedTags)
            },

            success: function(){
                alert("Preferenze salvate");
            }
        });
    });
});