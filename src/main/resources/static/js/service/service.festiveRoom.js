;(function () {
    "use strict";

    if (typeof Core === "undefined")
        throw "Core is not declared";

    Core.service.festiveRoom = Core.service.festiveRoom || {};

    Core.service.festiveRoom.getItems = function () {
        return {
            name: "getItems",
            method: "GET",
            url: "festiveRoom/getItems",
            func: function (json) {
                Core.views.festiveRoom.initView(json);
            },
            error: function (statusCode) {
            }
        }
    };

    Core.service.festiveRoom.book = function () {
        return {
            name: "book",
            method: "POST",
            url: "festiveRoom/book",
            func: function (price) {
                document.getElementById("search_container").style.display = "none";
                document.getElementById("include_book").style.display = "inline-block";
                Core.payment.paypal.generateButton(price, document.getElementById("button_paypal"));
                document.getElementById("label_price").textContent = price + " €";
            },
            error: function (statusCode) {
                document.getElementById("error_container").textContent = "La salle des fêtes n'est pas disponible durant cette période.";
            }
        }
    };

})();
