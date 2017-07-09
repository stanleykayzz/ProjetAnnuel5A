;(function () {
    "use strict";

    if (typeof Core === "undefined")
        throw "Core is not declared";

    Core.class.newsLetter = Core.class.newsLetter || {};

    Core.class.newsLetter.send = function (json) {
        var paramRequest = "token=" + client.token;
        utils.ajax(Core.service.newsLetter.send(), paramRequest, json)
    };
})();
