;(function () {
    "use strict";

    if(typeof Core === "undefined")
        throw "Core is not declared";

    Core.service.room = Core.service.room || {};

    Core.service.room.search = function () {
        return {
            name   : "searchRoom",
            method : "GET",
            url    : "/room/search",
            func : function (listRoom) {
                if(listRoom !== null && listRoom !== undefined)
                    Core.views.room.roomSearch(listRoom);
                else
                    return null;
            },
            error : function(statusCode){
            }
        };
    };


    Core.service.room.create = function () {
        return {
            name   : "create",
            method : "POST",
            url    : "/room",
            func : function () {

            },
            error : function(statusCode){
            }
        };
    };

    Core.service.room.update = function () {
        return {
            name   : "update",
            method : "PUT",
            url    : "/room",
            func : function () {

            },
            error : function(statusCode){
            }
        };
    };

    Core.service.room.delete = function () {
        return {
            name   : "delete",
            method : "DELETE",
            url    : "/room",
            func : function () {

            },
            error : function(statusCode){
            }
        };
    };

    Core.service.room.getList = function () {
        return {
            name   : "getList",
            method : "GET",
            url    : "/room",
            func : function (listRoom) {

            },
            error : function(statusCode){
            }
        };
    };

})();