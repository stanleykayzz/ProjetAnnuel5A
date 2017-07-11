;(function () {
    if(typeof Core === "undefined")
        throw "Core is not declared";

    Core.service.book.restaurant = Core.service.book.restaurant || {};

    Core.service.book.restaurant.bookRestaurant = function () {
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

    Core.service.book.restaurant.getListBookById = function () {
        return {
            name: "bookRestaurant",
            method: "GET",
            url: "",
            func: function (json) {
                var headers = {
                    date : {
                        content: "Date"
                    },
                    type: {
                       content: "Plage horaire"
                    },
                    number:{
                        content:  "Nombre de personnes"
                    }
                };

                utils.template.createLiTemplate(headers, json, document.getElementById("book_restaurant_content"), "read");
            },
            error: function (statusCode) {

            }
        };
    };

})();