;(function () {
    "use strict";

    if(typeof Core === "undefined")
        throw "Core is not declared";

    Core.views.restaurant = Core.views.restaurant || {};

    Core.views.includeContainer.initView = function () {
        var typeElement, numberElement, btn_booking;

        typeElement = document.getElementById("select_time");
        numberElement = document.getElementById("select_number");
        btn_booking = document.getElementById("btn_search_table");


        utils.removeListener(btn_booking, "click");
        utils.addListener(btn_booking, "click", function () {
            var json = {
                type: typeElement.options[typeElement.selectedIndex].value,
                number: numberElement.options[numberElement.selectedIndex].value
            };
            Core.class.restaurant.booking(json);
        }, false);
    };

})();
