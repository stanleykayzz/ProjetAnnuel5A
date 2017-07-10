;(function () {
    "use strict";

    if (typeof Core === "undefined")
        throw "Core is not declared";

    Core.class.book.room = Core.class.book.room || {};

    Core.class.book.room.bookRoom = function (json) {
        var paramRequest = "token=" + client.token;
        //utils.ajaxRequest(Core.service.book.room.bookRoom(), paramRequest, json);
        Core.service.book.room.bookRoom().func("1500");
    };

    Core.class.book.room.cancelBookRoom = function () {
        var paramRequest = "token=" + client.token;
        //utils.ajaxRequest(Core.service.book.room.cancelBookRoom(), paramRequest, null);
        Core.service.book.room.cancelBookRoom().func();
    };

    Core.class.book.room.initListBookRoomCurrent = function () {
        var paramRequest = "token=" + client.token +"&id=" + client.id;
        //utils.ajaxRequest(Core.service.book.room.getCurrentListById(), paramRequest, null);
        Core.service.book.room.getCurrentListById().func();
    };

    Core.class.book.room.initListBookRoomHold = function (json) {
        var paramRequest = "token=" + client.token +"&id=" + client.id;
        //utils.ajaxRequest(Core.service.book.room.getHoldListById(), paramRequest, null);
        Core.service.book.room.getHoldListById();

    };
})();