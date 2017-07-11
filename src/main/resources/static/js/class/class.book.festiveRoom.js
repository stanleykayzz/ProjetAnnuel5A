;(function () {
    "use strict";

    if (typeof Core === "undefined")
        throw "Core is not declared";

    Core.class.book.festiveRoom = Core.class.book.festiveRoom || {};

    Core.class.book.festiveRoom.bookFestiveRoom = function (paramJson, bodyJson) {
        var paramRequest = "token=" + client.token + "&dateStart=" + paramJson.dateStart + "&dateEnd" + paramJson.dateEnd;
        //utils.ajaxRequest(Core.service.book.festiveRoom.book(), paramRequest, bodyJson);
        Core.service.book.festiveRoom.bookFestiveRoom().func("3000");
    };

    Core.class.book.festiveRoom.initListBookFestiveRoom = function () {
        var paramRequest = "token=" + client.token;
        //utils.ajaxRequest(Core.service.book.festiveRoom.getListBookById(), paramRequest, bodyJson);
        Core.service.book.festiveRoom.getListBookById().func();
    };
})();