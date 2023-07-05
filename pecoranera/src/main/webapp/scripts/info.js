$(document).ready(function(){
    const isMobile = /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent);
    const imageSize = parseInt(window.getComputedStyle(document.body).getPropertyValue('--slide-size').replace("vw", ""));
    const images = [];

    // Calculate style difference for elements, the more they are distant, the more the style will change
    let styleCalculator = function(elemDistance, modifier){
        let value = 100 - (elemDistance * modifier);

        return value < modifier ? modifier : value;
    }
    
    //put slideshow images in array
    $("#slideshow>img").each(function(){
        images.push($(this));
    });

    function slideShowClick(){

        do{
            images.push(images.shift());
            
        }while(images[0].attr('id') != $(this).attr('id'));


        images.forEach((img, index) => {
            let  scale = styleCalculator(index, 5),
            brightness = styleCalculator(index, 20);

            img.css({"left": `${(imageSize / 4) * index}vw`,
                "z-index": 4 - index,
                "transform": `scale(${scale}%)`,
                "filter": `brightness(${brightness}%)`});
        });
    }

    function slideHover(){
        let elementPosition = images.findIndex(x => x.attr("id") == $(this).attr("id"));

        images.forEach((img, index) => {
            let distance = Math.abs(elementPosition - index),
            scale = styleCalculator(distance, 5),
            brightness = styleCalculator(distance, 20);

            img.css({"z-index": 4 - distance,
                    "transform": `scale(${scale}%)`,
                    "filter": `brightness(${brightness}%)`});
        });
    }

    function styleImages(){
        images.forEach((element, index) => {
            let  scale = styleCalculator(index, 5),
            brightness = styleCalculator(index, 20);

            element.css({"left": `${(imageSize / 4) * index}vw`,
                "z-index": 4 - index,
                "transform": `scale(${scale}%)`,
                "filter": `brightness(${brightness}%)`});
        });
    }

    //if screen is wide enough and not mobile then
    if(!isMobile &&  $(window).width() > 600){

        styleImages();

        // on click reorder the slideshow
        $("#slideshow>img").on("click", slideShowClick);

        // put hovered image in front and other behind
        $("#slideshow>img").on("mouseover", slideHover);
    }

    $(window).on('resize', function(){
        let width = $(this).width();
        if (!isMobile && width > 600){ 
            styleImages();

            // on click reorder the slideshow
            $("#slideshow>img").on("click", slideShowClick);

            // put hovered image in front and other behind
            $("#slideshow>img").on("mouseover", slideHover);
        }

        else{//remove listeners if mobile or not wide
            images.forEach(img => {
                img.removeAttr('style');
            });

            $("#slideshow>img").off("click", slideShowClick);

            // put hovered image in front and other behind
            $("#slideshow>img").off("mouseover", slideHover);
        }
    });

    

});