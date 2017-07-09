;(function () {
    "use strict";

    if (typeof Core === "undefined")
        throw "Core is not declared";

    Core.service.restaurant = Core.service.restaurant || {};

    Core.service.restaurant.create = function () {
        return {
            name   : "create",
            method : "POST",
            url    : "/restaurant",
            func : function () {

            },
            error : function(){

            }
        };
    };

    Core.service.restaurant.udapte = function () {
        return {
            name   : "create",
            method : "PUT",
            url    : "/restaurant",
            func : function () {

            },
            error : function(){

            }
        };
    };

    Core.service.restaurant.delete = function () {
        return {
            name   : "delete",
            method : "DELETE",
            url    : "/restaurant",
            func : function () {

            },
            error : function(){

            }
        };
    };

    Core.service.restaurant.getList = function () {
        return {
            name   : "getListTable",
            method : "GET",
            url    : "/restaurant",
            func : function () {

            },
            error : function(){

            }
        };
    };


})();