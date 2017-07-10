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
                var headers = ["Date", "Nombre de personnes", "Type"];
                utils.template.create(headers, null, document.getElementById("book_room_active_content"));
            },
            error: function (statusCode) {

            }
        };
    };

})();