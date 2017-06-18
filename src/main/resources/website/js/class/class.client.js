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

    Core.class.client.logout = function () {
        var paramRequest = "token="+ window.client.token;
        utils.ajaxRequest(data.clientService.logout, paramRequest, null);
    };

    Core.class.client.update = function (client) {
        var paramRequest = "token="+ window.client.token;
        client = '{'+
            '"name": "mollard",'+
            '"firstName": "toto",'+
            '"birthday": "1993-09-16",'+
            '"email": "tefzt@hotmail.fr",'+
            '"phone": "0102030406",'+
            '"country": "france",'+
            '"city": "Paris",'+
            '"address": "70 rue toto",'+
            '"postalCode": "75015",'+
            '"password": "test_5"'+
        '}';
        utils.ajaxRequest(data.clientService.update, paramRequest, client);
    };
})();