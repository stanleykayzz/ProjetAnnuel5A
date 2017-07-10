;(function () {
    "use strict";

    if (typeof Core === "undefined")
        throw "Core is not declared";

    Core.class.book.restaurant = Core.class.book.restaurant || {};

    Core.class.book.restaurant.bookRestaurant = function (json) {
        var paramRequest = "token=" + client.token + "&type=" + json.type + "&number=" + json.number;
        //utils.ajaxRequest(Core.service.book.restaurant.bookRestaurant(), paramRequest, null);
        Core.service.book.restaurant.bookRestaurant().func();
    };

    Core.class.book.restaurant.initListBookRestaurant = function (json) {
        var paramRequest = "token=" + client.token +"&id=" + client.id;
        utils.ajaxRequest(Core.service.book.restaurant.getListBookById(), paramRequest, null);
    };
})();