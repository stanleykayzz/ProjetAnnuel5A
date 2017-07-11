;(function (undefined) {
    "use strict";

    if (typeof Core === "undefined")
        throw "Core is not declared";

    Core.service.category = Core.service.category || {};

    Core.service.category.create = function () {
        return {
            name: "create",
            method: "POST",
            url: "/category",
            func: function () {
                
            },
            error: function () {

            }
        };
    };

    Core.service.category.delete = function () {
        return {
            name: "delete",
            method: "DELETE",
            url: "/category",
            func: function () {

            },
            error: function () {

            }
        };
    };

    Core.service.category.getListCategories = function () {
        return {
            name: "getListCategories",
            method: "GET",
            url: "/category",
            func: function (listCategories) {
                if (listCategories !== null && listCategories !== undefined)
                    data.listCategories = listCategories;
                else
                    return null;
            },
            error: function (statusCode) {
            }
        };
    };

    Core.service.category.initViewListCategories = function () {
        return {
            name: "initViewListCategories",
            method: "GET",
            url: "/category",
            func: function (json) {
                var headers = {
                    id: {
                        content: "ID"
                    },
                    name: {
                        content: "Nom"
                    },
                    costByNight: {
                        content: "Coût par nuit"
                    },
                    button: {
                        delete: {
                            btnClass: "btn btn-danger",
                            icone: "glyphicon glyphicon-remove",
                            preId: "categorie_delete",
                            func: function (element) {
                                utils.addListener(element, 'click', function (e) {
                                    utils.template.eventButtonRemove(e, Core.service.category.delete());
                                }, false);
                            }
                        }
                    }
                };

                utils.template.createLiTemplate(headers, null, document.getElementById("list_category_content"), "create", Core.service.category.create());
                utils.template.createLiTemplate(headers, json, document.getElementById("list_category_content"), "read");
            },
            error: function (statusCode) {
            }
        };
    };

})();