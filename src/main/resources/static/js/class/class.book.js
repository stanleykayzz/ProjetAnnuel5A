;(function () {
    "use strict";

    if (typeof Core === "undefined")
        throw "Core is not declared";

    Core.class.book = Core.class.book || {};

    Core.class.book.bookRoom = function (json) {
        var paramRequest = "token=" + client.token;
        //utils.ajaxRequest(Core.service.book.bookRoom(), paramRequest, json);
        Core.service.book.bookRoom().func("1500");
    };

    Core.class.book.cancelBookRoom = function () {
        var paramRequest = "token=" + client.token;
        //utils.ajaxRequest(Core.service.book.cancelBookRoom(), paramRequest, null);
        Core.service.book.cancelBookRoom().func();
    };

    Core.class.book.bookRestaurant = function (json) {
        var paramRequest = "token=" + client.token + "&type=" + json.type + "&number=" + json.number;
        //utils.ajaxRequest(Core.service.book.bookRestaurant(), paramRequest, null);
        Core.service.book.bookRestaurant().func();
    };

    Core.class.book.bookFestiveRoom = function (paramJson, bodyJson) {
        var paramRequest = "token=" + client.token + "&dateStart=" + paramJson.dateStart + "&dateEnd" + paramJson.dateEnd;
        //utils.ajaxRequest(Core.service.festiveRoom.book(), paramRequest, bodyJson);
        Core.service.book.bookFestiveRoom().func("3000");
    };
})();
