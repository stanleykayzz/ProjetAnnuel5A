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
        //utils.ajaxRequest(Core.service.category.getListCategories(), null, null);
        Core.service.category.getListCategories().func({
            "0": {
                id: 0,
                name: "Chambre Simple",
                imagePath: "img/room6.jpg",
                costByNight: 60000,
                costEuro: 60,
                description: "Prevent bad actors from posting malicious or low-quality content that can permanently damage your business’ reputation and drive good users off your site."
            },
            "1": {
                id: 1,
                name: "Chambre Double",
                imagePath: "img/room7.jpg",
                costByNight: 100000,
                costEuro: 100,
                description: "Prevent bad actors from posting malicious or low-quality content that can permanently damage your business’ reputation and drive good users off your site."
            },
            "2": {
                id: 2,
                name: "Suite Junior",
                imagePath: "img/room8.jpg",
                costByNight: 150000,
                costEuro: 150,
                description: "Prevent bad actors from posting malicious or low-quality content that can permanently damage your business’ reputation and drive good users off your site."
            },
            "3": {
                id: 3,
                name: "Suite Exécutive",
                imagePath: "img/room9.jpg",
                costByNight: 200000,
                costEuro: 200,
                description: "Prevent bad actors from posting malicious or low-quality content that can permanently damage your business’ reputation and drive good users off your site."
            }
        });
    };

})();
