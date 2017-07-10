;(function () {
    "use strict";

    if (typeof Core === "undefined")
        throw "Core is not declared";

    Core.views.clientListBook = Core.views.clientListBook || {};

    Core.views.clientListBook.initView = function () {
        var list_menu = document.getElementsByClassName("li_menu");

        for (var i = 0; i < list_menu.length; i++) {
            utils.template.manageListDisplay(list_menu[i].id);
        }
    };

    Core.views.clientListBook.initListBookRoomCurrent = function (json) {
        Core.class.book.room.initListBookRoomCurrent();
    };

    Core.views.clientListBook.initListBookRoomHold = function (json) {
        Core.class.book.room.initListBookRoomHold();
    };

    Core.views.clientListBook.initListBookRestaurant = function (json) {
        Core.class.book.room.initListBookRestaurant();
    };

    Core.views.clientListBook.initListBookFestiveRoom = function (json) {
        Core.class.book.room.initListBookFestiveRoom();
    };

})();