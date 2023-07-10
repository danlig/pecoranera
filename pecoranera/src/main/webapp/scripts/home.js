$(document).ready(function() {
    let chiSiamo = $("#chisiamo .side-text").first();
    let images = $("#chisiamo .side-images");
    let isMobile = /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent);

    $.fn.isInViewport = function (){
        var elementTop = $(this).offset().top;
        var elementBottom = elementTop + $(this).outerHeight() -20;

        var viewportTop = $(window).scrollTop();
        var viewportBottom = viewportTop + $(window).height();

        return elementBottom > viewportTop && elementTop < viewportBottom;
    }

    $('body').bind('beforeunload',function(){
        window.scrollTo({ top: 0, behavior: 'smooth' });
    });

    $('a[href="#"]').click(function() {
        var offset = -200; // <-- change the value here

        $('html, body').animate({
            scrollTop: chiSiamo.offset().top + offset
        }, 500);
    });

    $(window).on('resize scroll', function() {
        if (chiSiamo.isInViewport()) {
            //console.log("VISIBILE");
            chiSiamo.addClass('insight');
            images.children("img").addClass('insight')
            
        } else {
            //console.log("NOT! VISIBILE");
            chiSiamo.removeClass('insight');
            images.children("img").removeClass('insight')
        }
    });

    $("#menu-choice a").on('mouseover', function () {
        if( !isMobile && $(window).width() > 600) {
            $(this).addClass('active-choice');
            $(this).siblings().addClass('inactive-choice');
        }
    });

    $("#menu-choice a").on('mouseleave', function(){
        if( !isMobile && $(window).width() > 600){
            $(this).removeClass('active-choice');
            $(this).siblings().removeClass('inactive-choice');
        }
    });


});