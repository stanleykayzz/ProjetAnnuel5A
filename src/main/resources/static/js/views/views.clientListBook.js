;(function () {
    "use strict";

    if(typeof Core === "undefined")
        throw "Core is not declared";

    Core.views.clientListBook = Core.views.clientListBook || {};

    Core.views.clientListBook.initView = function () {
        var list_menu = document.getElementsByClassName("li_menu");

        for(var i = 0 ; i < list_menu.length ; i++) {
            utils.template.manageListDisplay(list_menu[i].id);
        }
    };

})();