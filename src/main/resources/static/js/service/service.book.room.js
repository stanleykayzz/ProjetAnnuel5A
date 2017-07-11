;(function () {
    if(typeof Core === "undefined")
        throw "Core is not declared";

    Core.service.book.room = Core.service.book.room || {};

    Core.service.book.room.bookRoom = function () {
        return {
            name   : "bookRoom",
            method : "POST",
            url    : "",
            func : function (price) {
                if(price !== null && price !== undefined){
                    document.getElementById("label_price").textContent = price + " francs CFA";
                    Core.payment.paypal.generateButton(price, document.getElementById("button_paypal"));
                } else {
                    return null;
                }
            },
            error : function(statusCode){
            }
        };
    };

    Core.service.book.room.cancelBookRoom = function () {
        return {
            name   : "cancelBookRoom",
            method : "DELETE",
            url    : "",
            func : function () {
                views.includeContainer.switchView("chambre");
            },
            error : function(statusCode){
            }
        };
    };

    Core.service.book.room.getCurrentList = function () {
        return {
            name   : "getListById",
            method : "GET",
            url    : "",
            func : function (json) {
                var headers;
                if(client.status === 0){
                    headers = {
                        number: {
                            content: "Numéro de chambre"
                        },
                        lastName:{
                            content: "Nom"
                        },
                        firstName:{
                            content: "Prénom"
                        },
                        date_start : {
                            content: "Arrivée"
                        },
                        date_end: {
                            content: "Départ"
                        },
                        type:{
                            content:  "Type"
                        },
                        price: {
                            content: "Prix"
                        }
                    };
                } else if(client.status === 1){
                    headers = {
                        number: {
                            content: "Numéro de chambre"
                        },
                        date_start : {
                            content: "Arrivée"
                        },
                        date_end: {
                            content: "Départ"
                        },
                        type:{
                            content:  "Type"
                        },
                        price: {
                            content: "Prix"
                        }
                    };
                }
                utils.template.createLiTemplate(headers, json, document.getElementById("book_room_active_content"), "read");
            },
            error : function(statusCode){
            }
        };
    };

    Core.service.book.room.getHoldList = function () {
        return {
            name   : "getHoldListById",
            method : "GET",
            url    : "",
            func : function (json) {
                var headers = {
                    number: {
                        content: "Numéro de chambre"
                    },
                    date_start : {
                        content: "Arrivée"
                    },
                    date_end: {
                        content: "Départ"
                    },
                    type:{
                        content:  "Type"
                    },
                    price: {
                        content: "Prix"
                    }
                };

                utils.template.createLiTemplate(headers, json, document.getElementById("book_room_hold_content"), "read");
            },
            error : function(statusCode){
            }
        };
    };
})();