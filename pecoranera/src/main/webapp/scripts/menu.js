$(document).ready(function(){

    let productChoice = window.location.hash.substring(1) || "1";
    let loadedProducts = {};
    let productTypes = {};
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

    function loadProductTypes(){
        $.ajax({
            url: "menu",

            dataType: 'json',

            success: function(data) {    
                 
                $.each(data, function(key, val){ 
                    console.log(val);

                    productTypes[val.id] = val.name;

                    $("#category-select").append(`<option value="${val.id}">${val.name}</option>`)
                    $("#product-types-link").append(`<a href="#${val.id}" class="category-link">${val.name}</a>`);
                });
                
            },

            error: function(){
                alert("Errore nel caricamento dei tipi di prodotti")
            }
        });

    }

    function loadProducts(choice, isFirstTime){

        if(choice == previousRef)
            return;

        if(!isFirstTime){
            loadedProducts[previousRef] = $("#product-section>div").children().detach();
        }

        if(loadedProducts.hasOwnProperty(choice)){
            $("#product-section>div").empty();
            $("#product-section>div").append(loadedProducts[choice]);
            previousRef = choice;
        }

        else {
            $.ajax({
                url: `menu/product`,

                data:{
                    type: productChoice
                },

                dataType: 'json',

                success: function(data) {
                    
                    
                    $.each(data, function(key, val){ 
                        $("#product-section>div").append(productToHtml(key, val));
                    });
                    
                },
                
               statusCode: {
                  404: function() {
                    alert('Errore, prodotti non trovati');
                  }
                }
             }).done(function(){
                if(isFirstTime){
                    previousRef = productChoice;
                } else {
                    previousRef = choice;
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
    $(window).on("load", loadProductTypes());
    $(window).on("load", loadProducts(productChoice, true));
    
});