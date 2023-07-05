$(document).ready(function(){

    let productChoice = window.location.hash.substring(1) || "birre";
    let loadedProducts = {};
    let previousRef;
   
    $(`a[href="#${productChoice}"]`).addClass("active");
    $(`option[value="${productChoice}"]`).attr('selected', 'selected');

    $(window).scroll(function() {
        if ($(document).scrollTop() > 1) {
            $('#scroll-up').fadeIn(500);
        }
        else {
            $('#scroll-up').fadeOut(250);
        }
    });

    function productToHtml(key, val) {
        return `<div id="${key}" class="product">
                    <div>
                        <h2>${key}</h2>

                        <p>${val.description}</p>
                    </div>

                    <span>&euro; ${val.price.toFixed(2)}</span>
                </div>`;
    }

    function loadProducts(jsonFile, isFirstTime){

        if(jsonFile == previousRef)
            return;

        if(!isFirstTime){
            loadedProducts[previousRef] = $("#product-section>div").children().detach();
        }

        if(loadedProducts.hasOwnProperty(jsonFile)){
            $("#product-section>div").empty();
            $("#product-section>div").append(loadedProducts[jsonFile]);
            previousRef = jsonFile;
        }

        else {
            $.ajax({
                url: `assets/${jsonFile}.json`,
                dataType: 'json',
                success: function(data) {
                    
                    
                    $.each(data, function(key, val){ 
                        $("#product-section>div").append(productToHtml(key, val));
                    });
                    
                },
                
               statusCode: {
                  404: function() {
                    alert('Errore, json file non trovato');
                  }
                }
             }).done(function(){
                if(isFirstTime){
                    previousRef = productChoice;
                } else {
                    previousRef = jsonFile;
                }
             });
        }
    }
    
    //click dei link categoria consegue un caricamento del prodotto del tipo scelto
    $(".category-link").on("click", function(){
        $(this).addClass("active");
        $(this).siblings().removeClass("active");

        let href = $(this).attr('href').replace("#", "");

        //modifichiamo anche selezione mobile
        $(`option[value="${href}"]`).attr("selected", "selected");
        $(`option[value="${href}"]`).siblings().removeAttr("selected");

        loadProducts(href, false);
    });

    $("#category-select").on("change", function(){
        //aggiorniamo anche selezione desktop
        $(`.category-link[href="#${this.value}"]`).addClass("active");
        $(`.category-link[href="#${this.value}"]`).siblings().removeClass("active");

        loadProducts(this.value, false);
    });

    $("#scroll-up").on("click", function(){
        window.scrollTo({ top: 0, behavior: 'smooth' });
    });

    //load products after loading 
    $(window).on("load", loadProducts(productChoice, true));

});