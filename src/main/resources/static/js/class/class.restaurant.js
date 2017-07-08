;(function () {
    "use strict";

    if (typeof Core === "undefined")
        throw "Core is not declared";

    Core.class.restaurant = Core.class.restaurant || {};

    Core.class.restaurant.booking = function (json) {
        var paramRequest = "token=" + client.token + "&type=" + json.type + "&number=" + json.number;
        //utils.ajaxRequest(Core.service.restaurant.book(), paramRequest, null);
        Core.service.restaurant.book().func();
    };

})();