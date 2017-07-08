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
                quantity: "30",
                unitPrice : "10"
            },
            "1": {
                id: "1",
                name: "cuillière",
                quantity: "200",
                unitPrice : "0"
            },
            "2": {
                id: "2",
                name: "fourchette",
                quantity: "200",
                unitPrice : "0"
            },
            "3": {
                id: "3",
                name: "couteau",
                quantity: "200",
                unitPrice : "0"
            },
            "4": {
                id: "4",
                name: "assiette",
                quantity: "200",
                unitPrice : "0"
            },
            "5": {
                id: "5",
                name: "table 6 personnes",
                quantity: "120",
                unitPrice : "15"
            },
            "6": {
                id: "6",
                name: "table 8 personnes",
                quantity: "120",
                unitPrice : "30"
            }
        });

    };

    Core.class.festiveRoom.book = function (paramJson, bodyJson) {
        var paramRequest = "token=" + client.token + "&dateStart=" + paramJson.dateStart + "&dateEnd" + paramJson.dateEnd;  
        //utils.ajaxRequest(Core.service.festiveRoom.book(), paramRequest, bodyJson);
        Core.service.festiveRoom.book().func("3000");
    };
})();