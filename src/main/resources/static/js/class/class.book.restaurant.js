;(function () {
    "use strict";

    if (typeof Core === "undefined")
        throw "Core is not declared";

    Core.class.book.restaurant = Core.class.book.restaurant || {};

    Core.class.book.restaurant.bookRestaurant = function (json) {
        var paramRequest = "token=" + client.token + "&type=" + json.type + "&number=" + json.number;
        //utils.ajaxRequest(Core.service.book.restaurant.bookRestaurant(), paramRequest, null);
    };

    Core.class.book.restaurant.initListBookRestaurant = function () {
        var paramRequest = "token=" + client.token;
        //utils.ajaxRequest(Core.service.book.restaurant.getListBookById(), paramRequest, null);
    };
})();