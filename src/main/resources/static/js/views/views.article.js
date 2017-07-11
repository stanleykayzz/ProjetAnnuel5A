/**
 * Created by maxime.
 *
 * version 1.0.0
 */
;(function () {
    "use strict";

    if (typeof Core === "undefined")
        throw "Core is not declared";

    Core.views.article = Core.views.article || {};

    /**
     * Send a request to display articles in the article View
     */
    Core.views.article.initView = function () {
        var paramRequest = "token=" + client.token;
        utils.ajaxRequest(Core.service.article.getList(), paramRequest, null);
    };
})();