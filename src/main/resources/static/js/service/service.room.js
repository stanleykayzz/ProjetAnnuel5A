;(function () {
    "use strict";

    if(typeof Core === "undefined")
        throw "Core is not declared";

    Core.service.room = Core.service.room || {};

    Core.service.room.search = function () {
        return {
            name   : "searchRoom",
            method : "GET",
            url    : "/booking/search",
            func : function (listRoom) {
                if(listRoom !== null && listRoom !== undefined)
                    Core.views.includeContainer.roomSearch(listRoom);
                else
                    return null;
            },
            error : function(statusCode){
            }
        };
    };

    Core.service.room.getListCategories = function () {
        return {
            name   : "getListCategories",
            method : "GET",
            url    : "/category/getListCategories",
            func : function (listCategories) {
                if(listCategories !== null && listCategories !== undefined)
                    data.listCategories = listCategories;
                else
                    return null;
            },
            error : function(statusCode){
            }
        };
    };

    Core.service.room.getBookinPrice = function () {
        return {
            name   : "getBookinPrice",
            method : "GET",
            url    : "/booking/getBookinPrice",
            func : function (price) {
                if(price !== null && price !== undefined){
                    document.getElementById("label_price").textContent = price + " €";
                    Core.payment.paypal.generateButton(price, document.getElementById("button_paypal"));

                } else {
                    return null;
                }

            },
            error : function(statusCode){
            }
        };
    };
})();