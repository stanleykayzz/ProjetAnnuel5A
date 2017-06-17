;(function () {
    "use strict";

    if(typeof Core === "undefined")
        throw "Core is not declared";

    Core.class = Core.class || {};
    Core.class.client = Core.class.client || {};

    Core.class.client = function(clientJson){
        this.address    = clientJson.address;
        this.birthday   = clientJson.birthday;
        this.city       = clientJson.city;
        this.clientId   = clientJson.clientId;
        this.country    = clientJson.country;
        this.email      = clientJson.email;
        this.firstName  = clientJson.firstName;
        this.name       = clientJson.name;
        this.password   = clientJson.password;
        this.phone      = clientJson.phone;
        this.postalCode = clientJson.postalCode;
        this.status     = clientJson.status;
        this.token      = clientJson.token;
        this.tokenDate  = clientJson.tokenDate;
    };

    Core.class.client.login = function (email, password) {
        var paramRequest = "email="+email+"&password="+password;
        utils.ajaxRequest(data.clientService.login, paramRequest, null);
    };

    Core.class.client.signup = function (client) {
        utils.ajaxRequest(data.clientService.signup, null, client);
    };
})();