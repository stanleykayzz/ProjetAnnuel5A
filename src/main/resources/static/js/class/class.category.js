;(function () {
    "use strict";

    if (typeof Core === "undefined")
        throw "Core is not declared";

    Core.class.category = Core.class.category || {};

    Core.class.category.create = function () {
        var paramRequest = "token" + client.token;
        utils.ajaxRequest(Core.service.category.create(), paramRequest, null);
    };

    Core.class.category.delete = function (id) {
        var paramRequest = "token" + client.token + "&id=" + id;
        utils.ajaxRequest(Core.service.category.delete(), paramRequest, null);
    };

    Core.class.category.getListCategories = function () {
        utils.ajaxRequest(Core.service.category.getListCategories(), null, null);
    };

    Core.class.category.initAdminListCategories = function () {
        utils.ajaxRequest(Core.service.category.initViewListCategories(), null, null);
    };

})();
