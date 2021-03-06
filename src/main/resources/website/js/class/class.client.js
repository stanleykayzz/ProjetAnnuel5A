;(function () {
    "use strict";

    if(typeof Core === "undefined")
        throw "Core is not declared";

    Core.class = Core.class || {};
    Core.class.client = Core.class.client || {};

    Core.class.client = function(clientJson){
        this.address    = clientJson.address;
        this.birthday   = clientJson.birthday;
        this.sexe       = clientJson.sexe;
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
        utils.ajaxRequest(Core.service.client.login(), paramRequest, null);
    };

    Core.class.client.confirmation = function (code) {
        var paramRequest = "email=" + window.sessionStorage.getItem("tmp_email") + "&code=" + code;
        utils.ajaxRequest(Core.service.client.confirmation(), paramRequest, null);
    };

    Core.class.client.signup = function (client) {
        utils.ajaxRequest(Core.service.client.signup(), null, client);
    };

    Core.class.client.removeSessionStorage = function () {
        window.sessionStorage.removeItem("token");
        window.sessionStorage.removeItem("token_date");
    };

    Core.class.client.reloadClient = function () {
        var token = window.sessionStorage.getItem("token");
        var paramRequest = "token=" + token;

        if(token !== null && token !== undefined)
            utils.ajaxRequest(Core.service.client.getClientByToken(), paramRequest, null, true);
    };

    //Prototype function

    Core.class.client.prototype.logout = function () {
        var paramRequest = "token="+ window.client.token;
        utils.ajaxRequest(Core.service.client.logout(), paramRequest, null, false);
    };

    Core.class.client.prototype.update = function (client, password) {
        var paramRequest = "token="+ window.client.token + "&password=" + password;
        utils.ajaxRequest(Core.service.client.update(), paramRequest, client);
    };

    Core.class.client.prototype.createSessionStorage = function (token, tokenDate) {
        var tokenAvailable = utils.verifSessionStorage(tokenDate);

        if(tokenAvailable === true){
            window.sessionStorage.setItem("token", token);
            window.sessionStorage.setItem("token_date", tokenDate);
        }
    };

    Core.class.client.prototype.reloadTokenDate = function (token) {
        var paramRequest = "token="+ window.client.token;
        utils.ajaxRequest(Core.service.client.reloadToken(), paramRequest, client, true);
    };
})();