;(function () {
    "use strict";

    if (typeof Core === "undefined")
        throw "Core is not declared";

    Core.class.book.room = Core.class.book.room || {};

    Core.class.book.room.bookRoom = function (json) {
        var paramRequest = "token=" + client.token;
        //utils.ajaxRequest(Core.service.book.room.bookRoom(), paramRequest, json);
        Core.service.book.room.bookRoom().func("1500");
    };

    Core.class.book.room.cancelBookRoom = function () {
        var paramRequest = "token=" + client.token;
        //utils.ajaxRequest(Core.service.book.room.cancelBookRoom(), paramRequest, null);
        Core.service.book.room.cancelBookRoom().func();
    };

    Core.class.book.room.initListBookRoomCurrent = function () {
        var paramRequest = "token=" + client.token;
        //utils.ajaxRequest(Core.service.book.room.getCurrentList(), paramRequest, null);
        /*Core.service.book.room.getCurrentList().func({
            "0":{
                number: {
                    content: "1"
                },
                lastName : {
                    content: "Mollard"
                },
                firstName : {
                    content: "Maxime"
                },
                date_start : {
                    content: "14/05/2017"
                },
                date_end: {
                    content: "21/05/2017"
                },
                type:{
                    content:  "Chambre double"
                },
                price: {
                    content: "2000"
                }
            },
            "1":{
                number: {
                    content: "1"
                },
                lastName : {
                    content: "Mollard"
                },
                firstName : {
                    content: "Maxime"
                },
                date_start : {
                    content: "14/05/2017"
                },
                date_end: {
                    content: "21/05/2017"
                },
                type:{
                    content:  "Chambre double"
                },
                price: {
                    content: "2000"
                }
            },
            "2":{
                number: {
                    content: "1"
                },
                lastName : {
                    content: "Mollard"
                },
                firstName : {
                    content: "Maxime"
                },
                date_start : {
                    content: "14/05/2017"
                },
                date_end: {
                    content: "21/05/2017"
                },
                type:{
                    content:  "Chambre double"
                },
                price: {
                    content: "2000"
                }
            },
            "3":{
                number: {
                    content: "1"
                },
                lastName : {
                    content: "Mollard"
                },
                firstName : {
                    content: "Maxime"
                },
                date_start : {
                    content: "14/05/2017"
                },
                date_end: {
                    content: "21/05/2017"
                },
                type:{
                    content:  "Chambre double"
                },
                price: {
                    content: "2000"
                }
            }
        });*/
    };

    Core.class.book.room.initListBookRoomHold = function () {
        var paramRequest = "token=" + client.token;
        //utils.ajaxRequest(Core.service.book.room.getHoldList(), paramRequest, null);
        /*Core.service.book.room.getHoldList().func({
            "0":{
                number: {
                    content: "1"
                },
                lastName : {
                    content: "Mollard"
                },
                firstName : {
                    content: "Maxime"
                },
                date_start : {
                    content: "14/05/2017"
                },
                date_end: {
                    content: "21/05/2017"
                },
                type:{
                    content:  "Chambre double"
                },
                price: {
                    content: "2000"
                }
            },
            "1":{
                number: {
                    content: "1"
                },
                lastName : {
                    content: "Mollard"
                },
                firstName : {
                    content: "Maxime"
                },
                date_start : {
                    content: "14/05/2017"
                },
                date_end: {
                    content: "21/05/2017"
                },
                type:{
                    content:  "Chambre double"
                },
                price: {
                    content: "2000"
                }
            },
            "2":{
                number: {
                    content: "1"
                },
                lastName : {
                    content: "Mollard"
                },
                firstName : {
                    content: "Maxime"
                },
                date_start : {
                    content: "14/05/2017"
                },
                date_end: {
                    content: "21/05/2017"
                },
                type:{
                    content:  "Chambre double"
                },
                price: {
                    content: "2000"
                }
            },
            "3":{
                number: {
                    content: "1"
                },
                lastName : {
                    content: "Mollard"
                },
                firstName : {
                    content: "Maxime"
                },
                date_start : {
                    content: "14/05/2017"
                },
                date_end: {
                    content: "21/05/2017"
                },
                type:{
                    content:  "Chambre double"
                },
                price: {
                    content: "2000"
                }
            }
        });*/
    };
})();