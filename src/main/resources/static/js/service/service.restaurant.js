;(function () {
    "use strict";

    if (typeof Core === "undefined")
        throw "Core is not declared";

    Core.service.restaurant = Core.service.restaurant || {};

    Core.service.restaurant.book = function () {
        return {
            name: "restaurantBook",
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
    }
})();