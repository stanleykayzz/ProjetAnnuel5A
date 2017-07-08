;(function () {
    "use strict";

    if(typeof Core === "undefined")
        throw "Core is not declared";

    Core.views.code = Core.views.code || {};

    Core.views.code.confirmationCode = function () {
        var btn_code = document.getElementById("btn_code");
        var ipt_code = document.getElementById("codeBtn");

        utils.addListener(btn_code, "click", function () {
            Core.class.client.confirmation(ipt_code.value);
        }, false);
    };
})();