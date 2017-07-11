;(function () {
    "use strict";

    if (typeof Core === "undefined")
        throw "Core is not declared";

    Core.views.admin = Core.views.admin || {};

    Core.views.admin.initView = function () {
        Core.class.book.room.initListBookRoomCurrent();
        Core.class.book.room.initListBookRoomHold();
        Core.class.book.restaurant.initListBookRestaurant();
        Core.class.book.festiveRoom.initListBookFestiveRoom();

        Core.class.category.initAdminListCategories();
        Core.class.room.initAdminListRoom();
        Core.class.restaurant.initAdminViewListRestaurant();
        Core.class.building.initAdminViewListBuilding();
        Core.class.services.initAdminViewListServices();
        Core.class.client.initAdminViewListClients();
        Core.class.article.initAdminViewListArticles();
        Core.class.iniAdminViewNewsLetter();

        var list_menu = document.getElementsByClassName("li_menu");

        for (var i = 0; i < list_menu.length; i++) {
            utils.template.manageListDisplay(list_menu[i].id);
        }
    };
})();