;(function(undefined) {
    "use strict";

    if(typeof Core === "undefined")
        throw "Core is not declared";

    Core.service.contact = Core.service.contact || {};

    Core.service.contact.send = function () {
        return {
            name: "send",
            method: "POST",
            url: "/contact",
            func: function () {

            },
            error: function (statusCode) {
            }
        }
    };
})();