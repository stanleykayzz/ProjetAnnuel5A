;(function(undefined) {
    "use strict";

    if(typeof Core === "undefined")
        throw "Core is not declared";

    Core.service.article = Core.service.article || {};

    Core.service.article.create = function () {
        return {
            name   : "create",
            method : "POST",
            url    : "/article",
            func : function () {

            },
            error : function(){

            }
        };
    };

    Core.service.article.udapte = function () {
        return {
            name   : "udapte",
            method : "PUT",
            url    : "/article",
            func : function () {

            },
            error : function(){

            }
        };
    };

    Core.service.article.delete = function () {
        return {
            name   : "delete",
            method : "DELETE",
            url    : "/article",
            func : function () {

            },
            error : function(){

            }
        };
    };

    Core.service.article.getList = function () {
        return {
            name   : "getList",
            method : "GET",
            url    : "/article",
            func : function () {

            },
            error : function(){

            }
        };
    };
})();