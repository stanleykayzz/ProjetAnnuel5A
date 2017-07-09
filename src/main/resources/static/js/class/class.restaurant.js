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

    Core.class.restaurant.getList = function () {
        var paramRequest = "token=" + client.token;
        utils.ajaxRequest(Core.service.restaurant.getList(), paramRequest, json);
    };

})();