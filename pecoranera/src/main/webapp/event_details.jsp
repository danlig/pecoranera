<!DOCTYPE html>
<html lang="it">
<head>
    <%@ page contentType="text/html; charset=UTF-8" %>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pecoranera Jazz Art Bistrot</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/event_details.css">
    <link rel="icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon" />
    <script src="https://code.jquery.com/jquery-3.7.0.js" integrity="sha256-JlqSTELeR4TLqP0OG9dxM7yDPqX1ox/HfgiSLBj8+kM=" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js" integrity="sha256-xLD7nhI62fcsEZK2/v8LsBcb4lG7dgULkuXoXB/j91c=" crossorigin="anonymous"></script>
    <script type="module" src="${pageContext.request.contextPath}/scripts/event_details.js"></script>
</head>
<body>
    <div id="event">        
        <div>
            <div id="left-side">
                <img src="" alt="">

                <div id="artist-list">
                    <span class="artist">Lello Petrarca</span>
                    <span class="artist">Aldo Fucile</span>
                    <span class="artist">Vincenzo Faraldo</span>
                </div> 
            </div>

            <div id="right-side">
                <h2 class="event-header">Nome-Evento</h2>

                <form method="post" action="">
                    <div>
                        <div id="ticket-input">
                            <button id="subtract" class="ticket-mod">-</button>
                            <input type="number" name="ticket-number" id="ticket-number" min="1">
                            <button id="add" class="ticket-mod">+</button>
                        </div>
                        
                        <p id="ticket-price">€10.50</p>
                    </div>

                    <p id="available-tickets">Biglietti disponibili: <span>15</span></p>

                    <button type="submit">Acquista <span id="buy-number">1</span> <span id="plural-word">biglietto</span></button>
                </form>

                <div id="tag-list">
                    <span id="1" class="filter-tag">Blues</span>
                    <span id="2" class="filter-tag">Jazz Fusion</span>
                    <span id="3" class="filter-tag">Soul Jazz</span>
                </div>  
        </div>
        
        <p>
            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam ultrices aliquam faucibus. Etiam porttitor dapibus arcu sit amet euismod. Praesent tincidunt tempor gravida. Sed scelerisque massa nunc, vitae sagittis lacus hendrerit vitae. Proin ultrices neque eu lorem porta vehicula. Vestibulum pretium eleifend nunc, dapibus interdum augue. Mauris ligula dolor, volutpat eget bibendum commodo, posuere ut quam.

In vel enim lorem. Ut ac nunc eget justo cursus aliquam et eget tortor. Phasellus tristique odio ut tortor bibendum, in auctor lectus facilisis. Nulla hendrerit vehicula metus, vulputate convallis risus maximus at. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. In eleifend lectus in quam interdum, quis bibendum nisl bibendum. Nullam dui nunc, semper in neque in, sagittis posuere arcu. Maecenas sit amet consequat mi. Morbi posuere condimentum nunc. Sed non ultricies ipsum. Vivamus vitae hendrerit nisl. Sed a tortor facilisis, ornare neque et, hendrerit quam. Vivamus congue erat ut lorem pharetra, quis sagittis libero elementum.

Aenean vel sodales purus. Nunc pharetra accumsan eros nec congue. Duis at auctor lectus, eu porttitor libero. Nulla et nisl velit. In malesuada malesuada sagittis. Nulla vitae volutpat justo, sed viverra dui. Sed placerat non magna ut fringilla. Pellentesque eget tellus orci. Duis aliquam velit et egestas posuere. Vestibulum scelerisque convallis tempus. Fusce ut leo at eros vestibulum posuere. Maecenas justo libero, commodo non posuere sit amet, dictum in eros. Etiam ligula odio, efficitur et odio nec, scelerisque faucibus orci. Mauris interdum pulvinar mollis. Pellentesque vitae nisl aliquam, rutrum nulla eu, tempus nisi.

Aliquam cursus, neque in maximus venenatis, odio est imperdiet augue, rhoncus finibus purus nunc quis mi. Suspendisse congue sem at auctor maximus. In non tempor libero. Aliquam vel risus id nunc gravida mollis. In at ultrices tortor. Sed elementum massa rutrum blandit consectetur. Morbi at pretium felis, nec elementum diam. Nullam sed purus eros.

Proin convallis, tellus eu facilisis maximus, felis ipsum tincidunt risus, ut dapibus lacus lectus at lacus. Morbi at lorem nec est pretium maximus. Integer molestie, justo et aliquet consectetur, lorem nisi maximus arcu, eu volutpat elit lacus eget ante. Aenean varius lacus rutrum venenatis laoreet. Maecenas eget ullamcorper urna. Fusce ultrices posuere tellus in ultrices. Vestibulum vitae gravida mauris. Duis tincidunt eget ligula quis aliquet.
        </p>
    </div>
</body>
</html>