;(function () {
    "use strict";

    if (typeof Core === "undefined")
        throw "Core is not declared";

    Core.class.article = Core.class.article || {};

    Core.class.article.create = function (json) {
        var paramRequest = "token=" + client.token;
        utils.ajaxRequest(Core.service.restaurant.create(), paramRequest, json);
    };

    Core.class.article.udapte = function (json) {
        var paramRequest = "token=" + client.token;
        utils.ajaxRequest(Core.service.article.udapte(), paramRequest, json);
    };

    Core.class.article.delete = function (id) {
        var paramRequest = "token=" + client.token + "&id=" + id;
        utils.ajaxRequest(Core.service.article.delete(), paramRequest, null);
    };

    Core.class.article.getList = function () {
        utils.ajaxRequest(Core.service.article.getList(), paramRequest, null);
    };

    Core.class.article.initAdminViewListArticles = function () {
        utils.ajaxRequest(Core.service.article.initAdminViewListArticles(), null, null);
    };
})();
