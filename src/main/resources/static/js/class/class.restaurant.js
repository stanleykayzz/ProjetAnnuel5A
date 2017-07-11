;(function () {
    "use strict";

    if (typeof Core === "undefined")
        throw "Core is not declared";

    Core.class.restaurant = Core.class.restaurant || {};

    Core.class.restaurant.create = function (json) {
        var paramRequest = "token=" + client.token;
        utils.ajaxRequest(Core.service.restaurant.create(), paramRequest, json);
    };

    Core.class.restaurant.udapte = function (json) {
        var paramRequest = "token=" + client.token;
        utils.ajaxRequest(Core.service.restaurant.udapte(), paramRequest, json);
    };

    Core.class.restaurant.delete = function (id) {
        var paramRequest = "token=" + client.token + "&id=" + id;
        utils.ajaxRequest(Core.service.restaurant.delete(), paramRequest, json);
    };

    Core.class.restaurant.initAdminViewListRestaurant = function () {
        var paramRequest = "token=" + client.token;
        //utils.ajaxRequest(Core.service.restaurant.initViewListRestaurant(), paramRequest, json);
        Core.service.restaurant.initAdminViewListRestaurant().func({
            table_1: {
                id: "1",
                nbClients: "8"
            },
            table_2: {
                id: "2",
                nbClients: "2"
            },
            table_3: {
                id: "3",
                nbClients: "6"
            },
            table_4: {
                id: "4",
                nbClients: "2"
            },
            table_5: {
                id: "5",
                nbClients: "4"
            },
            table_6: {
                id: "6",
                nbClients: "2"
            }
        });
    };

})();