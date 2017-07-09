;(function(undefined) {
    "use strict";

    if(typeof Core === "undefined")
        throw "Core is not declared";

    Core.service.book = Core.service.book || {};

    Core.service.book.bookRoom = function () {
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

    Core.service.book.cancelBookRoom = function () {
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

    Core.service.book.bookRestaurant = function () {
        return {
            name: "bookRestaurant",
            method: "POST",
            url: "",
            func: function () {
                document.getElementById("error_container").textContent = "";
                document.getElementById("valide_container").textContent = "Réservation effectuée"
            },
            error: function (statusCode) {
                document.getElementById("valide_container").textContent = "";
                document.getElementById("error_container").textContent = "La réservation n'a pas été effectuée."
            }
        };
    };

    Core.service.book.bookFestiveRoom = function () {
        return {
            name: "bookFestiveRoom",
            method: "POST",
            url: "",
            func: function () {
            },
            error: function (statusCode) {
            }
        };
    };
})();