;(function () {
    "use strict";

    if (typeof Core === "undefined")
        throw "Core is not declared";

    Core.class.room = Core.class.room || {};

    Core.class.room.getListCategories = function () {
        //utils.ajaxRequest(Core.service.room.getListCategories(), null, null);
        Core.service.room.getListCategories().func({
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

    Core.class.room.search = function (json) {
        var paramRequest = "" +
            "date_start=" + json.dateStart +
            "&date_end=" + json.dateEnd +
            "&type=" + json.type;

        // utils.ajaxRequest(Core.service.room.search(), paramRequest, null);
        Core.service.room.search().func({
            room_1: {
                type: "0",
                description: "Prevent bad actors from posting malicious or low-quality content that can permanently damage your business’ reputation and drive good users off your site."
            },
            room_2: {
                type: "0",
                description: "Prevent bad actors from posting malicious or low-quality content that can permanently damage your business’ reputation and drive good users off your site."
            },
            room_3: {
                type: "0",
                description: "Prevent bad actors from posting malicious or low-quality content that can permanently damage your business’ reputation and drive good users off your site."
            },
            room_4: {
                type: "1",
                description: "Prevent bad actors from posting malicious or low-quality content that can permanently damage your business’ reputation and drive good users off your site."
            },
            room_5: {
                type: "1",
                description: "Prevent bad actors from posting malicious or low-quality content that can permanently damage your business’ reputation and drive good users off your site."
            },
            room_6: {
                type: "1",
                description: "Prevent bad actors from posting malicious or low-quality content that can permanently damage your business’ reputation and drive good users off your site."
            },
            room_7: {
                type: "0",
                description: "Prevent bad actors from posting malicious or low-quality content that can permanently damage your business’ reputation and drive good users off your site."
            },
            room_8: {
                type: "3",
                description: "Prevent bad actors from posting malicious or low-quality content that can permanently damage your business’ reputation and drive good users off your site."
            }
            ,
            room_9: {
                type: "2",
                description: "Prevent bad actors from posting malicious or low-quality content that can permanently damage your business’ reputation and drive good users off your site."
            }
        });
    };

    Core.class.room.book = function (json) {
        var paramRequest = "token=" + client.token;
        //utils.ajaxRequest(Core.service.room.booking(), paramRequest, json);
        Core.service.room.book().func("1500");
    };

    Core.class.room.cancelBook = function () {
        var paramRequest = "token=" + client.token;
        //utils.ajaxRequest(Core.service.room.cancelBook(), paramRequest, null);
        Core.service.room.cancelBook().func();
    };
})();
