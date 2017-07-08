;(function () {
    "use strict";

    if (typeof Core === "undefined")
        throw "Core is not declared";

    Core.class.festiveRoom = Core.class.festiveRoom || {};


    Core.class.festiveRoom.initView = function () {
        var paramRequest = "token=" + client.token;
        //utils.ajaxRequest(Core.service.festiveRoom.getItems(), paramRequest, null);
        Core.service.festiveRoom.getItems().func({
            "0": {
                id: "0",
                name: "table 4 personnes",
                quantity: "30"
            },
            "1": {
                id: "1",
                name: "cuillière",
                quantity: "200"
            },
            "2": {
                id: "2",
                name: "fourchette",
                quantity: "200"
            },
            "3": {
                id: "3",
                name: "couteau",
                quantity: "200"
            },
            "4": {
                id: "4",
                name: "assiette",
                quantity: "200"
            },
            "5": {
                id: "5",
                name: "table 6 personnes",
                quantity: "120"
            },
            "6": {
                id: "6",
                name: "table 8 personnes",
                quantity: "120"
            }
        });

    };

    Core.class.festiveRoom.search = function (paramJson, bodyJson) {
        var paramRequest = "token=" + client.token + "&dateStart=" + paramJson.dateStart + "&dateEnd" + paramJson.dateEnd;  
        //utils.ajaxRequest(Core.service.festiveRoom.book(), paramRequest, bodyJson);
        Core.service.festiveRoom.book().func("3000");
    };
})();