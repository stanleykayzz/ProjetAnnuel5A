;(function () {
    "use strict";

    if (typeof Core === "undefined")
        throw "Core is not declared";

    Core.class.contact = Core.class.contact || {};

    Core.class.contact.send = function (json) {
        utils.ajaxRequest(Core.service.contact.send(), null, json);
    };
})();
