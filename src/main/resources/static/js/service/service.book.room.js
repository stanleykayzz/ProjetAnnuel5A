;(function () {
    if(typeof Core === "undefined")
        throw "Core is not declared";

    Core.service.book.room = Core.service.book.room || {};

    Core.service.book.room.bookRoom = function () {
        return {
            name   : "bookRoom",
            method : "POST",
            url    : "",
            func : function (price) {
                if(price !== null && price !== undefined){
                    document.getElementById("label_price").textContent = price + " €";
                    Core.payment.paypal.generateButton(price, document.getElementById("button_paypal"));
                } else {
                    return null;
                }
            },
            error : function(statusCode){
            }
        };
    };

    Core.service.book.room.cancelBookRoom = function () {
        return {
            name   : "cancelBookRoom",
            method : "DELETE",
            url    : "",
            func : function () {
                views.includeContainer.switchView("chambre");
            },
            error : function(statusCode){
            }
        };
    };

    Core.service.book.room.getCurrentListById = function () {
        return {
            name   : "getListById",
            method : "GET",
            url    : "",
            func : function (json) {
                
            },
            error : function(statusCode){
            }
        };
    };

    Core.service.book.room.getHoldListById = function () {
        return {
            name   : "getHoldListById",
            method : "GET",
            url    : "",
            func : function (json) {

            },
            error : function(statusCode){
            }
        };
    };
})();