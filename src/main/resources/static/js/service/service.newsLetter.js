;(function(undefined) {
    "use strict";

    if(typeof Core === "undefined")
        throw "Core is not declared";

    Core.service.newsLetter = Core.service.newsLetter || {};

    Core.service.newsLetter.send = function () {
        return {
            name   : "send",
            method : "POST",
            url    : "/newsLetter",
            func : function (clt) {

            },
            error : function(statusCode){

            }
        };
    }
})();