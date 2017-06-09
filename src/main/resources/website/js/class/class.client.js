;(function () {
    "use strict";

    if(typeof Core === "undefined")
        throw "Core is not declared";

    Core.class = Core.class || {};
    Core.class.client = Core.class.client || {};

    Core.class.client = function(){

    };

    Core.class.client.login = function (email, password) {
        //var paramRequest = "email=momo@hotmail.fr&password=test";
        var paramRequest = "email="+email+"&password="+password;
        var request = utils.ajaxRequest(data.clientService.login, paramRequest, null);
    };

})();