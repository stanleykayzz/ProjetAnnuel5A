;(function(undefined) {
    "use strict";

    if(typeof Core === "undefined")
        throw "Core is not declared";

    Core.service.category = Core.service.category || {};

    Core.service.category.create = function () {
        return {
            name   : "create",
            method : "POST",
            url    : "/category",
            func : function () {

            },
            error : function(){

            }
        };
    };

    Core.service.category.delete = function () {
        return {
            name   : "delete",
            method : "DELETE",
            url    : "/category",
            func : function () {

            },
            error : function(){

            }
        };
    };

    Core.service.category.getListCategories = function () {
        return {
            name   : "getListCategories",
            method : "GET",
            url    : "/category",
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
})();