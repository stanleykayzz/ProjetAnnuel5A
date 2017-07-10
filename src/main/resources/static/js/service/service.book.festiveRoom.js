;(function () {
    if(typeof Core === "undefined")
        throw "Core is not declared";

    Core.service.book.festiveRoom = Core.service.book.festiveRoom || {};

    Core.service.book.festiveRoom.bookFestiveRoom = function () {
        return {
            name: "bookFestiveRoom",
            method: "POST",
            url: "",
            func: function (price) {
                document.getElementById("label_price").textContent = price;
                Core.payment.paypal.generateButton(price);
            },
            error: function (statusCode) {
                document.getElementById("valide_container").textContent = "";
                document.getElementById("error_container").textContent = "La salle des fêtes n'est pas disponible durant cette période."
            }
        };
    };

    Core.service.book.festiveRoom.getListBookById = function () {
        return {
            name: "getListBookById",
            method: "GET",
            url: "",
            func: function (json) {

            },
            error: function (statusCode) {

            }
        };
    };
   
})();