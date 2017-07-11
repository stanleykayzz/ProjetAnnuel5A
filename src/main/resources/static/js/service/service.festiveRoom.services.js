;(function () {
    "use strict";

    if (typeof Core === "undefined")
        throw "Core is not declared";

    Core.service.festiveRoom.services = Core.service.festiveRoom.services || {};

    Core.service.festiveRoom.services.create = function () {
        return {
            name: "create",
            method: "POST",
            url: "/services",
            func: function (json) {

            },
            error: function (statusCode) {
            }
        }
    };

    Core.service.festiveRoom.services.update = function () {
        return {
            name: "update",
            method: "PUT",
            url: "/services",
            func: function (json) {

            },
            error: function (statusCode) {
            }
        }
    };

    Core.service.festiveRoom.services.delete = function () {
        return {
            name: "delete",
            method: "DELETE",
            url: "/services",
            func: function (json) {

            },
            error: function (statusCode) {
            }
        }
    };

    Core.service.festiveRoom.services.getList = function () {
        return {
            name: "getList",
            method: "GET",
            url: "/services",
            func: function (json) {
                Core.views.festiveRoom.initView(json);
            },
            error: function (statusCode) {
            }
        }
    };
})();
